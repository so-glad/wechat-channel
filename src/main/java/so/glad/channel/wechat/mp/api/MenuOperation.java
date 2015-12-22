package so.glad.channel.wechat.mp.api;

import so.glad.channel.wechat.model.menu.Menu;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public interface MenuOperation {

    void createMenu(Menu menu);

    Menu getMenu();

    Menu getMenuInfo();

    void deleteMenu();

}
