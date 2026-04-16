package api.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * CategoryManagementDataProvider - Data provider for category management test cases
 */
public class CategoryManagementDataProvider {

    @DataProvider(name = "categoryData")
    public Object[][] getCategoryData() {
        return new Object[][] {
                {"Electronics", "Electronic devices and accessories", true},
                {"Clothing", "Apparel and fashion items", true},
                {"Books", "Educational and entertainment books", true},
                {"Home Appliances", "Kitchen and household appliances", false}
        };
    }
}
