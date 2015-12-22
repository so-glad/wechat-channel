package so.glad.channel.wechat.impl;

import so.glad.channel.oauth2.AccessGrant;
import so.glad.channel.oauth2.GrantType;
import so.glad.channel.oauth2.OAuth2Operations;
import so.glad.channel.oauth2.OAuth2Parameters;

import java.util.Map;

/**
 * @author palmtale
 *         on 15/6/16.
 */
public class OAuth2Template extends AbstractOperation implements OAuth2Operations{
    @Override
    public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters oAuth2Parameters) {
        return null;
    }

    @Override
    public AccessGrant refreshAccess(String s, Map<String, String> map) {
        return null;
    }
}
