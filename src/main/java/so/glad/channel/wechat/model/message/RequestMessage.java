package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author palmtale
 *         on 15/6/15.
 */
@XmlRootElement(name = "xml")
public class RequestMessage extends WechatMessage {

    private Long msgId;

    private String content;

    @XmlElement(name = "MsgId")
    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @XmlElement(name = "Content")
    @XmlCDATA
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
