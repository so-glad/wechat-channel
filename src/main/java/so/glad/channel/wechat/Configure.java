package so.glad.channel.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author palmtale
 *         on 15/6/16.
 */
public class Configure {

    private final static Properties confProps = new Properties();

    static {
        Logger log = LoggerFactory.getLogger(Configure.class);
        try {
            confProps.load(Configure.class.getResourceAsStream("/wechat/config.properties"));
        }
        catch (Exception ex) {
            log.warn("Load Edrive Config file failed.", ex);
        }
    }

    public static String getProperty(String entry) {
        return confProps.getProperty(entry);
    }
}
