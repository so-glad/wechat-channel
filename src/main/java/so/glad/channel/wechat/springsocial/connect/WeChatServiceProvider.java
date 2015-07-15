package so.glad.channel.wechat.springsocial.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import so.glad.channel.wechat.api.WeChat;
import so.glad.channel.wechat.springsocial.WeChatSpringSocial;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    /**
     * Creates a WechatServiceProvider for the given application ID, secret, and namespace.
     * @param appId The application's App ID as assigned by Wechat
     * @param appSecret The application's App Secret as assigned by Wechat
     */
    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret));
    }

    public WeChat getApi(String accessToken) {
        return new WeChatSpringSocial(accessToken);
    }

}
