package api.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * OrderManagementDataProvider - Data provider for order management test cases
 */
public class OrderManagementDataProvider {

    @DataProvider(name = "orderData")
    public Object[][] getOrderData() {
        return new Object[][] {
                {"customer-001", "PENDING", 299.99},
                {"customer-002", "CONFIRMED", 499.99},
                {"customer-003", "PROCESSING", 149.99},
                {"customer-004", "SHIPPED", 899.99}
        };
    }
}
