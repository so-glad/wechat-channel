package so.glad.channel.wechat.mp.api;

import com.sun.istack.internal.Nullable;
import so.glad.channel.wechat.model.Subscriber;
import so.glad.channel.wechat.model.SubscriberGroup;
import so.glad.channel.wechat.model.SubscriberList;

import java.util.List;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public interface AccountOperation {

    SubscriberList listAccounts(@Nullable String nextOpenId);

    Subscriber getAccount(String openId);

    List<SubscriberGroup> listGroups();

//    createGroup();
//
//    updateGroup();
//
//    moveGroup();
//
//    checkGroup();
}
