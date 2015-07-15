package so.glad.channel.wechat.springsocial;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.api.UserOperations;
import so.glad.channel.wechat.api.ValidationOperations;
import so.glad.channel.wechat.api.WeChat;
import so.glad.channel.wechat.springsocial.model.WeChatModule;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class WeChatSpringSocial extends AbstractOAuth2ApiBinding implements WeChat {

    private UserOperations userOperations;

    private ValidationOperations validationOperations;

    private ObjectMapper objectMapper;

    /**
     * Create a new instance of WechatTemplate.
     * This constructor creates the WechatTemplate using a given access token.
     * Wechat TokenStrategy should use ACCESS_TOKEN_PARAMETER, which add parameter 'access_token=ACCESS_TOKEN' to wechat requests.
     * @param accessToken An access token given by Wechat after a successful OAuth 2 authentication (or through Wechat's JS library).
     */
    public WeChatSpringSocial(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        initialize();
    }

    @Override
    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
    }
    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    public ValidationOperations validationOperations() {
        return validationOperations;
    }

    public RestOperations restOperations() {
        return getRestTemplate();
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new WeChatErrorHandler());
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WeChatModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    // private helpers
    private void initialize() {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
        initSubApis();
    }

    private void initSubApis() {
        userOperations = new UserOperationsSpringSocial(restOperations(), isAuthorized());
        validationOperations = new ValidationSpringSocial(restOperations(), isAuthorized());
    }
}
