package so.glad.channel.wechat.mp.api.spring;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.model.menu.Menu;
import so.glad.channel.wechat.model.menu.MenuButton;
import so.glad.channel.wechat.mp.api.MenuOperation;

/**
 * @author palmtale
 *         on 15/7/10.
 */
public class MenuOperationTest {

    private MenuOperation menuOperation;

    @Before
    public void prepare(){
        Configure.updateAccessToken("_rA3-Qhd1D_l8_0ZMuq2FAyT0L5Kz19oOS-yCyifFIueTND3-OCbtgt0Q_n65wItfyfYOsmmCLuGzHsDa4XbdL2qBxDoJpCOM9MFVxpAEdQ");
        MenuOperationSpring menuOperation = new MenuOperationSpring();
        menuOperation.setRestTemplate(new RestTemplate());
        this.menuOperation = menuOperation;
    }

    @Test
    public void testCreateMenu(){
        Menu menu = new Menu();
        menu.setButton(Lists.newArrayList());

        MenuButton menuButton = new MenuButton();
        menuButton.setType("view");
        menuButton.setName("在线问诊");
        menuButton.setUrl("http://zhi.zunbao.in");
        menu.getButton().add(menuButton);

        menuButton = new MenuButton();
        menuButton.setName("治宠宝典");
        menuButton.setSubButtons(Lists.newArrayList());
        menuButton.getSubButtons().add(new MenuButton());
        menuButton.getSubButtons().get(0).setType("view");
        menuButton.getSubButtons().get(0).setName("喵症查询");
        menuButton.getSubButtons().get(0).setUrl("http://zhi.zunbao.in/#/index/wiki_category3/4");
        menuButton.getSubButtons().add(new MenuButton());
        menuButton.getSubButtons().get(1).setType("view");
        menuButton.getSubButtons().get(1).setName("汪症查询");
        menuButton.getSubButtons().get(1).setUrl("http://zhi.zunbao.in/#/index/wiki_category3/3");
        menuButton.getSubButtons().add(new MenuButton());
        menuButton.getSubButtons().get(2).setType("view");
        menuButton.getSubButtons().get(2).setName("喵药指南");
        menuButton.getSubButtons().get(2).setUrl("http://zhi.zunbao.in/#/index/wiki_category3/6");
        menuButton.getSubButtons().add(new MenuButton());
        menuButton.getSubButtons().get(3).setType("view");
        menuButton.getSubButtons().get(3).setName("汪药指南");
        menuButton.getSubButtons().get(3).setUrl("http://zhi.zunbao.in/#/index/wiki_category3/5");
        menuButton.getSubButtons().add(new MenuButton());
        menuButton.getSubButtons().get(4).setType("view");
        menuButton.getSubButtons().get(4).setName("御宠园");
        menuButton.getSubButtons().get(4).setUrl("http://zhi.zunbao.in");

        menu.getButton().add(menuButton);

        menuButton = new MenuButton();
        menuButton.setName("治尊服务");
        menuButton.setType("view");
        menuButton.setUrl("http://zhi.zunbao.in/admin/index.html");
        menu.getButton().add(menuButton);
        menuOperation.createMenu(menu);
    }
}
