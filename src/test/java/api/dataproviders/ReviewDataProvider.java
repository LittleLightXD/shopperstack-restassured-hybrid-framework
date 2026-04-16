package api.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * ReviewDataProvider - Test data for Review module test cases
 * Provides realistic test data variations for different review scenarios
 */
public class ReviewDataProvider {

    @DataProvider(name = "validReviewData")
    public Object[][] validReviewData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Excellent product! Highly recommended."},
            {"product-002", "shopper-002", 4, "Good quality, fast delivery."},
            {"product-003", "shopper-003", 3, "Average product, meets expectations."},
            {"product-004", "shopper-004", 2, "Below average, had some issues."},
        };
    }

    @DataProvider(name = "positiveReviewData")
    public Object[][] positiveReviewData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Perfect! Everything as described."},
            {"product-002", "shopper-002", 5, "Amazing quality and fast shipping!"},
            {"product-003", "shopper-003", 4, "Very satisfied with the purchase."},
            {"product-004", "shopper-004", 4, "Great value for money."},
        };
    }

    @DataProvider(name = "negativeReviewData")
    public Object[][] negativeReviewData() {
        return new Object[][] {
            {"product-001", "shopper-001", 1, "Very disappointed with the quality."},
            {"product-002", "shopper-002", 2, "Product arrived damaged."},
            {"product-003", "shopper-003", 1, "Not as described in the listing."},
            {"product-004", "shopper-004", 2, "Poor quality and customer service."},
        };
    }

    @DataProvider(name = "ratingDistributionData")
    public Object[][] ratingDistributionData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "5-star review"},
            {"product-002", "shopper-002", 4, "4-star review"},
            {"product-003", "shopper-003", 3, "3-star review"},
            {"product-004", "shopper-004", 2, "2-star review"},
            {"product-005", "shopper-005", 1, "1-star review"},
        };
    }

    @DataProvider(name = "verifiedPurchaseReviewData")
    public Object[][] verifiedPurchaseReviewData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Verified purchase review - Excellent!"},
            {"product-002", "shopper-002", 4, "Verified purchase review - Good quality"},
            {"product-003", "shopper-003", 3, "Verified purchase review - Average"},
        };
    }

    @DataProvider(name = "reviewWithMediaData")
    public Object[][] reviewWithMediaData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Great product with images attached"},
            {"product-002", "shopper-002", 4, "Review with video demonstration"},
            {"product-003", "shopper-003", 4, "Multiple images and video support"},
        };
    }

    @DataProvider(name = "reviewLengthData")
    public Object[][] reviewLengthData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Short review."},
            {"product-002", "shopper-002", 4, "This is a medium length review that provides some details about the product and the shopping experience."},
            {"product-003", "shopper-003", 3, "This is a very long and detailed review that goes into great depth about the product quality, its features, the shipping process, the customer service, and all other aspects of the purchase. The reviewer provides comprehensive information and recommendations."},
        };
    }

    @DataProvider(name = "reviewCommentData")
    public Object[][] reviewCommentData() {
        return new Object[][] {
            {"product-001", "shopper-001", 5, "Excellent product quality and value"},
            {"product-002", "shopper-002", 4, "Good product, fast and reliable shipping"},
            {"product-003", "shopper-003", 3, "Average product with standard features"},
            {"product-004", "shopper-004", 2, "Fair product with some minor issues"},
        };
    }
}
