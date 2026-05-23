package api.endpoints;

import api.payload.PaymentCardPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PaymentCardEndpoints {


    public static Response savePaymentCard(PaymentCardPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();
    }


    public static String savePaymentCardAndGetId(PaymentCardPayload payload) {
        Response response = savePaymentCard(payload);
        return response.jsonPath().getString("cardId");
    }


    public static Response getPaymentCardById(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }


    public static Response getAllPaymentCards() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();
    }


    public static Response getPaymentCardsByType(String cardType) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("cardType", cardType)
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();
    }


    public static Response updatePaymentCard(String cardId, PaymentCardPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }


    public static Response deletePaymentCard(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }


    public static Response setCardAsDefault(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"isDefault\": true}")
                .when()
                .put(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId + "/default")
                .then()
                .extract()
                .response();
    }


    public static Response getDefaultPaymentCard() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("isDefault", true)
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();
    }


    public static Response verifyPaymentCard(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .post(Routes.VERIFY_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }
}

