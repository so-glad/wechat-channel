package so.glad.channel.wechat.mp.api.spring;

import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.mp.api.AccessTokenUpdate;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class AccessTokenUpdatorInConfig implements AccessTokenUpdate {

    @Override
    public void updateAccessToken(String token) {
        Configure.updateAccessToken(token);
    }

}
