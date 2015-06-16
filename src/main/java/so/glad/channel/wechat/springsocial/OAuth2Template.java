package so.glad.channel.wechat.springsocial;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.FormMapHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.Const;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/6/16.
 */
public class OAuth2Template implements OAuth2Operations {

    private final String appKey;

    private final String appSecret;

    private final String apiUrl;

    private final String openUrl;

    private RestTemplate restTemplate;

    public OAuth2Template(String appKey, String appSecret) {
        Assert.notNull(appKey, "The appKey for Wechat cannot be null");
        Assert.notNull(appSecret, "The appSecret for Wechat cannot be null");
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.apiUrl = Configure.getProperty(Const.ConfigKey.API_URL);
        this.openUrl = Configure.getProperty(Const.ConfigKey.OPEN_URL);
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        String qrConnectURL = Configure.getProperty(Const.ConfigKey.OPEN_URL) + Configure.getProperty(Const.ConfigKey.CONNECT_QRCODE);
        return buildAuthUrl(qrConnectURL, GrantType.AUTHORIZATION_CODE, parameters);
    }

    @Override
    public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
        String qrConnectURL = Configure.getProperty(Const.ConfigKey.OPEN_URL) + Configure.getProperty(Const.ConfigKey.CONNECT_QRCODE);
        return buildAuthUrl(qrConnectURL, grantType, parameters);
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        return buildAuthorizeUrl(parameters);
    }

    @Override
    public String buildAuthenticateUrl(GrantType grantType, OAuth2Parameters parameters) {
        return buildAuthorizeUrl(grantType, parameters);
    }
    private String buildAuthUrl(String baseAuthUrl, GrantType grantType, OAuth2Parameters parameters) {
        StringBuilder authUrl = new StringBuilder(baseAuthUrl);
        authUrl.append(Configure.getProperty(Const.ConfigKey.CONNECT_QRCODE)).append("?").append(Const.AUTH_PARAM.APP_KEY).append("=").append(appKey);
        if (grantType == GrantType.AUTHORIZATION_CODE) {
            authUrl.append('&').append(Const.AUTH_PARAM.RESPONSE_TYPE).append('=').append("code");
        } else if (grantType == GrantType.IMPLICIT_GRANT) {
            authUrl.append('&').append(Const.AUTH_PARAM.RESPONSE_TYPE).append('=').append("token");
        }
        for (Map.Entry<String, List<String>> param : parameters.entrySet()) {
            String name = formEncode(param.getKey());
            for (String s : param.getValue()) {
                authUrl.append('&').append(name).append('=').append(formEncode(s));
            }
        }
        return authUrl.append("#wechat_redirect").toString();
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set(Const.AUTH_PARAM.APP_KEY, appKey);
        params.set(Const.AUTH_PARAM.APP_SECRET, appSecret);
        params.set(Const.AUTH_PARAM.CODE, authorizationCode);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "authorization_code");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(this.apiUrl + Configure.getProperty(Const.ConfigKey.OAUTH_TOKEN_ACHIEVE), params);
    }

    @Override
    public AccessGrant exchangeCredentialsForAccess(String username, String password, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set(Const.AUTH_PARAM.APP_KEY, appKey);
        params.set(Const.AUTH_PARAM.APP_SECRET, appSecret);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "password");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(this.apiUrl + Configure.getProperty(Const.ConfigKey.OAUTH_TOKEN_ACHIEVE), params);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, String scope, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set(Const.AUTH_PARAM.APP_KEY, appKey);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "refresh_token");
        params.set(Const.AUTH_PARAM.REFRESH_TOKEN, refreshToken);
        if(StringUtils.isNotBlank(scope)) {
            params.set(Const.AUTH_PARAM.SCOPE, scope);
        }
        if(additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(this.apiUrl + Configure.getProperty(Const.ConfigKey.OAUTH_TOKEN_REFRESH), params);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        return refreshAccess(refreshToken, null, additionalParameters);
    }

    @Override
    public AccessGrant authenticateClient() {
        //TODO
        return null;
    }

    @Override
    public AccessGrant authenticateClient(String scope) {
        //TODO
        return null;
    }

    private String formEncode(String data) {
        try {
            return URLEncoder.encode(data, Const.DEFAULT_ENCODING);
        }
        catch (UnsupportedEncodingException ex) {
            // should not happen, UTF-8 is always supported
            throw new IllegalStateException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        return extractAccessGrant(getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class));
    }

    private AccessGrant extractAccessGrant(Map<String, Object> result) {
        return createAccessGrant((String) result.get(Const.AUTH_PARAM.ACCESS_TOKEN),
                (String) result.get(Const.AUTH_PARAM.SCOPE),
                (String) result.get(Const.AUTH_PARAM.REFRESH_TOKEN),
                getIntegerValue(result, Const.AUTH_PARAM.EXPIRES_IN),
                result);
    }

    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return new AccessGrant(accessToken, scope, refreshToken, expiresIn);
    }

    protected RestTemplate getRestTemplate() {
        // Lazily create RestTemplate to make sure all parameters have had a chance to be set.
        // Can't do this InitializingBean.afterPropertiesSet() because it will often be created directly and not as a bean.
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }
        return restTemplate;
    }

    protected RestTemplate createRestTemplate() {
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converters = Lists.newArrayList();
        converters.add(new FormHttpMessageConverter());
        converters.add(new FormMapHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    private Long getIntegerValue(Map<String, Object> map, String key) {
        try {
            return Long.valueOf(String.valueOf(map.get(key))); // normalize to String before creating integer value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
