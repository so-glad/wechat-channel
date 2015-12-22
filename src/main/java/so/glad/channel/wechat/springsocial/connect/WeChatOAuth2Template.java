package so.glad.channel.wechat.springsocial.connect;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.MultiValueMap;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.Const;
import so.glad.channel.wechat.springsocial.OAuth2Template;

import java.util.Map;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class WeChatOAuth2Template extends OAuth2Template {

    public WeChatOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret,
                Configure.getProperty(Const.ConfigKey.OPEN_URL) + Configure.getProperty(Const.ConfigKey.CONNECT_QRCODE),
                null,
                Configure.getProperty(Const.ConfigKey.API_URL) + Configure.getProperty(Const.ConfigKey.OAUTH_TOKEN_ACHIEVE),
                Configure.getProperty(Const.ConfigKey.API_URL) + Configure.getProperty(Const.ConfigKey.OAUTH_TOKEN_REFRESH)
        );
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        return super.postForAccessGrant(accessTokenUrl, parameters);
    }

    @Override
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return new WeChatAccessGrant(accessToken, scope, refreshToken, expiresIn, (String)response.get("openid"));
    }

}

