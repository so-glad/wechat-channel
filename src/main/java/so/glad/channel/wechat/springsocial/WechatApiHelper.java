package so.glad.channel.wechat.springsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import so.glad.channel.wechat.api.WeChat;

/**
 * @author palmtale
 *         on 15/7/11.
 */
public class WechatApiHelper implements ApiHelper<WeChat> {

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserIdSource userIdSource;

    private WechatApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    public WeChat getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for Wechat");
        }

        Connection<WeChat> connection = usersConnectionRepository
                .createConnectionRepository(userIdSource.getUserId())
                .findPrimaryConnection(WeChat.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default WechatTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }

    private final static Logger logger = LoggerFactory.getLogger(WechatApiHelper.class);
}
