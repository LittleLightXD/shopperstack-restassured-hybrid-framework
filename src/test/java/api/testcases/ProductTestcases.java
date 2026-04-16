package api.testcases;

import api.base.BaseTest;
import api.dataproviders.ProductDataProvider;
import api.endpoints.ProductEndpoints;
import api.logging.CustomLogger;
import api.payload.ProductPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ProductTestcases - Comprehensive test scenarios for Product module
 * Covers positive, negative, and data-driven test cases for product CRUD operations,
 * filtering, searching, and inventory management
 */
public class ProductTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();

    // ==================== POSITIVE TEST CASES ====================

    @Test(priority = 1, description = "Create product with valid data")
    public void createProductWithValidDataTest() {
        logger.startTestCase("createProductWithValidDataTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setDescription("High quality test product");
        payload.setPrice(299.99);
        payload.setDiscountPrice(199.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");
        payload.setSubcategory("MOBILE_PHONES");
        payload.setBrand("TestBrand");
        payload.setMerchantId("merchant-123456");

        Response response = ProductEndpoints.createProduct(payload);
        logger.logAPIResponse(response.getStatusCode(), response.getBody().asString());
        
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("productId"));
        
        logger.endTestCase("createProductWithValidDataTest");
    }

    @Test(priority = 2, description = "Get product by ID")
    public void getProductByIdTest() {
        logger.startTestCase("getProductByIdTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setDescription("High quality test product");
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(payload);
        Response response = ProductEndpoints.getProductById(productId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("productId"), productId);
        
        logger.endTestCase("getProductByIdTest");
    }

    @Test(priority = 3, description = "Get all products")
    public void getAllProductsTest() {
        logger.startTestCase("getAllProductsTest");
        
        Response response = ProductEndpoints.getAllProducts(1, 10, "productName");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getAllProductsTest");
    }

    @Test(priority = 4, description = "Get products by merchant")
    public void getProductsByMerchantTest() {
        logger.startTestCase("getProductsByMerchantTest");
        
        Response response = ProductEndpoints.getProductsByMerchant("merchant-123456");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getProductsByMerchantTest");
    }

    @Test(priority = 5, description = "Update product details")
    public void updateProductTest() {
        logger.startTestCase("updateProductTest");
        
        ProductPayload createPayload = new ProductPayload();
        createPayload.setProductName("Test Product " + System.currentTimeMillis());
        createPayload.setDescription("Initial description");
        createPayload.setPrice(299.99);
        createPayload.setQuantity(100);
        createPayload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(createPayload);

        ProductPayload updatePayload = new ProductPayload();
        updatePayload.setProductName("Updated Product Name");
        updatePayload.setDescription("Updated description");
        updatePayload.setPrice(249.99);

        Response response = ProductEndpoints.updateProduct(productId, updatePayload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("updateProductTest");
    }

    @Test(priority = 6, description = "Search products")
    public void searchProductsTest() {
        logger.startTestCase("searchProductsTest");
        
        Response response = ProductEndpoints.searchProducts("Electronics");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("searchProductsTest");
    }

    @Test(priority = 7, description = "Filter products by category")
    public void filterProductsByCategoryTest() {
        logger.startTestCase("filterProductsByCategoryTest");
        
        Response response = ProductEndpoints.filterProductsByCategory("ELECTRONICS");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("filterProductsByCategoryTest");
    }

    @Test(priority = 8, description = "Filter products by price range")
    public void filterProductsByPriceTest() {
        logger.startTestCase("filterProductsByPriceTest");
        
        Response response = ProductEndpoints.filterProductsByPrice(100.0, 500.0);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("filterProductsByPriceTest");
    }

    @Test(priority = 9, description = "Get in-stock products")
    public void getInStockProductsTest() {
        logger.startTestCase("getInStockProductsTest");
        
        Response response = ProductEndpoints.getInStockProducts();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getInStockProductsTest");
    }

    // ==================== DATA-DRIVEN TEST CASES ====================

    @Test(priority = 10, dataProvider = "validProductData", dataProviderClass = ProductDataProvider.class,
            description = "Create product with data provider")
    public void createProductDataDrivenTest(String productName, Double price, Integer quantity, String category) {
        logger.startTestCase("createProductDataDrivenTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName(productName);
        payload.setPrice(price);
        payload.setQuantity(quantity);
        payload.setCategory(category);

        Response response = ProductEndpoints.createProduct(payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("createProductDataDrivenTest");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test(priority = 20, description = "Create product with missing product name")
    public void createProductWithMissingNameTest() {
        logger.startTestCase("createProductWithMissingNameTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setDescription("Product without name");
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createProductWithMissingNameTest");
    }

    @Test(priority = 21, description = "Create product with invalid price")
    public void createProductWithInvalidPriceTest() {
        logger.startTestCase("createProductWithInvalidPriceTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(-50.0);  // Invalid negative price
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createProductWithInvalidPriceTest");
    }

    @Test(priority = 22, description = "Create product with invalid quantity")
    public void createProductWithInvalidQuantityTest() {
        logger.startTestCase("createProductWithInvalidQuantityTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(299.99);
        payload.setQuantity(-10);  // Invalid negative quantity
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createProductWithInvalidQuantityTest");
    }

    @Test(priority = 23, description = "Get product with invalid ID")
    public void getProductWithInvalidIdTest() {
        logger.startTestCase("getProductWithInvalidIdTest");
        
        Response response = ProductEndpoints.getProductById("invalid-product-id");
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("getProductWithInvalidIdTest");
    }

    @Test(priority = 24, description = "Update product that does not exist")
    public void updateNonExistentProductTest() {
        logger.startTestCase("updateNonExistentProductTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Updated Product");
        payload.setPrice(199.99);

        Response response = ProductEndpoints.updateProduct("non-existent-id", payload);
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("updateNonExistentProductTest");
    }

    @Test(priority = 25, description = "Delete product")
    public void deleteProductTest() {
        logger.startTestCase("deleteProductTest");
        
        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(payload);
        Response response = ProductEndpoints.deleteProduct(productId);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("deleteProductTest");
    }

    @Test(priority = 26, description = "Filter products by brand")
    public void filterProductsByBrandTest() {
        logger.startTestCase("filterProductsByBrandTest");
        
        Response response = ProductEndpoints.filterProductsByBrand("Samsung");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("filterProductsByBrandTest");
    }

    @Test(priority = 27, description = "Filter products by rating")
    public void filterProductsByRatingTest() {
        logger.startTestCase("filterProductsByRatingTest");
        
        Response response = ProductEndpoints.filterProductsByRating(4.0);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("filterProductsByRatingTest");
    }

    @Test(priority = 28, description = "Get out of stock products")
    public void getOutOfStockProductsTest() {
        logger.startTestCase("getOutOfStockProductsTest");
        
        Response response = ProductEndpoints.getOutOfStockProducts();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getOutOfStockProductsTest");
    }
}
