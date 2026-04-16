package api.endpoints;

import api.payload.ReviewPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ReviewEndpoints - REST API endpoints for Product Review module
 * Provides all review-related operations (create, retrieve, update, delete, helpful votes)
 */
public class ReviewEndpoints {

    /**
     * Create a new product review
     */
    public static Response createReview(ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }

    /**
     * Create review and extract review ID for API chaining
     */
    public static String createReviewAndGetId(ReviewPayload payload) {
        Response response = createReview(payload);
        return response.jsonPath().getString("reviewId");
    }

    /**
     * Get all reviews for a product
     */
    public static Response getProductReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get reviews by rating
     */
    public static Response getReviewsByRating(String productId, Integer rating) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("rating", rating)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get verified purchase reviews only
     */
    public static Response getVerifiedPurchaseReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("isVerifiedPurchase", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get reviews with images/videos
     */
    public static Response getMediaReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("hasMedia", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Update review
     */
    public static Response updateReview(String reviewId, ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }

    /**
     * Delete review
     */
    public static Response deleteReview(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.DELETE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }

    /**
     * Mark review as helpful
     */
    public static Response markReviewHelpful(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"helpful\": true}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }

    /**
     * Mark review as unhelpful
     */
    public static Response markReviewUnhelpful(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"unhelpful\": true}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get reviews sorting by most helpful
     */
    public static Response getMostHelpfulReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("sortBy", "helpful")
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get reviews sorting by most recent
     */
    public static Response getMostRecentReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("sortBy", "recent")
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get review rating distribution for product
     */
    public static Response getRatingDistribution(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("getRatingDistribution", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Report inappropriate review
     */
    public static Response reportReview(String reviewId, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body("{\"reason\": \"" + reason + "\"}")
                .when()
                .post(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId) + "/report")
                .then()
                .extract()
                .response();
    }
}
