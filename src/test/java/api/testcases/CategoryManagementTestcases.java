package api.testcases;

import api.base.BaseTest;
import api.dataproviders.CategoryManagementDataProvider;
import api.endpoints.CategoryManagementEndpoints;
import api.logging.CustomLogger;
import api.payload.CategoryManagementPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CategoryManagementTestcases - Test scenarios for Category Management module
 * Covers category CRUD operations, subcategories, and category settings
 */
public class CategoryManagementTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();

    // ==================== POSITIVE TEST CASES ====================

    @Test(priority = 1, description = "Get all categories")
    public void getAllCategoriesTest() {
        logger.startTestCase("getAllCategoriesTest");
        
        Response response = CategoryManagementEndpoints.getAllCategories();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getAllCategoriesTest");
    }

    @Test(priority = 2, description = "Get category by ID")
    public void getCategoryByIdTest() {
        logger.startTestCase("getCategoryByIdTest");
        
        Response response = CategoryManagementEndpoints.getCategoryById("category-123");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getCategoryByIdTest");
    }

    @Test(priority = 3, description = "Create new category")
    public void createCategoryTest() {
        logger.startTestCase("createCategoryTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName("Electronics");
        payload.setDescription("Electronic devices and accessories");
        payload.setIsActive(true);

        Response response = CategoryManagementEndpoints.createCategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("createCategoryTest");
    }

    @Test(priority = 4, description = "Update category")
    public void updateCategoryTest() {
        logger.startTestCase("updateCategoryTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName("Electronics Updated");
        payload.setDescription("Updated electronics category");

        Response response = CategoryManagementEndpoints.updateCategory("category-123", payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("updateCategoryTest");
    }

    @Test(priority = 5, description = "Create subcategory")
    public void createSubcategoryTest() {
        logger.startTestCase("createSubcategoryTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setSubcategoryName("Laptops");
        payload.setParentCategoryId("category-123");

        Response response = CategoryManagementEndpoints.createSubcategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("createSubcategoryTest");
    }

    @Test(priority = 6, description = "Get subcategories")
    public void getSubcategoriesTest() {
        logger.startTestCase("getSubcategoriesTest");
        
        Response response = CategoryManagementEndpoints.getSubcategories("category-123");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getSubcategoriesTest");
    }

    @Test(priority = 7, description = "Activate category")
    public void activateCategoryTest() {
        logger.startTestCase("activateCategoryTest");
        
        Response response = CategoryManagementEndpoints.activateCategory("category-123");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("activateCategoryTest");
    }

    @Test(priority = 8, description = "Deactivate category")
    public void deactivateCategoryTest() {
        logger.startTestCase("deactivateCategoryTest");
        
        Response response = CategoryManagementEndpoints.deactivateCategory("category-123");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("deactivateCategoryTest");
    }

    // ==================== DATA-DRIVEN TEST CASES ====================

    @Test(priority = 10, dataProvider = "categoryData", dataProviderClass = CategoryManagementDataProvider.class,
            description = "Create category with data provider")
    public void createCategoryDataDrivenTest(String categoryName, String description, boolean isActive) {
        logger.startTestCase("createCategoryDataDrivenTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName(categoryName);
        payload.setDescription(description);
        payload.setIsActive(isActive);

        Response response = CategoryManagementEndpoints.createCategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        
        logger.endTestCase("createCategoryDataDrivenTest");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test(priority = 20, description = "Get non-existent category")
    public void getNonExistentCategoryTest() {
        logger.startTestCase("getNonExistentCategoryTest");
        
        Response response = CategoryManagementEndpoints.getCategoryById("non-existent-id");
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("getNonExistentCategoryTest");
    }

    @Test(priority = 21, description = "Create category with empty name")
    public void createCategoryWithEmptyNameTest() {
        logger.startTestCase("createCategoryWithEmptyNameTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName("");
        payload.setDescription("Missing category name");

        Response response = CategoryManagementEndpoints.createCategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("createCategoryWithEmptyNameTest");
    }

    @Test(priority = 22, description = "Create duplicate category")
    public void createDuplicateCategoryTest() {
        logger.startTestCase("createDuplicateCategoryTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName("Electronics");
        payload.setDescription("Duplicate category");

        Response response = CategoryManagementEndpoints.createCategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 409);
        
        logger.endTestCase("createDuplicateCategoryTest");
    }

    @Test(priority = 23, description = "Delete category")
    public void deleteCategoryTest() {
        logger.startTestCase("deleteCategoryTest");
        
        Response response = CategoryManagementEndpoints.deleteCategory("category-123");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("deleteCategoryTest");
    }

    @Test(priority = 24, description = "Delete category with associated products")
    public void deleteCategoryWithProductsTest() {
        logger.startTestCase("deleteCategoryWithProductsTest");
        
        Response response = CategoryManagementEndpoints.deleteCategory("category-with-products");
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("deleteCategoryWithProductsTest");
    }

    @Test(priority = 25, description = "Update non-existent category")
    public void updateNonExistentCategoryTest() {
        logger.startTestCase("updateNonExistentCategoryTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setCategoryName("Updated Name");

        Response response = CategoryManagementEndpoints.updateCategory("non-existent-id", payload);
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("updateNonExistentCategoryTest");
    }

    @Test(priority = 26, description = "Create subcategory with invalid parent")
    public void createSubcategoryWithInvalidParentTest() {
        logger.startTestCase("createSubcategoryWithInvalidParentTest");
        
        CategoryManagementPayload payload = new CategoryManagementPayload();
        payload.setSubcategoryName("Invalid Subcategory");
        payload.setParentCategoryId("invalid-parent-id");

        Response response = CategoryManagementEndpoints.createSubcategory(payload);
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("createSubcategoryWithInvalidParentTest");
    }
}
