package so.glad.channel.wechat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import so.glad.channel.wechat.Const;
import so.glad.channel.wechat.model.AccessToken;
import so.glad.channel.wechat.mp.api.AccessTokenUpdate;
import so.glad.channel.wechat.mp.api.BasicOperation;

import java.util.concurrent.*;

/**
 * @author palmtale
 *         on 15/7/9.
 */

public class AccessTokenUpdateTask {

    private Logger logger = LoggerFactory.getLogger(AccessTokenUpdateTask.class);

    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new CustomizableThreadFactory("TimeUpdateAccessToken"));

    private long defaultDelay = 10;

    private long preSecondsBeforeDo = 60;

    private BasicOperation basicOperation ;

    private AccessTokenUpdate accessTokenUpdate;

    private Environment config;

    public void start(){
        doTask(defaultDelay);
    }

    private void doTask(long delaySecond){
        ScheduledFuture<AccessToken> s = executorService.schedule(() ->
                basicOperation.archieveAccessToken(config.getProperty(Const.AUTH_PARAM.APP_KEY), config.getProperty(Const.AUTH_PARAM.APP_SECRET)),
                delaySecond, TimeUnit.SECONDS);
        long nextDelay = defaultDelay;
        AccessToken accessToken = null;
        try {
            accessToken = s.get();
            nextDelay = accessToken.getExpiresIn();
            if(nextDelay > preSecondsBeforeDo) {
                nextDelay = nextDelay - preSecondsBeforeDo;
            }
            accessTokenUpdate.updateAccessToken(accessToken.getToken());
        } catch (Exception e) {
            logger.warn("Wechat accessToken update task failed. ", e);
        }
        doTask(nextDelay);
    }

    public void setConfig(Environment config) {
        this.config = config;
    }

    public void setBasicOperation(BasicOperation basicOperation) {
        this.basicOperation = basicOperation;
    }

    public void setAccessTokenUpdate(AccessTokenUpdate accessTokenUpdate) {
        this.accessTokenUpdate = accessTokenUpdate;
    }
}
