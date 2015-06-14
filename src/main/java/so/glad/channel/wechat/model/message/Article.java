package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class Article {

    private String title;

    private String description;

    private String picUrl;

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equal(title, article.title) &&
                Objects.equal(description, article.description) &&
                Objects.equal(picUrl, article.picUrl) &&
                Objects.equal(url, article.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, description, picUrl, url);
    }
}
