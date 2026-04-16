package api.endpoints;

import api.payload.ReportingPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ReportingEndpoints - REST API endpoints for Reporting and Analytics
 */
public class ReportingEndpoints {

    private static final String REPORTING_BASE = "/api/reports";
    private static final String DASHBOARD_BASE = "/api/dashboard";

    public static Response getSalesDashboard() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(DASHBOARD_BASE + "/sales")
                .then()
                .extract()
                .response();
    }

    public static Response getRevenueReport(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/revenue")
                .then()
                .extract()
                .response();
    }

    public static Response getSalesByCategoryReport(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/sales-by-category")
                .then()
                .extract()
                .response();
    }

    public static Response getTopProductsReport(int limit) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("limit", limit)
                .when()
                .get(REPORTING_BASE + "/top-products")
                .then()
                .extract()
                .response();
    }

    public static Response getMerchantPerformanceReport(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/merchant-performance")
                .then()
                .extract()
                .response();
    }

    public static Response getMerchantPerformanceReportByMerchant(String merchantId, String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("merchantId", merchantId)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/merchant-performance")
                .then()
                .extract()
                .response();
    }

    public static Response getOrderAnalytics(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/order-analytics")
                .then()
                .extract()
                .response();
    }

    public static Response getCustomerAnalytics(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(REPORTING_BASE + "/customer-analytics")
                .then()
                .extract()
                .response();
    }

    public static Response exportSalesReportCsv(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("format", "csv")
                .when()
                .get(REPORTING_BASE + "/sales/export")
                .then()
                .extract()
                .response();
    }

    public static Response exportSalesReportPdf(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("format", "pdf")
                .when()
                .get(REPORTING_BASE + "/sales/export")
                .then()
                .extract()
                .response();
    }

    public static Response exportSalesReport(String startDate, String endDate, String format) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("format", format)
                .when()
                .get(REPORTING_BASE + "/sales/export")
                .then()
                .extract()
                .response();
    }

    public static Response generateReport(ReportingPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(REPORTING_BASE + "/generate")
                .then()
                .extract()
                .response();
    }
}
