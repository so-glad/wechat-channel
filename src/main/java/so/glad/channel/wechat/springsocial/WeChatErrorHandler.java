package so.glad.channel.wechat.springsocial;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.*;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/7/11.
 */
class WeChatErrorHandler extends DefaultResponseErrorHandler {

    private final static Logger logger = LoggerFactory.getLogger(WeChatErrorHandler.class);
    private final static String WECHAT = "wechat";
    private final static String ERROR_CODE = "errcode";
    private final static String ERROR_MESSAGE = "errmsg";

    /**
     * Delegates to {@link #hasError(HttpStatus)} with the response status code.
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        String json = readFully(response.getBody());
        if(json.contains(ERROR_CODE) && json.contains(ERROR_MESSAGE)){
            if(json.substring(json.indexOf(ERROR_MESSAGE)).contains("ok")){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        Map<String, String> errorDetails = extractErrorDetailsFromResponse(response);
        handleWechatError(response.getStatusCode(), errorDetails);
        if (errorDetails == null) {
            handleUncategorizedError(response, errorDetails);
        }

    }

    /**
     * Examines the error data returned from Wechat and throws the most applicable exception.
     * @param errorDetails a Map containing a "type" and a "message" corresponding to the Graph API's error response structure.
     */
    void handleWechatError(HttpStatus statusCode, Map<String, String> errorDetails) {
        // Can't trust the type to be useful. It's often OAuthException, even for things not OAuth-related.
        // Can rely only on the message (which itself isn't very consistent).
        String code = errorDetails.get(ERROR_CODE);
        String message = printErrorMapDetails(errorDetails);
        if (statusCode != HttpStatus.OK) {
            throw new ResourceNotFoundException(WECHAT, message);
        } else {
            if(code.equals("40029")){
                throw new NotAuthorizedException(WECHAT, message);
            }else if(code.equals("40030")){
                throw new InvalidAuthorizationException(WECHAT, message);
            }else if(code.equals("40003")){
                throw new OperationNotPermittedException(WECHAT, message);
            }else{
                throw new ResourceNotFoundException(WECHAT, message);
            }
        }
    }

    private void handleUncategorizedError(ClientHttpResponse response, Map<String, String> errorDetails) {
        try {
            super.handleError(response);
        } catch (Exception e) {
            if (errorDetails != null) {
                throw new UncategorizedApiException(WECHAT, printErrorMapDetails(errorDetails), e);
            } else {
                throw new UncategorizedApiException(WECHAT, "No error details from Wechat", e);
            }
        }
    }

    private String printErrorMapDetails(Map<String, String> errorDetails){
        return ERROR_CODE+"="+errorDetails.get(ERROR_CODE)+","+ERROR_MESSAGE+"="+errorDetails.get(ERROR_MESSAGE);
    }

    /*
     * Attempts to extract Wechat error details from the response.
     * Returns null if the response doesn't match the expected JSON error response.
     * Expected to return a Map<String, String> of keys 'errcode' & 'errmsg'.
     */
    private Map<String, String> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        String json = readFully(response.getBody());

        logger.debug("Error from Wechat: " + json);

        try {
            Map<String, String> responseMap = mapper.<Map<String, String>>readValue(json, new TypeReference<Map<String, String>>() {});
            Map<String, String> errorMap = Collections.<String,String>emptyMap();
            errorMap.put(ERROR_CODE, responseMap.get(ERROR_CODE));
            errorMap.put(ERROR_MESSAGE, responseMap.get(ERROR_MESSAGE));
            return errorMap;

        } catch (JsonParseException e) {
            return null;
        }
    }

    private String readFully(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        return sb.toString();
    }
}