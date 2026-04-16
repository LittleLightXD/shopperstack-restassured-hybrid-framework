package api.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import api.utils.ConfigReader;
import api.utils.TokenManager;
import api.logging.CustomLogger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

/**
 * BaseTest class - base setup for all test classes
 * Handles common setup, teardown, and reporting
 */
public class BaseTest {

    protected ExtentReports extentReports;
    protected ExtentTest test;
    protected ConfigReader config;

    /**
     * Setup before running tests
     */
    @BeforeClass
    public void setUp() {
        config = ConfigReader.getInstance();
        CustomLogger.info("========== Test Setup Started ==========");
        CustomLogger.info("Base URL: " + config.getBaseURL());
        CustomLogger.info("Environment: " + config.getEnvironment());
        
        initializeExtentReports();
        
        // Generate token for authenticated requests (disabled by default as endpoint may not exist)
        // Uncomment the line below after setting up login endpoint
        // TokenManager.generateToken();
        
        CustomLogger.info("========== Test Setup Completed ==========");
    }

    /**
     * Initialize Extent Reports
     */
    public void initializeExtentReports() {
        String reportPath = config.getExtentReportPath();
        
        // Create directory if it doesn't exist
        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        String reportFile = reportPath + "extent-report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
        sparkReporter.config().setReportName("ShopperStack API Test Report");
        sparkReporter.config().setDocumentTitle("API Test Execution Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Test Environment", config.getEnvironment());
        extentReports.setSystemInfo("Base URL", config.getBaseURL());
        extentReports.setSystemInfo("Test Framework", "REST Assured + TestNG");
    }

    /**
     * Cleanup after running tests
     */
    @AfterClass
    public void tearDown() {
        CustomLogger.info("========== Test Teardown Started ==========");
        
        // Clear token after tests
        TokenManager.clearToken();
        
        // Flush extent reports
        if (extentReports != null) {
            extentReports.flush();
            CustomLogger.info("✓ Test Report Generated");
        }
        
        CustomLogger.info("========== Test Teardown Completed ==========");
    }

    /**
     * Get config reader instance
     */
    public ConfigReader getConfig() {
        return config;
    }

    /**
     * Get extent test instance
     */
    public ExtentTest getExtentTest() {
        return test;
    }
}
