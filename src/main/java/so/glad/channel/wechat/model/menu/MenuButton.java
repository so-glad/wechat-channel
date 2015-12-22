package so.glad.channel.wechat.model.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author palmtale
 *         on 15/7/10.
 */
public class MenuButton {

    private MenuButtonType type;

    private String name;

    private String key;

    private String mediaId;

    private String url;

    private List<MenuButton> subButtons;
    @JsonProperty
    public String getType() {
        return type == null ? null : type.getCaption();
    }

    public void setType(String type) {
        this.type = MenuButtonType.valueOfCaption(type);
    }
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    @JsonProperty
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @JsonProperty("sub_button")
    public List<MenuButton> getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(List<MenuButton> subButtons) {
        this.subButtons = subButtons;
    }
    @JsonProperty("media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuButton)) {
            return false;
        }
        MenuButton that = (MenuButton) o;
        return Objects.equal(type, that.type) &&
                Objects.equal(name, that.name) &&
                Objects.equal(key, that.key) &&
                Objects.equal(mediaId, that.mediaId) &&
                Objects.equal(url, that.url) &&
                Objects.equal(subButtons, that.subButtons);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, name, key, mediaId, url, subButtons);
    }
}
