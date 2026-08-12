package api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.payload.AdminPayload;
import api.payload.LoginAdminPayload;
import api.specs.ReusableRequestSpec;
import api.endpoints.Routes;
import static io.restassured.RestAssured.*;

public class TokenManager {

    private static String token;
    private static String userId;
    private static final String LOGIN_ENDPOINT = Routes.LOGIN;


    public static void generateToken() {
        if (token != null) {
            System.out.println("Token already generated, reusing existing token");
            return;
        }

        ConfigReader config = ConfigReader.getInstance();
        LoginAdminPayload loginPayload = new LoginAdminPayload(
                config.getAdminUsername(), config.getAdminPassword());

        try {
            Response response = login(loginPayload);

            if (response.getStatusCode() == 401) {
                loginPayload = registerDisposableAdmin();
                response = login(loginPayload);
            }

            if (response.getStatusCode() == 200) {
                token = response.jsonPath().getString("data.jwtToken");
                userId = response.jsonPath().getString("data.userId");
                if (token == null || token.isBlank()) {
                    throw new IllegalStateException("Login response did not contain data.jwtToken");
                }
                System.out.println("✓ Token generated successfully");
            } else {
                System.out.println("✗ Failed to generate token. Status Code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Error generating token: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Response login(LoginAdminPayload loginPayload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(loginPayload)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    private static LoginAdminPayload registerDisposableAdmin() {
        String email = FakeDataGenerator.getUniqueEmail();
        String password = FakeDataGenerator.getStrongPassword();

        AdminPayload admin = new AdminPayload();
        admin.setCity("Bangalore");
        admin.setCountry("India");
        admin.setDob("1990-01-01");
        admin.setEmail(email);
        admin.setFirstName(FakeDataGenerator.getFirstName());
        admin.setGender("MALE");
        admin.setLastName(FakeDataGenerator.getLastName());
        admin.setPassword(password);
        admin.setPhone(FakeDataGenerator.getPhoneNumber());
        admin.setRole("ADMIN");
        admin.setState("Karnataka");
        admin.setStatus("ACTIVE");
        admin.setZoneId("ALPHA");

        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(admin)
                .when()
                .post(Routes.CREATE_ADMIN)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() != 201) {
            throw new IllegalStateException(
                    "Could not create a disposable admin. Status code: " + response.getStatusCode());
        }

        return new LoginAdminPayload(email, password);
    }


    public static String getToken() {
        if (token == null) {
            generateToken();
        }
        return token;
    }


    public static String getUserId() {
        if (userId == null) {
            generateToken();
        }
        return userId;
    }


    public static void setToken(String authToken) {
        token = authToken;
    }


    public static void setUserId(String id) {
        userId = id;
    }


    public static void clearToken() {
        token = null;
        userId = null;
        System.out.println("Token cleared");
    }


    public static boolean isTokenAvailable() {
        return token != null && !token.isEmpty();
    }
}
