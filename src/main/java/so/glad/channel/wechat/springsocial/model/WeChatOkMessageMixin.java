package so.glad.channel.wechat.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class WeChatOkMessageMixin extends WeChatObjectMixin {

    @JsonProperty("errcode")
    String errorCode;

    @JsonProperty("errmsg")
    String errorMessage;

}
