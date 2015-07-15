package so.glad.channel.wechat.api;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public interface ValidationOperations {

    public boolean validateAccessToken(String accessToken, String openId);
}
