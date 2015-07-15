package so.glad.channel.wechat.springsocial.connect;

/**
 * @author palmtale
 *         on 15/7/11.
 */
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import so.glad.channel.wechat.api.WeChat;

public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    /**
     * Creates a WechatConnectionFactory for the given application ID, secret, and namespace.
     * @param appId The application's App ID as assigned by Wechat
     * @param appSecret The application's App Secret as assigned by Wechat
     */
    public WeChatConnectionFactory(String appId, String appSecret) {
        super("wechat", new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
    }

}