package api.endpoints;

import api.payload.WalletPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * WalletEndpoints - REST API endpoints for Wallet operations
 * Provides wallet balance management and transaction processing
 */
public class WalletEndpoints {

    /**
     * Get wallet balance for shopper
     */
    public static Response getWalletBalance(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get("/wallet/" + shopperId + "/balance")
                .then()
                .extract()
                .response();
    }

    /**
     * Add funds to wallet
     */
    public static Response addFundsToWallet(String shopperId, Double amount) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"amount\": " + amount + ", \"transactionType\": \"ADD_FUNDS\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }

    /**
     * Deduct funds from wallet
     */
    public static Response deductFundsFromWallet(String shopperId, Double amount, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"amount\": " + amount + ", \"transactionType\": \"DEBIT\", \"reason\": \"" + reason + "\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }

    /**
     * Process wallet transaction
     */
    public static Response processWalletTransaction(String shopperId, WalletPayload.WalletTransaction transaction) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(transaction)
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }

    /**
     * Get wallet transaction history
     */
    public static Response getWalletTransactionHistory(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get("/wallet/" + shopperId + "/transactions")
                .then()
                .extract()
                .response();
    }

    /**
     * Refund to wallet
     */
    public static Response refundToWallet(String shopperId, Double amount, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"amount\": " + amount + ", \"transactionType\": \"REFUND\", \"orderId\": \"" + orderId + "\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }

    /**
     * Get wallet details
     */
    public static Response getWalletDetails(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get("/wallet/" + shopperId)
                .then()
                .extract()
                .response();
    }

    /**
     * Withdraw funds from wallet
     */
    public static Response withdrawFundsFromWallet(String shopperId, Double amount) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"amount\": " + amount + ", \"transactionType\": \"WITHDRAWAL\"}")
                .when()
                .post("/wallet/" + shopperId + "/withdraw")
                .then()
                .extract()
                .response();
    }
}
