package api.endpoints;

import api.payload.PaymentCardPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * PaymentCardEndpoints - REST API endpoints for Payment Card operations
 * Provides card management operations (save, retrieve, update, delete, default)
 */
public class PaymentCardEndpoints {

    /**
     * Save a new payment card (Credit or Debit)
     */
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

    /**
     * Save payment card and extract card ID
     */
    public static String savePaymentCardAndGetId(PaymentCardPayload payload) {
        Response response = savePaymentCard(payload);
        return response.jsonPath().getString("cardId");
    }

    /**
     * Get payment card by ID
     */
    public static Response getPaymentCardById(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }

    /**
     * Get all payment cards for shopper
     */
    public static Response getAllPaymentCards() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();
    }

    /**
     * Get payment cards by type (CREDIT_CARD, DEBIT_CARD)
     */
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

    /**
     * Update payment card details
     */
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

    /**
     * Delete payment card
     */
    public static Response deletePaymentCard(String cardId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.POST_SHOPPER_BANK_CARD + "/" + cardId)
                .then()
                .extract()
                .response();
    }

    /**
     * Set card as default payment method
     */
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

    /**
     * Get default payment card
     */
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

    /**
     * Verify payment card
     */
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
