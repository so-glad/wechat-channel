package so.glad.channel.wechat.model;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class Subscriber {

    /**
     * The flag whether user subscribe , it means user did not subscribe in the case of 0, cannot fetch other fields。
     */
    private Integer subscribe;

    /**
     * Subscriber id for an office account
     */
    private String officeId;

    private String nickname;

    private Integer gender;

    private String city;

    private String country;

    private String province;

    private String language;

    private String avatarUrl;

    private Date subscribeTime;

    private String privilege;

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subscriber)) {
            return false;
        }
        Subscriber that = (Subscriber) o;
        return Objects.equal(subscribe, that.subscribe) &&
                Objects.equal(officeId, that.officeId) &&
                Objects.equal(nickname, that.nickname) &&
                Objects.equal(gender, that.gender) &&
                Objects.equal(city, that.city) &&
                Objects.equal(country, that.country) &&
                Objects.equal(province, that.province) &&
                Objects.equal(language, that.language) &&
                Objects.equal(avatarUrl, that.avatarUrl) &&
                Objects.equal(subscribeTime, that.subscribeTime) &&
                Objects.equal(privilege, that.privilege);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subscribe, officeId, nickname,
                gender, city, country, province, language,
                avatarUrl, subscribeTime, privilege);
    }
}
