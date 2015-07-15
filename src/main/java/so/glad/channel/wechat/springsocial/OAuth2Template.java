package so.glad.channel.wechat.springsocial;

import com.google.common.collect.Lists;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
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

    private final String clientId;

    private final String clientSecret;

    private final String refreshTokenUrl;

    private final String accessTokenUrl;

    private final String authorizeUrl;

    private String authenticateUrl;

    private RestTemplate restTemplate;

    private boolean useParametersForClientAuthentication;

    /**
     * Constructs an OAuth2Template for a given set of client credentials.
     * Assumes that the authorization URL is the same as the authentication URL.
     * @param clientId the client ID
     * @param clientSecret the client secret
     * @param authorizeUrl the base URL to redirect to when doing authorization code or implicit grant authorization
     * @param accessTokenUrl the URL at which an authorization code, or user credentials may be exchanged for an access token.
     */
    public OAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        this(clientId, clientSecret, authorizeUrl, null, accessTokenUrl);
    }

    /**
     * Constructs an OAuth2Template for a given set of client credentials.
     * @param clientId the client ID
     * @param clientSecret the client secret
     * @param authorizeUrl the base URL to redirect to when doing authorization code or implicit grant authorization
     * @param authenticateUrl the URL to redirect to when doing authentication via authorization code grant
     * @param accessTokenUrl the URL at which an authorization code, or user credentials may be exchanged for an access token
     */
    public OAuth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        this(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl, accessTokenUrl);
    }

    /**
     * Constructs an OAuth2Template for a given set of client credentials.
     * @param clientId the client ID
     * @param clientSecret the client secret
     * @param authorizeUrl the base URL to redirect to when doing authorization code or implicit grant authorization
     * @param authenticateUrl the URL to redirect to when doing authentication via authorization code grant
     * @param accessTokenUrl the URL at which an authorization code, or user credentials may be exchanged for an access token
     * @param refreshTokenUrl the URL to refresh token
     */
    public OAuth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl, String refreshTokenUrl) {
        Assert.notNull(clientId, "The clientId property cannot be null");
        Assert.notNull(clientSecret, "The clientSecret property cannot be null");
        Assert.notNull(authorizeUrl, "The authorizeUrl property cannot be null");
        Assert.notNull(accessTokenUrl, "The accessTokenUrl property cannot be null");
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        String clientInfo = "?" + Const.AUTH_PARAM.APP_KEY + "=" + formEncode(clientId);
        this.authorizeUrl = authorizeUrl + clientInfo;
        if (authenticateUrl != null) {
            this.authenticateUrl = authenticateUrl + clientInfo;
        } else {
            this.authenticateUrl = null;
        }
        this.accessTokenUrl = accessTokenUrl;
        this.refreshTokenUrl = refreshTokenUrl;
    }

    /**
     * Set to true to pass client credentials to the provider as parameters instead of using HTTP Basic authentication.
     * @param useParametersForClientAuthentication to indicate if or not useParametersForClientAuthentication
     */
    public void setUseParametersForClientAuthentication(boolean useParametersForClientAuthentication) {
        this.useParametersForClientAuthentication = useParametersForClientAuthentication;
    }

    /**
     * Set the request factory on the underlying RestTemplate.
     * This can be used to plug in a different HttpClient to do things like configure custom SSL settings.
     * @param requestFactory a {@link ClientHttpRequestFactory} ClientHttpRequestFactory to set.
     */
    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        Assert.notNull(requestFactory, "The requestFactory property cannot be null");
        getRestTemplate().setRequestFactory(requestFactory);
    }

    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthUrl(authorizeUrl, GrantType.AUTHORIZATION_CODE, parameters);
    }

    public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
        return buildAuthUrl(authorizeUrl, grantType, parameters);
    }

    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        return authenticateUrl != null ? buildAuthUrl(authenticateUrl, GrantType.AUTHORIZATION_CODE, parameters) : buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);
    }

    public String buildAuthenticateUrl(GrantType grantType, OAuth2Parameters parameters) {
        return authenticateUrl != null ? buildAuthUrl(authenticateUrl, grantType, parameters) : buildAuthorizeUrl(grantType, parameters);
    }

    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (useParametersForClientAuthentication) {
            params.set(Const.AUTH_PARAM.APP_KEY, clientId);
            params.set(Const.AUTH_PARAM.APP_SECRET, clientSecret);
        }
        params.set(Const.AUTH_PARAM.CODE, authorizationCode);
        params.set(Const.AUTH_PARAM.REDIRECT_URL, redirectUri);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "authorization_code");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }


    public AccessGrant exchangeCredentialsForAccess(String username, String password, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (useParametersForClientAuthentication) {
            params.set(Const.AUTH_PARAM.APP_KEY, clientId);
            params.set(Const.AUTH_PARAM.APP_SECRET, clientSecret);
        }
        params.set("username", username);
        params.set("password", password);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "password");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    @Deprecated
    public AccessGrant refreshAccess(String refreshToken, String scope, MultiValueMap<String, String> additionalParameters) {
        additionalParameters.set("scope", scope);
        return refreshAccess(refreshToken, additionalParameters);
    }

    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (useParametersForClientAuthentication) {
            params.set(Const.AUTH_PARAM.APP_KEY, clientId);
            params.set(Const.AUTH_PARAM.APP_SECRET, clientSecret);
        }
        params.set(Const.AUTH_PARAM.REFRESH_TOKEN, refreshToken);
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "refresh_token");
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(refreshTokenUrl, params);
    }

    public AccessGrant authenticateClient() {
        return authenticateClient(null);
    }

    public AccessGrant authenticateClient(String scope) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (useParametersForClientAuthentication) {
            params.set(Const.AUTH_PARAM.APP_KEY, clientId);
            params.set(Const.AUTH_PARAM.APP_SECRET, clientSecret);
        }
        params.set(Const.AUTH_PARAM.GRANT_TYPE, "client_credentials");
        if (scope != null) {
            params.set(Const.AUTH_PARAM.SCOPE, scope);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    // subclassing hooks

    /**
     * Creates the {@link RestTemplate} used to communicate with the provider's OAuth 2 API.
     * This implementation creates a RestTemplate with a minimal set of HTTP message converters ({@link FormHttpMessageConverter} and {@link MappingJackson2HttpMessageConverter}).
     * May be overridden to customize how the RestTemplate is created.
     * For example, if the provider returns data in some format other than JSON for form-encoded, you might override to register an appropriate message converter.
     * @return the {@link RestTemplate} RestTemplate.
     */
    protected RestTemplate createRestTemplate() {
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converters = Lists.newArrayList();
        converters.add(new FormHttpMessageConverter());
        converters.add(new FormMapHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        if (!useParametersForClientAuthentication) {
            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            if (interceptors == null) {   // defensively initialize list if it is null. (See SOCIAL-430)
                interceptors = Lists.newArrayList();
                restTemplate.setInterceptors(interceptors);
            }
            //interceptors.add(new PreemptiveBasicAuthClientHttpRequestInterceptor(clientId, clientSecret));
        }
        return restTemplate;
    }

    /**
     * Posts the request for an access grant to the provider.
     * The default implementation uses RestTemplate to request the access token and expects a JSON response to be bound to a Map. The information in the Map will be used to create an {@link AccessGrant}.
     * Since the OAuth 2 specification indicates that an access token response should be in JSON format, there's often no need to override this method.
     * If all you need to do is capture provider-specific data in the response, you should override createAccessGrant() instead.
     * However, in the event of a provider whose access token response is non-JSON, you may need to override this method to request that the response be bound to something other than a Map.
     * For example, if the access token response is given as form-encoded, this method should be overridden to call RestTemplate.postForObject() asking for the response to be bound to a MultiValueMap (whose contents can then be used to create an AccessGrant).
     * @param accessTokenUrl the URL of the provider's access token endpoint.
     * @param parameters the parameters to post to the access token endpoint.
     * @return the access grant.
     */
    @SuppressWarnings("unchecked")
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        return extractAccessGrant(getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class));
    }

    /**
     * Creates an {@link AccessGrant} given the response from the access token exchange with the provider.
     * May be overridden to create a custom AccessGrant that captures provider-specific information from the access token response.
     * @param accessToken the access token value received from the provider
     * @param scope the scope of the access token
     * @param refreshToken a refresh token value received from the provider
     * @param expiresIn the time (in seconds) remaining before the access token expires.
     * @param response all parameters from the response received in the access token exchange.
     * @return an {@link AccessGrant}
     */
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return new AccessGrant(accessToken, scope, refreshToken, expiresIn);
    }

    // testing hooks

    protected RestTemplate getRestTemplate() {
        // Lazily create RestTemplate to make sure all parameters have had a chance to be set.
        // Can't do this InitializingBean.afterPropertiesSet() because it will often be created directly and not as a bean.
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }
        return restTemplate;
    }

    // internal helpers
    private String buildAuthUrl(String baseAuthUrl, GrantType grantType, OAuth2Parameters parameters) {
        StringBuilder authUrl = new StringBuilder(baseAuthUrl);
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
        return authUrl.toString();
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

    private AccessGrant extractAccessGrant(Map<String, Object> result) {
        return createAccessGrant((String) result.get(Const.AUTH_PARAM.ACCESS_TOKEN),
                (String) result.get(Const.AUTH_PARAM.SCOPE),
                (String) result.get(Const.AUTH_PARAM.REFRESH_TOKEN),
                getIntegerValue(result, Const.AUTH_PARAM.EXPIRES_IN), result);
    }

    // Retrieves object from map into an Integer, regardless of the object's actual type. Allows for flexibility in object type (eg, "3600" vs 3600).
    private Long getIntegerValue(Map<String, Object> map, String key) {
        try {
            return Long.valueOf(String.valueOf(map.get(key))); // normalize to String before creating integer value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
