package so.glad.channel.wechat.springsocial.connect;

/**
 * @author palmtale
 *         on 15/7/11.
 */
import org.springframework.social.oauth2.AccessGrant;

/**
 * @author John Cao
 *
 */
@SuppressWarnings("serial")
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    public WeChatAccessGrant(String accessToken) {
        super(accessToken);
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken,
                             Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken,
                             Long expiresIn, String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }


}