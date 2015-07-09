package so.glad.channel.wechat.springsocial;

import org.junit.Before;
import org.springframework.social.oauth2.OAuth2Operations;
import so.glad.channel.wechat.Configure;
import so.glad.channel.wechat.Const;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class OAuth2TemplateTest {

    private OAuth2Operations oAuth2Operations;
    @Before
    public void prepare(){
        oAuth2Operations = new org.springframework.social.oauth2
                .OAuth2Template("wx9dc43bc21ace677c","d81b88a0f4b4750394100a301c1dab4a",
                "",
                Configure.getProperty(Const.ConfigKey.API_URL) + Configure.getProperty(Const.ConfigKey.TOKEN_ACHIEVE));
    }

    public void testA(){
    }
}
