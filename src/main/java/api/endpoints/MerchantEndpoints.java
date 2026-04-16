package api.endpoints;

import api.payload.MerchantPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

/**
 * MerchantEndpoints class - contains all merchant API request logic
 * Converts Postman collection merchant operations to REST Assured
 */
public class MerchantEndpoints {

    /**
     * Create merchant with payload
     * POST /api/merchants
     */
    public static Response createMerchant(MerchantPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Create merchant and extract merchant ID (API Chaining)
     */
    public static String createMerchantAndGetId(MerchantPayload payload) {
        Response response = createMerchant(payload);
        
        if (response.getStatusCode() == 201) {
            String merchantId = response.jsonPath().getString("merchantId");
            return merchantId;
        }
        return null;
    }

    /**
     * Get merchant by ID
     * GET /api/merchants/{merchantId}
     */
    public static Response getMerchantById(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Get all merchants
     * GET /api/merchants
     */
    public static Response getAllMerchants() {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Update merchant by ID
     * PUT /api/merchants/{merchantId}
     */
    public static Response updateMerchant(String merchantId, MerchantPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .body(payload)
                .when()
                .put(Routes.UPDATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Delete merchant by ID
     * DELETE /api/merchants/{merchantId}
     */
    public static Response deleteMerchant(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .when()
                .delete(Routes.UPDATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Update merchant status
     * PUT /api/merchants/{merchantId}/status
     */
    public static Response updateMerchantStatus(String merchantId, String status) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .body("{\"status\":\"" + status + "\"}")
                .when()
                .put(Routes.UPDATE_MERCHANT_STATUS)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Get merchant with company details (embedded data)
     */
    public static Response getMerchantWithCompanyDetails(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Get merchant with address details (embedded data)
     */
    public static Response getMerchantWithAddressDetails(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Search merchants by name
     */
    public static Response searchMerchants(String searchTerm) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("search", searchTerm)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Get merchants by status
     */
    public static Response getMerchantsByStatus(String status) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("status", status)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();
        
        return response;
    }
}
