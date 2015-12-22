package so.glad.channel.wechat.springsocial.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import so.glad.channel.wechat.api.WeChat;

/**
 * @author palmtale
 *         on 15/7/11.
 */


public class WeChatAdapter implements ApiAdapter<WeChat> {

    public boolean test(WeChat wechat) {
        try {
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    public void setConnectionValues(WeChat wechat, ConnectionValues values) {
        throw new UnsupportedOperationException();
    }

    public UserProfile fetchUserProfile(WeChat wechat) {
        throw new UnsupportedOperationException();
    }

    public void updateStatus(WeChat wechat, String message) {
        throw new UnsupportedOperationException();
    }

}
