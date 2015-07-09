package so.glad.channel.wechat.model;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class MessageError {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageError)) {
            return false;
        }
        MessageError that = (MessageError) o;
        return Objects.equal(errCode, that.errCode) &&
                Objects.equal(errMsg, that.errMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(errCode, errMsg);
    }
}
