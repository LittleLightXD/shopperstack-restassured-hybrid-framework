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

public class BaseTest {

    protected ExtentReports extentReports;
    protected ExtentTest test;
    protected ConfigReader config;


    @BeforeClass
    public void setUp() {
        config = ConfigReader.getInstance();
        CustomLogger.info("========== Test Setup Started ==========");
        CustomLogger.info("Base URL: " + config.getBaseURL());
        CustomLogger.info("Environment: " + config.getEnvironment());

        initializeExtentReports();





        CustomLogger.info("========== Test Setup Completed ==========");
    }


    public void initializeExtentReports() {
        String reportPath = config.getExtentReportPath();


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


    @AfterClass
    public void tearDown() {
        CustomLogger.info("========== Test Teardown Started ==========");


        TokenManager.clearToken();


        if (extentReports != null) {
            extentReports.flush();
            CustomLogger.info("✓ Test Report Generated");
        }

        CustomLogger.info("========== Test Teardown Completed ==========");
    }


    public ConfigReader getConfig() {
        return config;
    }


    public ExtentTest getExtentTest() {
        return test;
    }
}

