package so.glad.channel.wechat;

/**
 * @author Cartoon
 *         on 2015/4/9.
 */
public interface Const {

    String DEFAULT_ENCODING = "UTF-8";

    interface AUTH_PARAM{

        String APP_KEY = "appid";

        String APP_SECRET = "secret";

        String RESPONSE_TYPE = "response_type";

        String REDIRECT_URL = "redirect_uri";

        String SCOPE = "scope";

        String STATE = "state";

        String GRANT_TYPE = "grant_type";

        String CODE = "code";

        String ACCESS_TOKEN = "access_token";

        String REFRESH_TOKEN = "refresh_token";

        String EXPIRES_IN = "expires_in";

        String OPEN_ID = "openid";

        String UNION_ID = "UNION_ID";
    }

    interface MESSAGE_KEY {
        String MESSAGE_TYPE = "MsgType";
        String CONTENT = "Content";
    }

    interface ConfigKey {
        String API_URL = "url.api";
        String OPEN_URL = "url.open";
        String CONNECT_QRCODE = "connect.qrcode";
        String OAUTH_TOKEN_ACHIEVE = "oauth.token.achieve";
        String OAUTH_TOKEN_REFRESH = "oauth.token.refresh";
        String OAUTH_TOKEN_VALID = "oauth.token.valid";

        String TOKEN_ACHIEVE = "token.achieve";
        String MENU_CREATE = "menu.create";
        String MENU_QUERY = "menu.get";
        String MENU_DELETE = "menu.delete";

    }

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

    interface QR_ACTION_NAME {

        String QR_SCENE = "QR_SCENE";

        String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    }
}
