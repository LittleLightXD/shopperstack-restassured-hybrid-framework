package api.endpoints;

import api.payload.OrderManagementPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * OrderManagementEndpoints - REST API endpoints for Order Management
 */
public class OrderManagementEndpoints {

    private static final String ORDER_BASE = "/api/orders";

    public static Response getAllOrders() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(ORDER_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response getOrderById(String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(ORDER_BASE + "/" + orderId)
                .then()
                .extract()
                .response();
    }

    public static Response createOrder(OrderManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(ORDER_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response updateOrderStatus(String orderId, OrderManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(ORDER_BASE + "/" + orderId + "/status")
                .then()
                .extract()
                .response();
    }

    public static Response cancelOrder(String orderId, OrderManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(ORDER_BASE + "/" + orderId + "/cancel")
                .then()
                .extract()
                .response();
    }

    public static Response getOrdersByCustomer(String customerId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("customerId", customerId)
                .when()
                .get(ORDER_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response getOrdersByDateRange(String startDate, String endDate) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(ORDER_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response getOrderItems(String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(ORDER_BASE + "/" + orderId + "/items")
                .then()
                .extract()
                .response();
    }

    public static Response addOrderItem(String orderId, OrderManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(ORDER_BASE + "/" + orderId + "/items")
                .then()
                .extract()
                .response();
    }
}
