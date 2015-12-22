package so.glad.channel.wechat.springsocial.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public abstract class WeChatObject {

        private Map<String, Object> extraData;

        public WeChatObject() {
            this.extraData = new HashMap<String, Object>();
        }

        /**
         * @return Any fields in response from Wechat that are otherwise not mapped to any properties.
         */
        public Map<String, Object> getExtraData() {
            return extraData;
        }

        /**
         * {@link JsonAnySetter} hook. Called when an otherwise unmapped property is being processed during JSON deserialization.
         * @param key The property's key.
         * @param value The property's value.
         */
        protected void add(String key, Object value) {
            extraData.put(key, value);
        }

}

