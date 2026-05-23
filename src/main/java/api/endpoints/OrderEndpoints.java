package api.endpoints;

import api.payload.OrderPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderEndpoints {


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


    public static String createOrderAndGetId(String shopperId, OrderPayload payload) {
        Response response = createOrder(shopperId, payload);
        return response.jsonPath().getString("orderId");
    }


    public static Response getShopperOrders(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


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

