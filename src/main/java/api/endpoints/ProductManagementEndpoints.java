package api.endpoints;

import api.payload.ProductManagementPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductManagementEndpoints {

    private static final String PRODUCT_BASE = "/api/products";

    public static Response getAllProducts() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(PRODUCT_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response getProductById(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(PRODUCT_BASE + "/" + productId)
                .then()
                .extract()
                .response();
    }

    public static Response createProduct(ProductManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(PRODUCT_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response updateProduct(String productId, ProductManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(PRODUCT_BASE + "/" + productId)
                .then()
                .extract()
                .response();
    }

    public static Response deleteProduct(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(PRODUCT_BASE + "/" + productId)
                .then()
                .extract()
                .response();
    }

    public static Response getProductsByCategory(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("categoryId", categoryId)
                .when()
                .get(PRODUCT_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response searchProducts(String searchTerm) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("search", searchTerm)
                .when()
                .get(PRODUCT_BASE + "/search")
                .then()
                .extract()
                .response();
    }

    public static Response updateProductInventory(String productId, ProductManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(PRODUCT_BASE + "/" + productId + "/inventory")
                .then()
                .extract()
                .response();
    }

    public static Response addProductImage(String productId, String imageUrl) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("imageUrl", imageUrl)
                .when()
                .post(PRODUCT_BASE + "/" + productId + "/images")
                .then()
                .extract()
                .response();
    }
}

