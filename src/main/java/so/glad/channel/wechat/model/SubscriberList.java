package so.glad.channel.wechat.model;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class SubscriberList {

    private Long total;

    private Long count;

    private List<String> openIdList = Lists.newArrayList();

    private String nextOpenId;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<String> getOpenIdList() {
        return openIdList;
    }

    public void setOpenIdList(List<String> openIdList) {
        this.openIdList = openIdList;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriberList)) {
            return false;
        }
        SubscriberList that = (SubscriberList) o;
        return Objects.equal(total, that.total) &&
                Objects.equal(count, that.count) &&
                Objects.equal(openIdList, that.openIdList) &&
                Objects.equal(nextOpenId, that.nextOpenId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(total, count, openIdList, nextOpenId);
    }
}
