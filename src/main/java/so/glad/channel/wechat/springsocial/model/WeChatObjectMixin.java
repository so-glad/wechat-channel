package so.glad.channel.wechat.springsocial.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class WeChatObjectMixin {

    @JsonAnySetter
    abstract void add(String key, Object value);

}
