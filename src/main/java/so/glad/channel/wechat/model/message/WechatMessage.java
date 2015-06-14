package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class WechatMessage extends Message {

    private String fromUser;

    private Long createTime;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WechatMessage)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        WechatMessage that = (WechatMessage) o;
        return Objects.equal(fromUser, that.fromUser) &&
                Objects.equal(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), fromUser, createTime);
    }
}
