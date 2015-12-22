package so.glad.channel.wechat.springsocial.model;

import java.io.Serializable;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@SuppressWarnings("serial")
public class WeChatOkMessage extends WeChatObject implements Serializable {

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

}
