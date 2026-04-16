package api.endpoints;

import api.payload.*;
import api.specs.ReusableRequestSpec;
import api.specs.ReusableResponseSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

/**
 * AdminEndpoints class - contains all admin API request logic
 */
public class AdminEndpoints {

    /**
     * Create admin with payload
     */
    public static Response createAdmin(AdminPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.CREATE_ADMIN)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Create admin and extract admin ID (API Chaining)
     */
    public static String createAdminAndGetId(AdminPayload payload) {
        Response response = createAdmin(payload);
        
        if (response.getStatusCode() == 201) {
            String adminId = response.jsonPath().getString("adminId");
            return adminId;
        }
        return null;
    }

    /**
     * Get admin by ID
     */
    public static Response getAdminById(String adminId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("adminId", adminId)
                .when()
                .get(Routes.GET_ADMIN)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Update admin by ID
     */
    public static Response updateAdmin(String adminId, AdminPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("adminId", adminId)
                .body(payload)
                .when()
                .put(Routes.UPDATE_ADMIN)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Delete admin by ID
     */
    public static Response deleteAdmin(String adminId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .pathParam("adminId", adminId)
                .when()
                .delete(Routes.GET_ADMIN)
                .then()
                .extract()
                .response();
        
        return response;
    }

    /**
     * Login admin (extract token)
     */
    public static Response loginAdmin(LoginAdminPayload loginPayload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(loginPayload)
                .when()
                .post(Routes.LOGIN)
                .then()
                .extract()
                .response();
        
        if (response.getStatusCode() == 200) {
            String token = response.jsonPath().getString("token");
            String userId = response.jsonPath().getString("userId");
            TokenManager.setToken(token);
            TokenManager.setUserId(userId);
        }
        
        return response;
    }
}
