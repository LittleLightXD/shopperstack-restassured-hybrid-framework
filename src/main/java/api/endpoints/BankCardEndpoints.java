package api.endpoints;

import api.payload.BankCardPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * BankCardEndpoints - REST API endpoints for Bank Account operations
 * Provides bank account management (create, verify, update balance, transactions)
 */
public class BankCardEndpoints {

    /**
     * Create a new bank account
     */
    public static Response createBankAccount(BankCardPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }

    /**
     * Create bank account and extract account ID
     */
    public static String createBankAccountAndGetId(BankCardPayload payload) {
        Response response = createBankAccount(payload);
        return response.jsonPath().getString("bankAccountId");
    }

    /**
     * Get bank account by ID
     */
    public static Response getBankAccountById(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }

    /**
     * Get all bank accounts
     */
    public static Response getAllBankAccounts() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }

    /**
     * Verify bank account
     */
    public static Response verifyBankAccount(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .post(Routes.LOGIN_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId + "/verify")
                .then()
                .extract()
                .response();
    }

    /**
     * Update bank account balance
     */
    public static Response updateBankAccountBalance(String bankAccountId, Double newBalance) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"balance\": " + newBalance + "}")
                .when()
                .put(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }

    /**
     * Create bank transaction (Debit/Credit)
     */
    public static Response createBankTransaction(String bankAccountId, BankCardPayload.TransactionDetails transaction) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(transaction)
                .when()
                .post(Routes.SHOPPER_BANK_CARDS_TRANSACTION)
                .then()
                .extract()
                .response();
    }

    /**
     * Get bank account transaction history
     */
    public static Response getBankAccountTransactions(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("bankAccountId", bankAccountId)
                .when()
                .get(Routes.SHOPPER_BANK_CARDS_TRANSACTION)
                .then()
                .extract()
                .response();
    }

    /**
     * Delete bank account
     */
    public static Response deleteBankAccount(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }

    /**
     * Set bank account as default
     */
    public static Response setBankAccountAsDefault(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"isDefault\": true}")
                .when()
                .put(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId + "/default")
                .then()
                .extract()
                .response();
    }
}
