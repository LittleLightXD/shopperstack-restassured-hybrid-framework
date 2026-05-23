package api.dataproviders;

import api.utils.FakeDataGenerator;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "validProductData")
    public Object[][] validProductData() {
        return new Object[][] {
            {"Wireless Headphones " + System.currentTimeMillis(), 299.99, 100, "ELECTRONICS"},
            {"USB-C Cable " + System.currentTimeMillis(), 19.99, 500, "ELECTRONICS"},
            {"Mobile Phone Stand " + System.currentTimeMillis(), 49.99, 250, "ACCESSORIES"},
            {"Laptop Backpack " + System.currentTimeMillis(), 79.99, 150, "ACCESSORIES"},
        };
    }

    @DataProvider(name = "productWithDiscountData")
    public Object[][] productWithDiscountData() {
        return new Object[][] {
            {"Product with 20% Discount " + System.currentTimeMillis(), 500.0, 400.0, 100, "ELECTRONICS"},
            {"Product with 50% Discount " + System.currentTimeMillis(), 200.0, 100.0, 50, "FASHION"},
            {"Product with 30% Discount " + System.currentTimeMillis(), 150.0, 105.0, 200, "ACCESSORIES"},
        };
    }

    @DataProvider(name = "categoryData")
    public Object[][] categoryData() {
        return new Object[][] {
            {"Product A " + System.currentTimeMillis(), 199.99, 100, "ELECTRONICS"},
            {"Product B " + System.currentTimeMillis(), 299.99, 200, "FASHION"},
            {"Product C " + System.currentTimeMillis(), 399.99, 150, "HOME_APPLIANCES"},
            {"Product D " + System.currentTimeMillis(), 99.99, 300, "BEAUTY"},
            {"Product E " + System.currentTimeMillis(), 499.99, 75, "SPORTS"},
        };
    }

    @DataProvider(name = "priceRangeData")
    public Object[][] priceRangeData() {
        return new Object[][] {
            {"Budget Product " + System.currentTimeMillis(), 49.99, 300, "ELECTRONICS"},
            {"Mid-Range Product " + System.currentTimeMillis(), 199.99, 200, "ELECTRONICS"},
            {"Premium Product " + System.currentTimeMillis(), 799.99, 50, "ELECTRONICS"},
            {"Ultra-Premium Product " + System.currentTimeMillis(), 1999.99, 20, "ELECTRONICS"},
        };
    }

    @DataProvider(name = "brandData")
    public Object[][] brandData() {
        return new Object[][] {
            {"Samsung Product " + System.currentTimeMillis(), 299.99, 100, "SAMSUNG"},
            {"Apple Product " + System.currentTimeMillis(), 999.99, 50, "APPLE"},
            {"Sony Product " + System.currentTimeMillis(), 499.99, 75, "SONY"},
            {"LG Product " + System.currentTimeMillis(), 399.99, 100, "LG"},
        };
    }

    @DataProvider(name = "inventoryData")
    public Object[][] inventoryData() {
        return new Object[][] {
            {"In Stock Product " + System.currentTimeMillis(), 199.99, 100, "ELECTRONICS"},
            {"Limited Stock Product " + System.currentTimeMillis(), 299.99, 5, "ELECTRONICS"},
            {"High Stock Product " + System.currentTimeMillis(), 99.99, 1000, "ELECTRONICS"},
            {"Clearance Product " + System.currentTimeMillis(), 49.99, 10, "ELECTRONICS"},
        };
    }

    @DataProvider(name = "subcategoryData")
    public Object[][] subcategoryData() {
        return new Object[][] {
            {"Mobile Phone " + System.currentTimeMillis(), 499.99, 50, "ELECTRONICS"},
            {"Laptop " + System.currentTimeMillis(), 999.99, 30, "ELECTRONICS"},
            {"Tablet " + System.currentTimeMillis(), 399.99, 40, "ELECTRONICS"},
            {"Smart Watch " + System.currentTimeMillis(), 199.99, 100, "ELECTRONICS"},
        };
    }
}

