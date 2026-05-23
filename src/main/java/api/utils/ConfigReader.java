package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }


    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }


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


    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


    public String getBaseURL() {
        return getProperty("baseURL");
    }


    public String getEnvironment() {
        return getProperty("environment", "dev");
    }


    public String getAdminUsername() {
        return getProperty("admin_username");
    }


    public String getAdminPassword() {
        return getProperty("admin_password");
    }


    public int getRequestTimeout() {
        return Integer.parseInt(getProperty("request_timeout", "10"));
    }


    public int getResponseTimeout() {
        return Integer.parseInt(getProperty("response_timeout", "15"));
    }


    public boolean isLoggingEnabled() {
        return Boolean.parseBoolean(getProperty("enable_logging", "true"));
    }


    public String getExtentReportPath() {
        return getProperty("extent_report_path", "./test-output/extent-reports/");
    }
}

