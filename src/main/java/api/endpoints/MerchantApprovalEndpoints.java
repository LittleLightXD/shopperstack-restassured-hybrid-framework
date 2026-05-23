package api.endpoints;

import api.payload.MerchantApprovalPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MerchantApprovalEndpoints {


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


    public static Response unblockMerchant(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .patch(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/unblock")
                .then()
                .extract()
                .response();
    }


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


    public static Response getMerchantApprovalHistory(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId) + "/approval-history")
                .then()
                .extract()
                .response();
    }


    public static Response deleteMerchantPermanently(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.UPDATE_MERCHANT.replace("{merchantId}", merchantId))
                .then()
                .extract()
                .response();
    }


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

