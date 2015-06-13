package so.glad.channel.wechat;

/**
 * @author Cartoon
 *         on 2015/4/9.
 */
public interface Const {

    interface RS_MSG_TYPE {

        String TEXT = "text";

        String IMAGE = "image";

        String VOICE = "voice";

        String VIDEO = "video";

        String MUSIC = "music";

        String NEWS = "news";
    }

    interface RQ_MSG_TYPE {
        String TEXT = "text";

        String IMAGE = "image";

        String LINK = "link";

        String LOCATION = "location";

        String VOICE = "voice";

        String EVENT = "event";
    }

    interface EVENT_TYPE {

        String SUBSCRIBE = "subscribe";

        String UNSUBSCRIBE = "unsubscribe";

        String CLICK = "CLICK";

        String SCAN = "SCAN";
    }

    interface OAUTH_SCOPE{

        String SNSAPI_BASE = "snsapi_base";

        String SNSAPI_USERINFO = "snsapi_userinfo";
    }
}
