package api.endpoints;

import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AdminReviewEndpoints {


    public static Response getAllReviews() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static Response getPendingReviewsForModeration() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", "PENDING_MODERATION")
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static Response getFlaggedReviews() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("isFlagged", true)
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static Response deleteReviewByAdmin(String reviewId, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"reason\": \"" + reason + "\"}")
                .when()
                .delete(Routes.DELETE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }


    public static Response approveFlaggedReview(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"status\": \"APPROVED\"}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId) + "/approve")
                .then()
                .extract()
                .response();
    }


    public static Response rejectFlaggedReview(String reviewId, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"status\": \"REJECTED\", \"reason\": \"" + reason + "\"}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId) + "/reject")
                .then()
                .extract()
                .response();
    }


    public static Response getReviewsByMerchantAdmin(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("merchantId", merchantId)
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static Response getReviewStatistics() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("getStatistics", true)
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static Response getLowRatingReviews(Integer maxRating) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("maxRating", maxRating)
                .when()
                .get(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }
}

