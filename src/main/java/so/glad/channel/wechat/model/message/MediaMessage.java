package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class MediaMessage {

    private String type;

    private String mediaId;

    private Date createAt;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
        if (!(o instanceof MediaMessage)) {
            return false;
        }
        MediaMessage that = (MediaMessage) o;
        return Objects.equal(type, that.type) &&
                Objects.equal(mediaId, that.mediaId) &&
                Objects.equal(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, mediaId, createAt);
    }
}
