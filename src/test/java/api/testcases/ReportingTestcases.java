package api.testcases;

import api.base.BaseTest;
import api.dataproviders.ReportingDataProvider;
import api.endpoints.ReportingEndpoints;
import api.logging.CustomLogger;
import api.payload.ReportingPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ReportingTestcases - Test scenarios for Reporting and Analytics module
 * Covers report generation, analytics, dashboards, and data export
 */
public class ReportingTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();

    // ==================== POSITIVE TEST CASES ====================

    @Test(priority = 1, description = "Get sales dashboard")
    public void getSalesDashboardTest() {
        logger.startTestCase("getSalesDashboardTest");
        
        Response response = ReportingEndpoints.getSalesDashboard();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getSalesDashboardTest");
    }

    @Test(priority = 2, description = "Get revenue report")
    public void getRevenueReportTest() {
        logger.startTestCase("getRevenueReportTest");
        
        Response response = ReportingEndpoints.getRevenueReport("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getRevenueReportTest");
    }

    @Test(priority = 3, description = "Get sales by category report")
    public void getSalesByCategoryReportTest() {
        logger.startTestCase("getSalesByCategoryReportTest");
        
        Response response = ReportingEndpoints.getSalesByCategoryReport("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getSalesByCategoryReportTest");
    }

    @Test(priority = 4, description = "Get top products report")
    public void getTopProductsReportTest() {
        logger.startTestCase("getTopProductsReportTest");
        
        Response response = ReportingEndpoints.getTopProductsReport(10);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getTopProductsReportTest");
    }

    @Test(priority = 5, description = "Get merchant performance report")
    public void getMerchantPerformanceReportTest() {
        logger.startTestCase("getMerchantPerformanceReportTest");
        
        Response response = ReportingEndpoints.getMerchantPerformanceReport("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getMerchantPerformanceReportTest");
    }

    @Test(priority = 6, description = "Get order analytics")
    public void getOrderAnalyticsTest() {
        logger.startTestCase("getOrderAnalyticsTest");
        
        Response response = ReportingEndpoints.getOrderAnalytics("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getOrderAnalyticsTest");
    }

    @Test(priority = 7, description = "Export sales report to CSV")
    public void exportSalesReportCsvTest() {
        logger.startTestCase("exportSalesReportCsvTest");
        
        Response response = ReportingEndpoints.exportSalesReportCsv("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("exportSalesReportCsvTest");
    }

    @Test(priority = 8, description = "Export sales report to PDF")
    public void exportSalesReportPdfTest() {
        logger.startTestCase("exportSalesReportPdfTest");
        
        Response response = ReportingEndpoints.exportSalesReportPdf("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("exportSalesReportPdfTest");
    }

    @Test(priority = 9, description = "Get customer analytics")
    public void getCustomerAnalyticsTest() {
        logger.startTestCase("getCustomerAnalyticsTest");
        
        Response response = ReportingEndpoints.getCustomerAnalytics("2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getCustomerAnalyticsTest");
    }

    // ==================== DATA-DRIVEN TEST CASES ====================

    @Test(priority = 10, dataProvider = "reportData", dataProviderClass = ReportingDataProvider.class,
            description = "Generate report with data provider")
    public void generateReportDataDrivenTest(String reportType, String startDate, String endDate) {
        logger.startTestCase("generateReportDataDrivenTest");
        
        ReportingPayload payload = new ReportingPayload();
        payload.setReportType(reportType);
        payload.setStartDate(startDate);
        payload.setEndDate(endDate);

        Response response = ReportingEndpoints.generateReport(payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("generateReportDataDrivenTest");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test(priority = 20, description = "Get report with invalid date range")
    public void getReportWithInvalidDateRangeTest() {
        logger.startTestCase("getReportWithInvalidDateRangeTest");
        
        Response response = ReportingEndpoints.getRevenueReport("2024-12-31", "2024-01-01");
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("getReportWithInvalidDateRangeTest");
    }

    @Test(priority = 21, description = "Export report with invalid format")
    public void exportReportWithInvalidFormatTest() {
        logger.startTestCase("exportReportWithInvalidFormatTest");
        
        Response response = ReportingEndpoints.exportSalesReport("2024-01-01", "2024-12-31", "INVALID_FORMAT");
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("exportReportWithInvalidFormatTest");
    }

    @Test(priority = 22, description = "Get top products report with invalid limit")
    public void getTopProductsReportWithInvalidLimitTest() {
        logger.startTestCase("getTopProductsReportWithInvalidLimitTest");
        
        Response response = ReportingEndpoints.getTopProductsReport(-5);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("getTopProductsReportWithInvalidLimitTest");
    }

    @Test(priority = 23, description = "Generate report without required fields")
    public void generateReportWithoutRequiredFieldsTest() {
        logger.startTestCase("generateReportWithoutRequiredFieldsTest");
        
        ReportingPayload payload = new ReportingPayload();
        payload.setStartDate("2024-01-01");

        Response response = ReportingEndpoints.generateReport(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("generateReportWithoutRequiredFieldsTest");
    }

    @Test(priority = 24, description = "Get merchant performance report for non-existent merchant")
    public void getReportForNonExistentMerchantTest() {
        logger.startTestCase("getReportForNonExistentMerchantTest");
        
        Response response = ReportingEndpoints.getMerchantPerformanceReportByMerchant("non-existent-merchant", 
                                                                                       "2024-01-01", "2024-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("getReportForNonExistentMerchantTest");
    }

    @Test(priority = 25, description = "Unauthorized report access")
    public void unauthorizedReportAccessTest() {
        logger.startTestCase("unauthorizedReportAccessTest");
        
        Response response = ReportingEndpoints.getSalesDashboard();
        
        Assert.assertEquals(response.getStatusCode(), 401);
        
        logger.endTestCase("unauthorizedReportAccessTest");
    }

    @Test(priority = 26, description = "Get analytics for future dates")
    public void getAnalyticsForFutureDatesTest() {
        logger.startTestCase("getAnalyticsForFutureDatesTest");
        
        Response response = ReportingEndpoints.getOrderAnalytics("2025-01-01", "2025-12-31");
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("getAnalyticsForFutureDatesTest");
    }

    @Test(priority = 27, description = "Export large report")
    public void exportLargeReportTest() {
        logger.startTestCase("exportLargeReportTest");
        
        Response response = ReportingEndpoints.exportSalesReportCsv("2020-01-01", "2024-12-31");
        
        // Should timeout or return success with large file
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 408);
        
        logger.endTestCase("exportLargeReportTest");
    }
}
