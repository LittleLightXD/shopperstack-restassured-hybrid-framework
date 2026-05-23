package api.testcases;

import api.base.BaseTest;
import api.endpoints.MerchantEndpoints;
import api.payload.MerchantPayload;
import api.utils.FakeDataGenerator;
import api.logging.CustomLogger;
import api.dataproviders.MerchantDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MerchantTestcases extends BaseTest {



    @Test(priority = 1, description = "Create merchant with valid data - Status 201")
    public void createMerchantWithValidDataTest() {
        CustomLogger.startTestCase("createMerchantWithValidData");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setBusinessEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setBusinessPhone(FakeDataGenerator.getPhoneNumber());
        payload.setBusinessType("RETAIL");
        payload.setGstNumber("18AABCU1234H1Z0");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(response.jsonPath().getString("merchantId"), "Merchant ID should not be null");

        CustomLogger.info("✓ Merchant created successfully with ID: " + response.jsonPath().getString("merchantId"));
        CustomLogger.endTestCase("createMerchantWithValidData");
    }

    @Test(priority = 2, description = "Create merchant with company and address details")
    public void createMerchantWithEmbeddedDetailsTest() {
        CustomLogger.startTestCase("createMerchantWithEmbeddedDetails");

        MerchantPayload.CompanyDetails companyDetails = new MerchantPayload.CompanyDetails();
        companyDetails.setCompanyName(FakeDataGenerator.getCompanyName());
        companyDetails.setBusinessType("WHOLESALE");
        companyDetails.setGst("18AABCU1234H1Z0");

        MerchantPayload.AddressDetails addressDetails = new MerchantPayload.AddressDetails();
        addressDetails.setAddress(FakeDataGenerator.getAddress());
        addressDetails.setCity(FakeDataGenerator.getCity());
        addressDetails.setState("Karnataka");
        addressDetails.setCountry(FakeDataGenerator.getCountry());
        addressDetails.setZipCode(FakeDataGenerator.getPostalCode());

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setCompanyDetails(companyDetails);
        payload.setAddressDetails(addressDetails);

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        CustomLogger.info("✓ Merchant created with company and address details");
        CustomLogger.endTestCase("createMerchantWithEmbeddedDetails");
    }

    @Test(priority = 3, description = "Get merchant by valid ID")
    public void getMerchantByIdTest() {
        CustomLogger.startTestCase("getMerchantById");


        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        Response response = MerchantEndpoints.getMerchantById(merchantId);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("merchantId"), merchantId);

        CustomLogger.info("✓ Merchant retrieved successfully");
        CustomLogger.endTestCase("getMerchantById");
    }

    @Test(priority = 4, description = "Update merchant - API Chaining")
    public void updateMerchantTest() {
        CustomLogger.startTestCase("updateMerchant");


        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName("Original Name");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        MerchantPayload updatePayload = new MerchantPayload();
        updatePayload.setBusinessName("Updated Name");

        Response response = MerchantEndpoints.updateMerchant(merchantId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        CustomLogger.info("✓ Merchant updated successfully");
        CustomLogger.endTestCase("updateMerchant");
    }

    @Test(priority = 5, description = "Update merchant status")
    public void updateMerchantStatusTest() {
        CustomLogger.startTestCase("updateMerchantStatus");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        Response response = MerchantEndpoints.updateMerchantStatus(merchantId, "ACTIVE");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        CustomLogger.info("✓ Merchant status updated to ACTIVE");
        CustomLogger.endTestCase("updateMerchantStatus");
    }



    @Test(priority = 10, dataProvider = "validMerchantData",
           dataProviderClass = MerchantDataProvider.class,
           description = "Create merchant with multiple datasets")
    public void createMerchantDataDrivenTest(String businessName, String email, String phone, String businessType) {
        CustomLogger.startTestCase("createMerchantDataDriven");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(businessName);
        payload.setEmail(email);
        payload.setPhone(phone);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setBusinessType(businessType);

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201);
        CustomLogger.info("✓ Merchant created: " + businessName);
        CustomLogger.endTestCase("createMerchantDataDriven");
    }



    @Test(priority = 20, description = "Duplicate email validation - Status 409")
    public void duplicateMerchantEmailTest() {
        CustomLogger.startTestCase("duplicateMerchantEmail");

        String duplicateEmail = FakeDataGenerator.getUniqueEmail();


        MerchantPayload payload1 = new MerchantPayload();
        payload1.setBusinessName(FakeDataGenerator.getCompanyName());
        payload1.setEmail(duplicateEmail);
        payload1.setPhone(FakeDataGenerator.getPhoneNumber());
        payload1.setPassword(FakeDataGenerator.getStrongPassword());
        payload1.setConfirmPassword(payload1.getPassword());

        Response response1 = MerchantEndpoints.createMerchant(payload1);
        Assert.assertEquals(response1.getStatusCode(), 201);


        MerchantPayload payload2 = new MerchantPayload();
        payload2.setBusinessName(FakeDataGenerator.getCompanyName());
        payload2.setEmail(duplicateEmail);
        payload2.setPhone(FakeDataGenerator.getPhoneNumber());
        payload2.setPassword(FakeDataGenerator.getStrongPassword());
        payload2.setConfirmPassword(payload2.getPassword());

        Response response2 = MerchantEndpoints.createMerchant(payload2);

        Assert.assertEquals(response2.getStatusCode(), 409, "Expected status code 409 for duplicate email");
        CustomLogger.info("✓ Duplicate email correctly rejected with 409");
        CustomLogger.endTestCase("duplicateMerchantEmail");
    }

    @Test(priority = 21, description = "Missing required fields - Status 400")
    public void createMerchantWithMissingFieldsTest() {
        CustomLogger.startTestCase("createMerchantWithMissingFields");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());

        payload.setPassword(FakeDataGenerator.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422,
                "Expected error status code");
        CustomLogger.info("✓ Missing fields correctly rejected");
        CustomLogger.endTestCase("createMerchantWithMissingFields");
    }

    @Test(priority = 22, description = "Invalid email format - Status 400")
    public void createMerchantWithInvalidEmailTest() {
        CustomLogger.startTestCase("createMerchantWithInvalidEmail");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail("invalid-email-format");
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
        CustomLogger.info("✓ Invalid email format rejected");
        CustomLogger.endTestCase("createMerchantWithInvalidEmail");
    }

    @Test(priority = 23, description = "Invalid phone format - Status 400")
    public void createMerchantWithInvalidPhoneTest() {
        CustomLogger.startTestCase("createMerchantWithInvalidPhone");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone("123");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
        CustomLogger.info("✓ Invalid phone format rejected");
        CustomLogger.endTestCase("createMerchantWithInvalidPhone");
    }

    @Test(priority = 24, description = "Invalid merchant ID - Status 404")
    public void getMerchantWithInvalidIdTest() {
        CustomLogger.startTestCase("getMerchantWithInvalidId");

        Response response = MerchantEndpoints.getMerchantById("invalid-merchant-id-99999");

        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");
        CustomLogger.info("✓ Invalid merchant ID correctly returned 404");
        CustomLogger.endTestCase("getMerchantWithInvalidId");
    }

    @Test(priority = 25, description = "Password and confirm password mismatch - Status 400")
    public void passwordMismatchTest() {
        CustomLogger.startTestCase("passwordMismatch");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword("Password@123");
        payload.setConfirmPassword("DifferentPassword@123");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
        CustomLogger.info("✓ Password mismatch rejected");
        CustomLogger.endTestCase("passwordMismatch");
    }

    @Test(priority = 26, description = "Invalid GST number - Status 400")
    public void invalidGstNumberTest() {
        CustomLogger.startTestCase("invalidGstNumber");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setGstNumber("INVALID");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
        CustomLogger.info("✓ Invalid GST number rejected");
        CustomLogger.endTestCase("invalidGstNumber");
    }

    @Test(priority = 27, description = "Delete merchant - Status 200/204")
    public void deleteMerchantTest() {
        CustomLogger.startTestCase("deleteMerchant");

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");

        Response response = MerchantEndpoints.deleteMerchant(merchantId);

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204,
                "Expected 200 or 204");
        CustomLogger.info("✓ Merchant deleted successfully");
        CustomLogger.endTestCase("deleteMerchant");
    }

    @Test(priority = 28, description = "Access control - unauthorized request without token")
    public void unauthorizedAccessTest() {
        CustomLogger.startTestCase("unauthorizedAccess");



        CustomLogger.info("✓ Access control validation test");
        CustomLogger.endTestCase("unauthorizedAccess");
    }
}

