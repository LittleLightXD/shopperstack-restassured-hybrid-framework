package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.specs.ReusableRequestSpec;
import static io.restassured.RestAssured.*;

/**
 * TokenManager class to manage authentication tokens
 * Stores token and provides reusable access
 */
public class TokenManager {

    private static String token;
    private static String userId;
    private static final String LOGIN_ENDPOINT = "/api/admin/login";

    /**
     * Login and get token (executes only once)
     */
    public static void generateToken() {
        if (token != null) {
            System.out.println("Token already generated, reusing existing token");
            return;
        }

        ConfigReader config = ConfigReader.getInstance();
        String username = config.getAdminUsername();
        String password = config.getAdminPassword();

        try {
            Response response = given()
                    .spec(ReusableRequestSpec.buildRequestSpec())
                    .body("{\"email\":\"" + username + "\",\"password\":\"" + password + "\"}")
                    .when()
                    .post(LOGIN_ENDPOINT)
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                token = response.jsonPath().getString("token");
                userId = response.jsonPath().getString("userId");
                System.out.println("✓ Token generated successfully");
                System.out.println("Token: " + token);
            } else {
                System.out.println("✗ Failed to generate token. Status Code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Error generating token: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get stored token
     */
    public static String getToken() {
        if (token == null) {
            generateToken();
        }
        return token;
    }

    /**
     * Get stored user ID
     */
    public static String getUserId() {
        if (userId == null) {
            generateToken();
        }
        return userId;
    }

    /**
     * Set token manually (useful for test scenarios)
     */
    public static void setToken(String authToken) {
        token = authToken;
    }

    /**
     * Set user ID manually
     */
    public static void setUserId(String id) {
        userId = id;
    }

    /**
     * Clear token (useful for logout scenarios)
     */
    public static void clearToken() {
        token = null;
        userId = null;
        System.out.println("Token cleared");
    }

    /**
     * Check if token is available
     */
    public static boolean isTokenAvailable() {
        return token != null && !token.isEmpty();
    }
}
