package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public abstract class Message {

    private String toUser;

    private String msgType;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equal(toUser, message.toUser) &&
                Objects.equal(msgType, message.msgType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(toUser, msgType);
    }
}
