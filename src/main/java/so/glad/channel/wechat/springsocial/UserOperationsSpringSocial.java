package so.glad.channel.wechat.springsocial;

import org.springframework.web.client.RestOperations;
import so.glad.channel.wechat.api.UserOperations;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class UserOperationsSpringSocial extends AbstractWechatOperations implements UserOperations {

    public UserOperationsSpringSocial(RestOperations restOperations, boolean isAuthorizedForUser) {
        super(restOperations, isAuthorizedForUser);
    }


}
