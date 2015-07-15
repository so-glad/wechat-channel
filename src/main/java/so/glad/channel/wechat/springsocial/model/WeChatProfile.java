package so.glad.channel.wechat.springsocial.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@SuppressWarnings("serial")
public class WeChatProfile extends WeChatObject implements Serializable {

    private String openId;
    private String nickName;
    private Sex sex;
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private List<String> privilege;
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public static enum Sex {
        UNKNOWN(0), MALE(1), FEMALE(2);

        private final int sexDigit;

        Sex(int sexDigit){
            this.sexDigit = sexDigit;
        }

        public int getSexDigit() {
            return sexDigit;
        }

        public static Sex valueOf(int sexDigit){
            for(Sex s: Sex.values()){
                if(s.getSexDigit()==sexDigit)
                    return s;
            }
            return UNKNOWN;
        }

    }


}
