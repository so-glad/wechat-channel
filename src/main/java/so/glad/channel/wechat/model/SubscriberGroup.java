package so.glad.channel.wechat.model;

import com.google.common.base.Objects;

/**
 * @author palmtale
 *         on 15/6/15.
 */
public class SubscriberGroup {

    private String id;

    private String name;

    private int count;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriberGroup)) {
            return false;
        }
        SubscriberGroup that = (SubscriberGroup) o;
        return Objects.equal(count, that.count) &&
                Objects.equal(id, that.id) &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, count);
    }
}

