package so.glad.channel.wechat.springsocial;

import org.springframework.web.client.RestOperations;
import so.glad.channel.wechat.api.ValidationOperations;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class ValidationSpringSocial extends AbstractWechatOperations implements
        ValidationOperations {

    public ValidationSpringSocial(RestOperations restOperations,
                                  boolean isAuthorized) {
        super(restOperations, isAuthorized);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.wechat.api.ValidationOperations#validateAccessToken(java.lang.String, java.lang.String)
     */
    @Override
    public boolean validateAccessToken(String accessToken, String openId) {
        requireAuthorization();
        //TODO
        return false;
    }

}
