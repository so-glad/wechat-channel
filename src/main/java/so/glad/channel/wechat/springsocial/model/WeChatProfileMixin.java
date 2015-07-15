package so.glad.channel.wechat.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class WeChatProfileMixin extends WeChatObjectMixin {

    @JsonProperty("openid")
    String openId;

    @JsonProperty("nickname")
    String nickName;

    @JsonProperty("sex")
    @JsonDeserialize(using=SexDeserializer.class)
    WeChatProfile.Sex sex;

    @JsonProperty("province")
    String province;

    @JsonProperty("city")
    String city;

    @JsonProperty("country")
    String country;

    @JsonProperty("headimgurl")
    String headImgUrl;

    @JsonProperty("privilege")
    List<String> privilege;

    @JsonProperty("unionid")
    String unionId;

    private static class SexDeserializer extends JsonDeserializer<WeChatProfile.Sex> {
        @Override
        public WeChatProfile.Sex deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            try {
                return WeChatProfile.Sex.valueOf(jp.getIntValue());
            } catch (IllegalArgumentException e) {
                return WeChatProfile.Sex.UNKNOWN;
            }
        }
    }

}