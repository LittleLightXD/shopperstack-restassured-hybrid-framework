package api.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {

    private static final Logger logger = LogManager.getLogger(CustomLogger.class);


    public static void info(String message) {
        logger.info(message);
    }


    public static void debug(String message) {
        logger.debug(message);
    }


    public static void warn(String message) {
        logger.warn(message);
    }


    public static void error(String message) {
        logger.error(message);
    }


    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }


    public static void startTestCase(String testCaseName) {
        logger.info("===================================");
        logger.info("Test Case: " + testCaseName + " STARTED");
        logger.info("===================================");
    }


    public static void endTestCase(String testCaseName) {
        logger.info("===================================");
        logger.info("Test Case: " + testCaseName + " ENDED");
        logger.info("===================================");
    }


    public static void logAPIRequest(String httpMethod, String endpoint, String requestBody) {
        logger.info("API Request: " + httpMethod + " " + endpoint);
        logger.info("Request Body: " + requestBody);
    }


    public static void logAPIResponse(int statusCode, String responseBody) {
        logger.info("API Response Status Code: " + statusCode);
        logger.info("Response Body: " + responseBody);
    }
}

