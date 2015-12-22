package so.glad.channel.wechat.api;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestOperations;

/**
 * @author Cartoon
 *         on 2015/4/9.
 */
public interface WeChat extends ApiBinding {
    /**
     * API for performing operations on Wechat user profiles.
     * @return {@link UserOperations}
     */
    UserOperations userOperations();

    /**
     * API for performing operations on Wechat validation checking.
     * @return {@link ValidationOperations}
     */
    ValidationOperations validationOperations();

    /**
     * Returns the underlying {@link RestOperations} object allowing for consumption of Wechat endpoints that may not be otherwise covered by the API binding.
     * The RestOperations object returned is configured to include an OAuth 2 "Authorization" header on all requests.
     * @return RestOperations instrumented to include Authorization header on all requests
     */
    RestOperations restOperations();
}
