package so.glad.channel.wechat.mp.api.spring;

import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.Const;
import so.glad.channel.wechat.model.AccessToken;
import so.glad.channel.wechat.model.MediaType;
import so.glad.channel.wechat.model.message.MediaMessage;
import so.glad.channel.wechat.mp.api.BasicOperation;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class BasicOperationSpring implements BasicOperation {

    private RestTemplate restTemplate;

    @Override
    public AccessToken archieveAccessToken(String appKey, String appSecret) {
        StringBuilder url = new StringBuilder(Configure.getProperty(Const.ConfigKey.API_URL));
        try {
            url.append(Configure.getProperty(Const.ConfigKey.TOKEN_ACHIEVE)).append("?")
                    .append(Const.AUTH_PARAM.GRANT_TYPE).append("=").append("client_credential").append("&")
                    .append(Const.AUTH_PARAM.APP_KEY).append("=").append(URLEncoder.encode(appKey, Const.DEFAULT_ENCODING)).append("&")
                    .append(Const.AUTH_PARAM.APP_SECRET).append("=").append(URLEncoder.encode(appSecret, Const.DEFAULT_ENCODING)).append("&");
        } catch (UnsupportedEncodingException ignored){

        }
        Map accessMap = restTemplate.getForObject(url.toString(), Map.class);
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(accessMap.get(Const.AUTH_PARAM.ACCESS_TOKEN).toString());
        accessToken.setExpiresIn(Integer.parseInt(accessMap.get(Const.AUTH_PARAM.EXPIRES_IN).toString()));
        return accessToken;
    }

    @Override
    public MediaMessage uploadMedia(String accessToken, MediaType mediaType, Object o) {
        return null;
    }

    @Override
    public String downloadMedia(String accessToken, String mediaId) {
        return null;
    }
    @Resource
    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
}
