package so.glad.channel.wechat.springsocial;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class AbstractWechatOperations {

    private RestOperations restOperations;
    private final boolean isAuthorized;

    public AbstractWechatOperations(RestOperations restOperations, boolean isAuthorized) {
        this.restOperations = restOperations;
        this.isAuthorized = isAuthorized;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("facebook");
        }
    }

    public <T> T fetchObject(String BASE_URI, Class<T> type, MultiValueMap<String, String> queryParameters) {
        URI uri = URIBuilder.fromUri(BASE_URI).queryParams(queryParameters).build();
        return getRestOperations().getForObject(uri, type);
    }

    public <T> T fetchObject(String BASE_URI, Class<T> type, String paramName, String paramValue) {
        URI uri = URIBuilder.fromUri(BASE_URI).queryParam(paramName, paramValue).build();
        return getRestOperations().getForObject(uri, type);
    }

    protected RestOperations getRestOperations() {
        return restOperations;
    }
}
