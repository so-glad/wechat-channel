package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public abstract class RequestMessage extends WechatMessage {

    private long msgId;

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
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
        return Objects.equal(msgId, that.msgId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), msgId);
    }
}
