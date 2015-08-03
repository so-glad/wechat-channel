package so.glad.channel.wechat;

import so.glad.channel.wechat.model.VerifyParameters;

import java.io.InputStream;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/6/14.
 */
public abstract class WeChatController {

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

}
