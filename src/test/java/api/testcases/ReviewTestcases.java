package api.testcases;

import api.base.BaseTest;
import api.dataproviders.ReviewDataProvider;
import api.endpoints.ReviewEndpoints;
import api.logging.CustomLogger;
import api.payload.ReviewPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReviewTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();



    @Test(priority = 1, description = "Create product review with valid data")
    public void createReviewWithValidDataTest() {
        logger.startTestCase("createReviewWithValidDataTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setTitle("Excellent Product!");
        payload.setComment("Great quality and fast delivery. Highly recommended!");
        payload.setIsVerifiedPurchase(true);

        Response response = ReviewEndpoints.createReview(payload);
        logger.logAPIResponse(response.getStatusCode(), response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("reviewId"));

        logger.endTestCase("createReviewWithValidDataTest");
    }

    @Test(priority = 2, description = "Get all reviews for product")
    public void getProductReviewsTest() {
        logger.startTestCase("getProductReviewsTest");

        Response response = ReviewEndpoints.getProductReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getProductReviewsTest");
    }

    @Test(priority = 3, description = "Get reviews by rating")
    public void getReviewsByRatingTest() {
        logger.startTestCase("getReviewsByRatingTest");

        Response response = ReviewEndpoints.getReviewsByRating("product-123456", 5);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getReviewsByRatingTest");
    }

    @Test(priority = 4, description = "Get verified purchase reviews")
    public void getVerifiedPurchaseReviewsTest() {
        logger.startTestCase("getVerifiedPurchaseReviewsTest");

        Response response = ReviewEndpoints.getVerifiedPurchaseReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getVerifiedPurchaseReviewsTest");
    }

    @Test(priority = 5, description = "Update review")
    public void updateReviewTest() {
        logger.startTestCase("updateReviewTest");

        ReviewPayload createPayload = new ReviewPayload();
        createPayload.setProductId("product-123456");
        createPayload.setShopperId("shopper-123456");
        createPayload.setRating(5);
        createPayload.setComment("Initial comment");

        String reviewId = ReviewEndpoints.createReviewAndGetId(createPayload);

        ReviewPayload updatePayload = new ReviewPayload();
        updatePayload.setComment("Updated comment with more details");
        updatePayload.setRating(4);

        Response response = ReviewEndpoints.updateReview(reviewId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("updateReviewTest");
    }

    @Test(priority = 6, description = "Get most helpful reviews")
    public void getMostHelpfulReviewsTest() {
        logger.startTestCase("getMostHelpfulReviewsTest");

        Response response = ReviewEndpoints.getMostHelpfulReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getMostHelpfulReviewsTest");
    }

    @Test(priority = 7, description = "Get most recent reviews")
    public void getMostRecentReviewsTest() {
        logger.startTestCase("getMostRecentReviewsTest");

        Response response = ReviewEndpoints.getMostRecentReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getMostRecentReviewsTest");
    }

    @Test(priority = 8, description = "Get rating distribution")
    public void getRatingDistributionTest() {
        logger.startTestCase("getRatingDistributionTest");

        Response response = ReviewEndpoints.getRatingDistribution("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getRatingDistributionTest");
    }



    @Test(priority = 10, dataProvider = "validReviewData", dataProviderClass = ReviewDataProvider.class,
            description = "Create review with data provider")
    public void createReviewDataDrivenTest(String productId, String shopperId, Integer rating, String comment) {
        logger.startTestCase("createReviewDataDrivenTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId(productId);
        payload.setShopperId(shopperId);
        payload.setRating(rating);
        payload.setComment(comment);

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createReviewDataDrivenTest");
    }



    @Test(priority = 20, description = "Create review with invalid rating (too high)")
    public void createReviewWithInvalidRatingTest() {
        logger.startTestCase("createReviewWithInvalidRatingTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(10);
        payload.setComment("Invalid rating");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createReviewWithInvalidRatingTest");
    }

    @Test(priority = 21, description = "Create review with missing product ID")
    public void createReviewWithMissingProductIdTest() {
        logger.startTestCase("createReviewWithMissingProductIdTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Missing product ID");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createReviewWithMissingProductIdTest");
    }

    @Test(priority = 22, description = "Create review with empty comment")
    public void createReviewWithEmptyCommentTest() {
        logger.startTestCase("createReviewWithEmptyCommentTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createReviewWithEmptyCommentTest");
    }

    @Test(priority = 23, description = "Create review with negative rating")
    public void createReviewWithNegativeRatingTest() {
        logger.startTestCase("createReviewWithNegativeRatingTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(-1);
        payload.setComment("Negative rating");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createReviewWithNegativeRatingTest");
    }

    @Test(priority = 24, description = "Update non-existent review")
    public void updateNonExistentReviewTest() {
        logger.startTestCase("updateNonExistentReviewTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setComment("Updated comment");

        Response response = ReviewEndpoints.updateReview("invalid-review-id", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("updateNonExistentReviewTest");
    }

    @Test(priority = 25, description = "Delete review")
    public void deleteReviewTest() {
        logger.startTestCase("deleteReviewTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Review to delete");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.deleteReview(reviewId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("deleteReviewTest");
    }

    @Test(priority = 26, description = "Mark review as helpful")
    public void markReviewHelpfulTest() {
        logger.startTestCase("markReviewHelpfulTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Helpful review");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.markReviewHelpful(reviewId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("markReviewHelpfulTest");
    }

    @Test(priority = 27, description = "Get reviews with media (images/videos)")
    public void getMediaReviewsTest() {
        logger.startTestCase("getMediaReviewsTest");

        Response response = ReviewEndpoints.getMediaReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getMediaReviewsTest");
    }

    @Test(priority = 28, description = "Report inappropriate review")
    public void reportReviewTest() {
        logger.startTestCase("reportReviewTest");

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Review to report");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.reportReview(reviewId, "OFFENSIVE_LANGUAGE");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("reportReviewTest");
    }
}

