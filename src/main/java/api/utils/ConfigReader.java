package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class to read configuration from properties file
 * Implements Singleton pattern for single instance
 */
public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    /**
     * Get singleton instance of ConfigReader
     */
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    /**
     * Load properties from config.properties file
     */
    private void loadProperties() {
        try {
            String configPath = "src/main/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get property value by key
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value with default value if key doesn't exist
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get Base URL
     */
    public String getBaseURL() {
        return getProperty("baseURL");
    }

    /**
     * Get Environment
     */
    public String getEnvironment() {
        return getProperty("environment", "dev");
    }

    /**
     * Get Admin Username
     */
    public String getAdminUsername() {
        return getProperty("admin_username");
    }

    /**
     * Get Admin Password
     */
    public String getAdminPassword() {
        return getProperty("admin_password");
    }

    /**
     * Get Request Timeout
     */
    public int getRequestTimeout() {
        return Integer.parseInt(getProperty("request_timeout", "10"));
    }

    /**
     * Get Response Timeout
     */
    public int getResponseTimeout() {
        return Integer.parseInt(getProperty("response_timeout", "15"));
    }

    /**
     * Check if logging is enabled
     */
    public boolean isLoggingEnabled() {
        return Boolean.parseBoolean(getProperty("enable_logging", "true"));
    }

    /**
     * Get Extent Report Path
     */
    public String getExtentReportPath() {
        return getProperty("extent_report_path", "./test-output/extent-reports/");
    }
}
