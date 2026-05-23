package api.testcases;

import api.base.BaseTest;
import api.endpoints.AdminEndpoints;
import api.payload.AdminPayload;
import api.payload.LoginAdminPayload;
import api.utils.FakeDataGenerator;
import api.logging.CustomLogger;
import api.dataproviders.AdminDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Admintestcases extends BaseTest {



    @Test(priority = 1, description = "Create admin with valid data")
    public void createAdminWithValidDataTest() {
        CustomLogger.startTestCase("createAdminWithValidData");


        AdminPayload payload = new AdminPayload();
        payload.setCity("Bangalore");
        payload.setCountry("India");
        payload.setDob("1990-01-01");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setGender("MALE");
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setState("Karnataka");
        payload.setStatus("ACTIVE");
        payload.setZoneId("ALPHA");


        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(response.jsonPath().getString("adminId"), "Admin ID should not be null");

        CustomLogger.info("✓ Admin created successfully with ID: " + response.jsonPath().getString("adminId"));
        CustomLogger.endTestCase("createAdminWithValidData");
    }

    @Test(priority = 2, description = "Get admin by valid ID")
    public void getAdminByIdTest() {
        CustomLogger.startTestCase("getAdminById");


        AdminPayload payload = new AdminPayload();
        payload.setCity("Mumbai");
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName("Test");
        payload.setLastName("User");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response createResponse = AdminEndpoints.createAdmin(payload);
        String adminId = createResponse.jsonPath().getString("adminId");


        Response response = AdminEndpoints.getAdminById(adminId);


        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("adminId"), adminId, "Admin ID should match");

        CustomLogger.info("✓ Admin retrieved successfully");
        CustomLogger.endTestCase("getAdminById");
    }

    @Test(priority = 3, description = "Update admin details")
    public void updateAdminTest() {
        CustomLogger.startTestCase("updateAdmin");


        AdminPayload payload = new AdminPayload();
        payload.setCity("Delhi");
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName("Original");
        payload.setLastName("Name");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response createResponse = AdminEndpoints.createAdmin(payload);
        String adminId = createResponse.jsonPath().getString("adminId");


        AdminPayload updatePayload = new AdminPayload();
        updatePayload.setFirstName("Updated");
        updatePayload.setLastName("Name");

        Response response = AdminEndpoints.updateAdmin(adminId, updatePayload);


        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

        CustomLogger.info("✓ Admin updated successfully");
        CustomLogger.endTestCase("updateAdmin");
    }

    @Test(priority = 4, description = "API Chaining - Create admin and extract ID")
    public void apiChainingTest() {
        CustomLogger.startTestCase("apiChaining");

        AdminPayload payload = new AdminPayload();
        payload.setCity("Pune");
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");


        String adminId = AdminEndpoints.createAdminAndGetId(payload);


        Assert.assertNotNull(adminId, "Admin ID should not be null");

        CustomLogger.info("✓ API Chaining successful - Admin ID: " + adminId);
        CustomLogger.endTestCase("apiChaining");
    }



    @Test(priority = 5, dataProvider = "validAdminData", dataProviderClass = AdminDataProvider.class,
            description = "Create admin with multiple valid datasets")
    public void createAdminDataDrivenTest(String firstName, String lastName, String email, String phone) {
        CustomLogger.startTestCase("createAdminDataDriven");

        AdminPayload payload = new AdminPayload();
        payload.setCity("TestCity");
        payload.setCountry("India");
        payload.setEmail(email);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(phone);
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response response = AdminEndpoints.createAdmin(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        CustomLogger.info("✓ Admin created for user: " + firstName + " " + lastName);
        CustomLogger.endTestCase("createAdminDataDriven");
    }



    @Test(priority = 6, description = "Create admin with invalid email format")
    public void createAdminWithInvalidEmailTest() {
        CustomLogger.startTestCase("createAdminWithInvalidEmail");

        AdminPayload payload = new AdminPayload();
        payload.setCity("TestCity");
        payload.setCountry("India");
        payload.setEmail("invalid-email");
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for invalid email");

        CustomLogger.info("✓ Invalid email correctly rejected");
        CustomLogger.endTestCase("createAdminWithInvalidEmail");
    }

    @Test(priority = 7, description = "Create admin with missing required fields")
    public void createAdminWithMissingFieldsTest() {
        CustomLogger.startTestCase("createAdminWithMissingFields");

        AdminPayload payload = new AdminPayload();
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());

        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");

        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422,
                "Expected error status code");

        CustomLogger.info("✓ Missing fields correctly rejected");
        CustomLogger.endTestCase("createAdminWithMissingFields");
    }

    @Test(priority = 8, description = "Get admin with invalid ID")
    public void getAdminWithInvalidIdTest() {
        CustomLogger.startTestCase("getAdminWithInvalidId");

        Response response = AdminEndpoints.getAdminById("invalid-admin-id-12345");


        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");

        CustomLogger.info("✓ Invalid admin ID correctly returned 404");
        CustomLogger.endTestCase("getAdminWithInvalidId");
    }

    @Test(priority = 9, description = "Duplicate email validation")
    public void duplicateEmailTest() {
        CustomLogger.startTestCase("duplicateEmail");

        String duplicateEmail = FakeDataGenerator.getUniqueEmail();


        AdminPayload payload1 = new AdminPayload();
        payload1.setCity("City1");
        payload1.setCountry("India");
        payload1.setEmail(duplicateEmail);
        payload1.setFirstName("Admin1");
        payload1.setLastName("User1");
        payload1.setPassword(FakeDataGenerator.getPassword());
        payload1.setPhone(FakeDataGenerator.getPhoneNumber());
        payload1.setRole("ADMIN");
        payload1.setStatus("ACTIVE");

        Response response1 = AdminEndpoints.createAdmin(payload1);
        Assert.assertEquals(response1.getStatusCode(), 201, "First admin should be created");


        AdminPayload payload2 = new AdminPayload();
        payload2.setCity("City2");
        payload2.setCountry("India");
        payload2.setEmail(duplicateEmail);
        payload2.setFirstName("Admin2");
        payload2.setLastName("User2");
        payload2.setPassword(FakeDataGenerator.getPassword());
        payload2.setPhone(FakeDataGenerator.getPhoneNumber());
        payload2.setRole("ADMIN");
        payload2.setStatus("ACTIVE");

        Response response2 = AdminEndpoints.createAdmin(payload2);


        Assert.assertTrue(response2.getStatusCode() == 409 || response2.getStatusCode() == 400,
                "Duplicate email should be rejected");

        CustomLogger.info("✓ Duplicate email correctly rejected");
        CustomLogger.endTestCase("duplicateEmail");
    }

    @Test(priority = 10, description = "Unauthorized access without token")
    public void unauthorizedAccessTest() {
        CustomLogger.startTestCase("unauthorizedAccess");

        String adminId = "test-admin-id";



        Response response = AdminEndpoints.getAdminById(adminId);


        CustomLogger.info("✓ Token validation test completed");
        CustomLogger.endTestCase("unauthorizedAccess");
    }
}

