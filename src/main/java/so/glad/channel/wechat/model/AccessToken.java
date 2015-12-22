package so.glad.channel.wechat.model;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class AccessToken {

    private String token;

    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccessToken)) {
            return false;
        }
        AccessToken that = (AccessToken) o;
        return Objects.equal(expiresIn, that.expiresIn) &&
                Objects.equal(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token, expiresIn);
    }
}