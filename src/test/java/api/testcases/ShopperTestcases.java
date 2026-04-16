package api.testcases;

import api.base.BaseTest;
import api.dataproviders.ShopperDataProvider;
import api.endpoints.ShopperEndpoints;
import api.logging.CustomLogger;
import api.payload.ShopperPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ShopperTestcases - Comprehensive test scenarios for Shopper module
 * Covers positive, negative, and data-driven test cases for shopper CRUD operations,
 * address management, wishlist, and cart functionality
 */
public class ShopperTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();

    // ==================== POSITIVE TEST CASES ====================

    @Test(priority = 1, description = "Create shopper with valid data")
    public void createShopperWithValidDataTest() {
        logger.startTestCase("createShopperWithValidDataTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setGender("MALE");
        payload.setDateOfBirth("1990-01-15");

        Response response = ShopperEndpoints.createShopper(payload);
        logger.logAPIResponse(response.getStatusCode(), response.getBody().asString());
        
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("shopperId"));
        
        logger.endTestCase("createShopperWithValidDataTest");
    }

    @Test(priority = 2, description = "Get shopper by ID")
    public void getShopperByIdTest() {
        logger.startTestCase("getShopperByIdTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.getShopperById(shopperId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("shopperId"), shopperId);
        
        logger.endTestCase("getShopperByIdTest");
    }

    @Test(priority = 3, description = "Update shopper profile")
    public void updateShopperTest() {
        logger.startTestCase("updateShopperTest");
        
        ShopperPayload createPayload = new ShopperPayload();
        createPayload.setFirstName(FakeDataGenerator.getFirstName());
        createPayload.setLastName(FakeDataGenerator.getLastName());
        createPayload.setEmail(FakeDataGenerator.getUniqueEmail());
        createPayload.setPassword(FakeDataGenerator.getStrongPassword());
        createPayload.setConfirmPassword(createPayload.getPassword());
        createPayload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(createPayload);

        ShopperPayload updatePayload = new ShopperPayload();
        updatePayload.setFirstName("UpdatedFirstName");
        updatePayload.setLastName("UpdatedLastName");
        updatePayload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.updateShopper(shopperId, updatePayload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("updateShopperTest");
    }

    @Test(priority = 4, description = "Add address to shopper account")
    public void addShopperAddressTest() {
        logger.startTestCase("addShopperAddressTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);

        ShopperPayload.AddressDetails addressDetails = new ShopperPayload.AddressDetails();
        addressDetails.setAddress(FakeDataGenerator.getAddress());
        addressDetails.setCity(FakeDataGenerator.getCity());
        addressDetails.setState("TestState");
        addressDetails.setCountry(FakeDataGenerator.getCountry());
        addressDetails.setZipCode(FakeDataGenerator.getPostalCode());
        addressDetails.setAddressType("HOME");

        Response response = ShopperEndpoints.addShopperAddress(shopperId, addressDetails);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("addShopperAddressTest");
    }

    @Test(priority = 5, description = "Add items to wishlist")
    public void addToWishlistTest() {
        logger.startTestCase("addToWishlistTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.addToWishlist(shopperId, "product-123456");
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("addToWishlistTest");
    }

    @Test(priority = 6, description = "Add items to cart")
    public void addToCartTest() {
        logger.startTestCase("addToCartTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.addToCart(shopperId, "product-123456", 2);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("addToCartTest");
    }

    @Test(priority = 7, description = "Get shopper cart")
    public void getCartTest() {
        logger.startTestCase("getCartTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.getCart(shopperId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getCartTest");
    }

    // ==================== DATA-DRIVEN TEST CASES ====================

    @Test(priority = 10, dataProvider = "validShopperData", dataProviderClass = ShopperDataProvider.class,
            description = "Create shopper with data provider")
    public void createShopperDataDrivenTest(String firstName, String lastName, String email, String phone) {
        logger.startTestCase("createShopperDataDrivenTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setEmail(email);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(phone);

        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("createShopperDataDrivenTest");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test(priority = 20, description = "Create shopper with invalid email")
    public void createShopperWithInvalidEmailTest() {
        logger.startTestCase("createShopperWithInvalidEmailTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail("invalid-email-format");
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createShopperWithInvalidEmailTest");
    }

    @Test(priority = 21, description = "Create shopper with missing required fields")
    public void createShopperWithMissingFieldsTest() {
        logger.startTestCase("createShopperWithMissingFieldsTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        // Missing password and phone

        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createShopperWithMissingFieldsTest");
    }

    @Test(priority = 22, description = "Get shopper with invalid ID")
    public void getShopperWithInvalidIdTest() {
        logger.startTestCase("getShopperWithInvalidIdTest");
        
        Response response = ShopperEndpoints.getShopperById("invalid-shopper-id");
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("getShopperWithInvalidIdTest");
    }

    @Test(priority = 23, description = "Create shopper with password mismatch")
    public void passwordMismatchTest() {
        logger.startTestCase("passwordMismatchTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword("DifferentPassword123!");
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("passwordMismatchTest");
    }

    @Test(priority = 24, description = "Create shopper with invalid phone")
    public void createShopperWithInvalidPhoneTest() {
        logger.startTestCase("createShopperWithInvalidPhoneTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone("123");  // Invalid phone

        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createShopperWithInvalidPhoneTest");
    }

    @Test(priority = 25, description = "Duplicate email registration")
    public void duplicateShopperEmailTest() {
        logger.startTestCase("duplicateShopperEmailTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        String email = FakeDataGenerator.getUniqueEmail();
        payload.setEmail(email);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        // First registration should succeed
        ShopperEndpoints.createShopper(payload);

        // Second registration with same email should fail
        Response response = ShopperEndpoints.createShopper(payload);
        
        Assert.assertEquals(response.getStatusCode(), 409);
        
        logger.endTestCase("duplicateShopperEmailTest");
    }

    @Test(priority = 26, description = "Delete shopper account")
    public void deleteShopperTest() {
        logger.startTestCase("deleteShopperTest");
        
        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.deleteShopper(shopperId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("deleteShopperTest");
    }

    @Test(priority = 27, description = "Unauthorized access to shopper endpoint")
    public void unauthorizedAccessTest() {
        logger.startTestCase("unauthorizedAccessTest");
        
        // Try to access without valid token
        Response response = ShopperEndpoints.getShopperById("some-id");
        
        Assert.assertEquals(response.getStatusCode(), 401);
        
        logger.endTestCase("unauthorizedAccessTest");
    }
}
