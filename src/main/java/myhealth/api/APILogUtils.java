package myhealth.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログ出力クラス
 *
 */
public class APILogUtils {
    private final static Logger logger = LoggerFactory.getLogger(APILogUtils.class);
    public static void info(String msg) {
        logger.info(msg);
    }
    public static void warn(String msg) {
        logger.warn(msg);
    }
    public static void error(String msg) {
        logger.error(msg);
    }
    public static void debug(String msg) {
        logger.debug(msg);
    }
    public static void trace(String msg) {
        logger.trace(msg);
    }
}