package so.glad.channel.wechat.model.message;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public abstract class ResponseMessage extends WechatMessage {

    private Integer funcFlag;

    public Integer getFuncFlag() {
        return funcFlag;
    }

    public void setFuncFlag(Integer funcFlag) {
        this.funcFlag = funcFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseMessage)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ResponseMessage that = (ResponseMessage) o;
        return Objects.equal(funcFlag, that.funcFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), funcFlag);
    }
}
