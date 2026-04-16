package api.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * ReportingDataProvider - Data provider for reporting and analytics test cases
 */
public class ReportingDataProvider {

    @DataProvider(name = "reportData")
    public Object[][] getReportData() {
        return new Object[][] {
                {"SALES", "2024-01-01", "2024-01-31"},
                {"REVENUE", "2024-02-01", "2024-02-29"},
                {"MERCHANT_PERFORMANCE", "2024-03-01", "2024-03-31"},
                {"ORDER_ANALYTICS", "2024-04-01", "2024-04-30"}
        };
    }
}
