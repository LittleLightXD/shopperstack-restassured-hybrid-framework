package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ReviewPayload - POJO for Product Review API request/response
 * Contains all review-related fields for product ratings and comments
 */
public class ReviewPayload {

    @JsonProperty("reviewId")
    private String reviewId;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("shopperId")
    private String shopperId;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("title")
    private String title;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("isVerifiedPurchase")
    private Boolean isVerifiedPurchase;

    @JsonProperty("helpful")
    private Integer helpful;

    @JsonProperty("unhelpful")
    private Integer unhelpful;

    @JsonProperty("reviewDate")
    private String reviewDate;

    @JsonProperty("reviewStatus")
    private String reviewStatus;

    @JsonProperty("images")
    private String[] images;

    @JsonProperty("videos")
    private String[] videos;

    @JsonProperty("createdDateTime")
    private String createdDateTime;

    @JsonProperty("updatedDateTime")
    private String updatedDateTime;

    // Nested RatingDistribution class
    public static class RatingDistribution {
        @JsonProperty("fiveStarCount") 
        private Integer fiveStarCount;

        @JsonProperty("fourStarCount") 
        private Integer fourStarCount;

        @JsonProperty("threeStarCount") 
        private Integer threeStarCount;

        @JsonProperty("twoStarCount")
        private Integer twoStarCount;

        @JsonProperty("oneStarCount")
        private Integer oneStarCount;

        @JsonProperty("averageRating")
        private Double averageRating;

        @JsonProperty("totalReviews")
        private Integer totalReviews;

        // Constructors
        public RatingDistribution() {}

        public RatingDistribution(Integer fiveStarCount, Integer fourStarCount, Integer threeStarCount) {
            this.fiveStarCount = fiveStarCount;
            this.fourStarCount = fourStarCount;
            this.threeStarCount = threeStarCount;
        }

        // Getters and Setters
        public Integer getFiveStarCount() {
            return fiveStarCount;
        }

        public void setFiveStarCount(Integer fiveStarCount) {
            this.fiveStarCount = fiveStarCount;
        }

        public Integer getFourStarCount() {
            return fourStarCount;
        }

        public void setFourStarCount(Integer fourStarCount) {
            this.fourStarCount = fourStarCount;
        }

        public Integer getThreeStarCount() {
            return threeStarCount;
        }

        public void setThreeStarCount(Integer threeStarCount) {
            this.threeStarCount = threeStarCount;
        }

        public Integer getTwoStarCount() {
            return twoStarCount;
        }

        public void setTwoStarCount(Integer twoStarCount) {
            this.twoStarCount = twoStarCount;
        }

        public Integer getOneStarCount() {
            return oneStarCount;
        }

        public void setOneStarCount(Integer oneStarCount) {
            this.oneStarCount = oneStarCount;
        }

        public Double getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(Double averageRating) {
            this.averageRating = averageRating;
        }

        public Integer getTotalReviews() {
            return totalReviews;
        }

        public void setTotalReviews(Integer totalReviews) {
            this.totalReviews = totalReviews;
        }
    }

    // Constructors
    public ReviewPayload() {}

    public ReviewPayload(String productId, String shopperId, Integer rating, String comment) {
        this.productId = productId;
        this.shopperId = shopperId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and Setters
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsVerifiedPurchase() {
        return isVerifiedPurchase;
    }

    public void setIsVerifiedPurchase(Boolean isVerifiedPurchase) {
        this.isVerifiedPurchase = isVerifiedPurchase;
    }

    public Integer getHelpful() {
        return helpful;
    }

    public void setHelpful(Integer helpful) {
        this.helpful = helpful;
    }

    public Integer getUnhelpful() {
        return unhelpful;
    }

    public void setUnhelpful(Integer unhelpful) {
        this.unhelpful = unhelpful;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
