package api.endpoints;

import api.payload.CategoryManagementPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * CategoryManagementEndpoints - REST API endpoints for Category Management
 */
public class CategoryManagementEndpoints {

    private static final String CATEGORY_BASE = "/api/categories";

    public static Response getAllCategories() {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(CATEGORY_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response getCategoryById(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(CATEGORY_BASE + "/" + categoryId)
                .then()
                .extract()
                .response();
    }

    public static Response createCategory(CategoryManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(CATEGORY_BASE)
                .then()
                .extract()
                .response();
    }

    public static Response updateCategory(String categoryId, CategoryManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(CATEGORY_BASE + "/" + categoryId)
                .then()
                .extract()
                .response();
    }

    public static Response deleteCategory(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(CATEGORY_BASE + "/" + categoryId)
                .then()
                .extract()
                .response();
    }

    public static Response createSubcategory(CategoryManagementPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .post(CATEGORY_BASE + "/subcategories")
                .then()
                .extract()
                .response();
    }

    public static Response getSubcategories(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(CATEGORY_BASE + "/" + categoryId + "/subcategories")
                .then()
                .extract()
                .response();
    }

    public static Response activateCategory(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .put(CATEGORY_BASE + "/" + categoryId + "/activate")
                .then()
                .extract()
                .response();
    }

    public static Response deactivateCategory(String categoryId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .put(CATEGORY_BASE + "/" + categoryId + "/deactivate")
                .then()
                .extract()
                .response();
    }
}
