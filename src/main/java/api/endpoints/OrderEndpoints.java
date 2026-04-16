package api.endpoints;

import api.payload.OrderPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * OrderEndpoints - REST API endpoints for Order module
 * Provides all order-related operations (create, retrieve, update, cancel, track)
 */
public class OrderEndpoints {

    /**
     * Create a new order
     */
    public static Response createOrder(String shopperId, OrderPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(Routes.POST_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Create order and extract order ID for API chaining
     */
    public static String createOrderAndGetId(String shopperId, OrderPayload payload) {
        Response response = createOrder(shopperId, payload);
        return response.jsonPath().getString("orderId");
    }

    /**
     * Get all orders for a shopper
     */
    public static Response getShopperOrders(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get order by ID
     */
    public static Response getOrderById(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }

    /**
     * Update order details
     */
    public static Response updateOrder(String shopperId, String orderId, OrderPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }

    /**
     * Cancel order
     */
    public static Response cancelOrder(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"orderStatus\": \"CANCELLED\"}")
                .when()
                .put(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get order invoice
     */
    public static Response getOrderInvoice(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_ORDER_INVOICE.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get orders by status
     */
    public static Response getOrdersByStatus(String shopperId, String status) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", status)
                .when()
                .get(Routes.GET_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get orders by payment status
     */
    public static Response getOrdersByPaymentStatus(String shopperId, String paymentStatus) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("paymentStatus", paymentStatus)
                .when()
                .get(Routes.GET_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Track order by tracking number
     */
    public static Response trackOrder(String shopperId, String trackingNumber) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("trackingNumber", trackingNumber)
                .when()
                .get(Routes.GET_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Generate order invoice PDF
     */
    public static Response generateInvoicePDF(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .accept("application/pdf")
                .when()
                .get(Routes.GET_ORDER_INVOICE.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }

    /**
     * Delete order
     */
    public static Response deleteOrder(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }
}
