package api.testcases;

import api.base.BaseTest;
import api.dataproviders.ProductManagementDataProvider;
import api.endpoints.ProductManagementEndpoints;
import api.logging.CustomLogger;
import api.payload.ProductManagementPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductManagementTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();



    @Test(priority = 1, description = "Get all products")
    public void getAllProductsTest() {
        logger.startTestCase("getAllProductsTest");

        Response response = ProductManagementEndpoints.getAllProducts();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getAllProductsTest");
    }

    @Test(priority = 2, description = "Get product by ID")
    public void getProductByIdTest() {
        logger.startTestCase("getProductByIdTest");

        Response response = ProductManagementEndpoints.getProductById("product-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getProductByIdTest");
    }

    @Test(priority = 3, description = "Create new product")
    public void createProductTest() {
        logger.startTestCase("createProductTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Laptop");
        payload.setSku("LAPTOP-001");
        payload.setPrice(999.99);
        payload.setQuantity(100);
        payload.setCategoryId("cat-123");

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createProductTest");
    }

    @Test(priority = 4, description = "Update product")
    public void updateProductTest() {
        logger.startTestCase("updateProductTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Laptop Pro");
        payload.setPrice(1299.99);

        Response response = ProductManagementEndpoints.updateProduct("product-123", payload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("updateProductTest");
    }

    @Test(priority = 5, description = "Delete product")
    public void deleteProductTest() {
        logger.startTestCase("deleteProductTest");

        Response response = ProductManagementEndpoints.deleteProduct("product-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("deleteProductTest");
    }

    @Test(priority = 6, description = "Get products by category")
    public void getProductsByCategoryTest() {
        logger.startTestCase("getProductsByCategoryTest");

        Response response = ProductManagementEndpoints.getProductsByCategory("cat-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getProductsByCategoryTest");
    }

    @Test(priority = 7, description = "Search products")
    public void searchProductsTest() {
        logger.startTestCase("searchProductsTest");

        Response response = ProductManagementEndpoints.searchProducts("laptop");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("searchProductsTest");
    }

    @Test(priority = 8, description = "Update product inventory")
    public void updateProductInventoryTest() {
        logger.startTestCase("updateProductInventoryTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setQuantity(150);

        Response response = ProductManagementEndpoints.updateProductInventory("product-123", payload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("updateProductInventoryTest");
    }

    @Test(priority = 9, description = "Add product image")
    public void addProductImageTest() {
        logger.startTestCase("addProductImageTest");

        Response response = ProductManagementEndpoints.addProductImage("product-123", "image-url.jpg");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("addProductImageTest");
    }



    @Test(priority = 10, dataProvider = "productData", dataProviderClass = ProductManagementDataProvider.class,
            description = "Create product with data provider")
    public void createProductDataDrivenTest(String productName, String sku, double price, int quantity) {
        logger.startTestCase("createProductDataDrivenTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName(productName);
        payload.setSku(sku);
        payload.setPrice(price);
        payload.setQuantity(quantity);

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createProductDataDrivenTest");
    }



    @Test(priority = 20, description = "Get non-existent product")
    public void getNonExistentProductTest() {
        logger.startTestCase("getNonExistentProductTest");

        Response response = ProductManagementEndpoints.getProductById("non-existent-id");

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("getNonExistentProductTest");
    }

    @Test(priority = 21, description = "Create product with missing name")
    public void createProductWithMissingNameTest() {
        logger.startTestCase("createProductWithMissingNameTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setSku("PROD-001");
        payload.setPrice(99.99);

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createProductWithMissingNameTest");
    }

    @Test(priority = 22, description = "Create product with duplicate SKU")
    public void createProductWithDuplicateSkuTest() {
        logger.startTestCase("createProductWithDuplicateSkuTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Duplicate Product");
        payload.setSku("LAPTOP-001");
        payload.setPrice(999.99);

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 409);

        logger.endTestCase("createProductWithDuplicateSkuTest");
    }

    @Test(priority = 23, description = "Create product with negative price")
    public void createProductWithNegativePriceTest() {
        logger.startTestCase("createProductWithNegativePriceTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Invalid Product");
        payload.setSku("INVALID-001");
        payload.setPrice(-50.00);

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createProductWithNegativePriceTest");
    }

    @Test(priority = 24, description = "Update non-existent product")
    public void updateNonExistentProductTest() {
        logger.startTestCase("updateNonExistentProductTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Updated Name");

        Response response = ProductManagementEndpoints.updateProduct("non-existent-id", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("updateNonExistentProductTest");
    }

    @Test(priority = 25, description = "Delete non-existent product")
    public void deleteNonExistentProductTest() {
        logger.startTestCase("deleteNonExistentProductTest");

        Response response = ProductManagementEndpoints.deleteProduct("non-existent-id");

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("deleteNonExistentProductTest");
    }

    @Test(priority = 26, description = "Update with invalid quantity")
    public void updateProductWithInvalidQuantityTest() {
        logger.startTestCase("updateProductWithInvalidQuantityTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setQuantity(-50);

        Response response = ProductManagementEndpoints.updateProductInventory("product-123", payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("updateProductWithInvalidQuantityTest");
    }

    @Test(priority = 27, description = "Create product with invalid category")
    public void createProductWithInvalidCategoryTest() {
        logger.startTestCase("createProductWithInvalidCategoryTest");

        ProductManagementPayload payload = new ProductManagementPayload();
        payload.setProductName("Product");
        payload.setSku("PROD-001");
        payload.setPrice(99.99);
        payload.setCategoryId("invalid-cat-id");

        Response response = ProductManagementEndpoints.createProduct(payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("createProductWithInvalidCategoryTest");
    }
}

