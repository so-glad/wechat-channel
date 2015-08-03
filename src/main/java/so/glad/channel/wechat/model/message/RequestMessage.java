package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author palmtale
 *         on 15/6/15.
 */
@XmlRootElement(name = "xml")
public class RequestMessage extends WechatMessage {

    private long msgId;

    private String content;

    @XmlElement(name = "MsgId")
    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    @XmlElement(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestMessage)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RequestMessage that = (RequestMessage) o;
        return Objects.equal(msgId, that.msgId) &&
                Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), msgId, content);
    }
}
