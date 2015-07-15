package so.glad.channel.wechat.springsocial.model;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author palmtale
 *         on 15/7/11.
 */
@SuppressWarnings("serial")
public class WeChatModule extends SimpleModule {

    public WeChatModule() {
        super("WeChatModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(WeChatProfile.class, WeChatProfileMixin.class);
        context.setMixInAnnotations(WeChatOkMessage.class, WeChatOkMessageMixin.class);
    }
}
