package api.endpoints;

import api.payload.MerchantApprovalPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * MerchantApprovalEndpoints - REST API endpoints for Merchant Approval/Management
 * Provides admin-level operations for merchant approval, rejection, and status management
 */
public class MerchantApprovalEndpoints {

    /**
     * Get all pending merchants (status = PENDING_APPROVAL)
     */
    public static Response getPendingMerchants() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", "PENDING_APPROVAL")
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get approved merchants
     */
    public static Response getApprovedMerchants() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", "APPROVED")
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get blocked merchants
     */
    public static Response getBlockedMerchants() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", "BLOCKED")
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
    }

    /**
     * Approve merchant (PATCH request)
     */
    public static Response approveMerchant(String merchantId, MerchantApprovalPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .patch(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/approve")
                .then()
                .extract()
                .response();
    }

    /**
     * Reject merchant approval (PATCH request)
     */
    public static Response rejectMerchant(String merchantId, MerchantApprovalPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .patch(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/reject")
                .then()
                .extract()
                .response();
    }

    /**
     * Block merchant account (PATCH request)
     */
    public static Response blockMerchant(String merchantId, MerchantApprovalPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .patch(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/block")
                .then()
                .extract()
                .response();
    }

    /**
     * Unblock merchant account (PATCH request)
     */
    public static Response unblockMerchant(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .patch(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/unblock")
                .then()
                .extract()
                .response();
    }

    /**
     * Get pending merchants count
     */
    public static Response getPendingMerchantsCount() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", "PENDING_APPROVAL")
                .queryParam("count", true)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get merchant approval history
     */
    public static Response getMerchantApprovalHistory(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/approval-history")
                .then()
                .extract()
                .response();
    }

    /**
     * Delete merchant permanently (cascade delete)
     */
    public static Response deleteMerchantPermanently(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get merchants by approval criteria
     */
    public static Response getMerchantsByApprovalCriteria(String status, Integer minRating) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", status)
                .queryParam("minRating", minRating)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
    }
}
