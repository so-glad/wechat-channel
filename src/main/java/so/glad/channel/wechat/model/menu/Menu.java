package so.glad.channel.wechat.model.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author palmtale
 *         on 15/7/10.
 */

public class Menu {

    private Boolean menuOpen;

    private List<MenuButton> button;
    @JsonProperty("is_menu_open")
    public Boolean getMenuOpen() {
        return menuOpen;
    }

    public void setMenuOpen(Boolean menuOpen) {
        this.menuOpen = menuOpen;
    }
    @JsonProperty
    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equal(menuOpen, menu.menuOpen) &&
                Objects.equal(button, menu.button);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menuOpen, button);
    }
}
