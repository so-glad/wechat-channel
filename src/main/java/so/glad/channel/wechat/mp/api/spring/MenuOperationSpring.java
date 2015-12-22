package so.glad.channel.wechat.mp.api.spring;

import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.Const;
import so.glad.channel.wechat.model.menu.Menu;
import so.glad.channel.wechat.mp.api.MenuOperation;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author palmtale
 *         on 15/7/10.
 */
public class MenuOperationSpring implements MenuOperation {

    private RestTemplate restTemplate;

    @Override
    public void createMenu(Menu menu) {
        StringBuilder url = new StringBuilder(Configure.getProperty(Const.ConfigKey.API_URL));
        url.append(Configure.getProperty(Const.ConfigKey.MENU_CREATE)).append("?")
                .append(Const.AUTH_PARAM.ACCESS_TOKEN).append("=").append(Configure.getAccessToken());
        Map<String, String> map = restTemplate.postForObject(url.toString(), menu, Map.class);

    }

    @Override
    public Menu getMenu() {
        return null;
    }

    @Override
    public Menu getMenuInfo() {
        return null;
    }

    @Override
    public void deleteMenu() {

    }
    @Resource
    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
}
