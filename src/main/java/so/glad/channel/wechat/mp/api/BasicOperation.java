package so.glad.channel.wechat.mp.api;

import so.glad.channel.wechat.model.AccessToken;
import so.glad.channel.wechat.model.MediaType;
import so.glad.channel.wechat.model.message.MediaMessage;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public interface BasicOperation {

    AccessToken archieveAccessToken(String appKey, String appSecret);

    MediaMessage uploadMedia(String accessToken, MediaType mediaType, Object o);

    String downloadMedia(String accessToken, String mediaId);
}
