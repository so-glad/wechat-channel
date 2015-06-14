package so.glad.channel.wechat.model;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class QRCode {

    private String expireSeconds;


    private String actionName;

    private String actionInfo;

    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    private String sceneId;

    private String ticket;

    public String getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(String expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QRCode)) {
            return false;
        }
        QRCode qrCode = (QRCode) o;
        return Objects.equal(expireSeconds, qrCode.expireSeconds) &&
                Objects.equal(actionName, qrCode.actionName) &&
                Objects.equal(actionInfo, qrCode.actionInfo) &&
                Objects.equal(sceneId, qrCode.sceneId) &&
                Objects.equal(ticket, qrCode.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expireSeconds, actionName, actionInfo, sceneId, ticket);
    }
}
