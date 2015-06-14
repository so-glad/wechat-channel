package so.glad.channel.wechat;

import so.glad.channel.wechat.model.VerifyParameters;

import java.io.InputStream;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/6/14.
 */
public class WeChatController {

    private String token;

    protected void setToken(String token){
        this.token = token;
    }

    public String get(VerifyParameters verifyParameters){
        if(verifyParameters.passBy(token)) {
            return verifyParameters.getEchostr();
        } else {
            return "";
        }
    }

    public String post(InputStream inputStream) {
        String respMessage = null;
        Map<String, String> messageMap = unmarshell(inputStream);
        String msgType = messageMap.get(Const.MESSAGE_KEY.MESSAGE_TYPE);
        return respMessage;
    }

    private Map<String, String> unmarshell(InputStream inputStream) {
        return null;
    }
}
