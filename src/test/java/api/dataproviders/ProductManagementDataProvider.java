package api.dataproviders;

import org.testng.annotations.DataProvider;

public class ProductManagementDataProvider {

    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        return new Object[][] {
                {"Laptop Pro", "LAPTOP-001", 1299.99, 50},
                {"Wireless Mouse", "MOUSE-001", 49.99, 200},
                {"USB-C Cable", "CABLE-001", 19.99, 500},
                {"Notebook", "NOTE-001", 9.99, 1000}
        };
    }
}

