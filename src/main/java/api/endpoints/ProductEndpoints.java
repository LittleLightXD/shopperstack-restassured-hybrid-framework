package api.endpoints;

import api.payload.ProductPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ProductEndpoints - REST API endpoints for Product module
 * Provides all product-related operations (CRUD, search, filtering, merchant products)
 */
public class ProductEndpoints {

    /**
     * Create a new product (Merchant privilege)
     */
    public static Response createProduct(ProductPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Create product and extract product ID for API chaining
     */
    public static String createProductAndGetId(ProductPayload payload) {
        Response response = createProduct(payload);
        return response.jsonPath().getString("productId");
    }

    /**
     * Get product by ID
     */
    public static Response getProductById(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get all products with pagination and filters
     */
    public static Response getAllProducts(Integer pageNumber, Integer pageSize, String sortBy) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .queryParam("sortBy", sortBy)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get product by merchant ID
     */
    public static Response getProductsByMerchant(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.MERCHANT_PRODUCT.replace("{merchantId}", merchantId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get all products alphabetically sorted
     */
    public static Response getAllProductsAlphabetically() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.ALL_PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Update product details
     */
    public static Response updateProduct(String productId, ProductPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Delete product
     */
    public static Response deleteProduct(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Search products by name
     */
    public static Response searchProducts(String searchTerm) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("searchTerm", searchTerm)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Filter products by category
     */
    public static Response filterProductsByCategory(String category) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("category", category)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Filter products by price range
     */
    public static Response filterProductsByPrice(Double minPrice, Double maxPrice) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Filter products by brand
     */
    public static Response filterProductsByBrand(String brand) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("brand", brand)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Filter products by rating
     */
    public static Response filterProductsByRating(Double minRating) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("minRating", minRating)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get in-stock products
     */
    public static Response getInStockProducts() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("inStock", true)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get out-of-stock products
     */
    public static Response getOutOfStockProducts() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("inStock", false)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }
}
