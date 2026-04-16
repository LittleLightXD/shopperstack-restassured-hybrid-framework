package api.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CustomLogger class for centralized logging
 */
public class CustomLogger {

    private static final Logger logger = LogManager.getLogger(CustomLogger.class);

    /**
     * Log info level message
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log debug level message
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log warning level message
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log error level message
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log error with exception
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * Log test case start
     */
    public static void startTestCase(String testCaseName) {
        logger.info("===================================");
        logger.info("Test Case: " + testCaseName + " STARTED");
        logger.info("===================================");
    }

    /**
     * Log test case end
     */
    public static void endTestCase(String testCaseName) {
        logger.info("===================================");
        logger.info("Test Case: " + testCaseName + " ENDED");
        logger.info("===================================");
    }

    /**
     * Log API request details
     */
    public static void logAPIRequest(String httpMethod, String endpoint, String requestBody) {
        logger.info("API Request: " + httpMethod + " " + endpoint);
        logger.info("Request Body: " + requestBody);
    }

    /**
     * Log API response details
     */
    public static void logAPIResponse(int statusCode, String responseBody) {
        logger.info("API Response Status Code: " + statusCode);
        logger.info("Response Body: " + responseBody);
    }
}
