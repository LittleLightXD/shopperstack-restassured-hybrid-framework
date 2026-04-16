package api.dataproviders;

import api.utils.FakeDataGenerator;
import org.testng.annotations.DataProvider;

/**
 * OrderDataProvider - Test data for Order module test cases
 * Provides realistic test data variations for different order scenarios
 */
public class OrderDataProvider {

    @DataProvider(name = "validOrderData")
    public Object[][] validOrderData() {
        return new Object[][] {
            {"shopper-001", 999.99, "CREDIT_CARD"},
            {"shopper-002", 1599.99, "DEBIT_CARD"},
            {"shopper-003", 2499.99, "NET_BANKING"},
            {"shopper-004", 599.99, "UPI"},
        };
    }

    @DataProvider(name = "orderWithPaymentMethodData")
    public Object[][] orderWithPaymentMethodData() {
        return new Object[][] {
            {"shopper-001", 999.99, "CREDIT_CARD"},
            {"shopper-002", 1199.99, "DEBIT_CARD"},
            {"shopper-003", 1599.99, "NET_BANKING"},
            {"shopper-004", 799.99, "UPI"},
            {"shopper-005", 899.99, "WALLET"},
        };
    }

    @DataProvider(name = "orderWithDiscountData")
    public Object[][] orderWithDiscountData() {
        return new Object[][] {
            {"shopper-001", 1000.0, 100.0, "CREDIT_CARD"},  // 10% discount
            {"shopper-002", 1500.0, 300.0, "DEBIT_CARD"},   // 20% discount
            {"shopper-003", 2000.0, 200.0, "NET_BANKING"},  // 10% discount
            {"shopper-004", 500.0, 50.0, "UPI"},            // 10% discount
        };
    }

    @DataProvider(name = "orderStatusData")
    public Object[][] orderStatusData() {
        return new Object[][] {
            {"shopper-001", 999.99, "PENDING"},
            {"shopper-002", 1199.99, "CONFIRMED"},
            {"shopper-003", 1599.99, "SHIPPED"},
            {"shopper-004", 799.99, "DELIVERED"},
            {"shopper-005", 599.99, "CANCELLED"},
        };
    }

    @DataProvider(name = "orderPaymentStatusData")
    public Object[][] orderPaymentStatusData() {
        return new Object[][] {
            {"shopper-001", 999.99, "PENDING"},
            {"shopper-002", 1199.99, "PAID"},
            {"shopper-003", 1599.99, "FAILED"},
            {"shopper-004", 799.99, "REFUNDED"},
        };
    }

    @DataProvider(name = "orderWithShippingData")
    public Object[][] orderWithShippingData() {
        return new Object[][] {
            {"shopper-001", 999.99, 50.0, "STANDARD"},
            {"shopper-002", 1199.99, 100.0, "EXPRESS"},
            {"shopper-003", 1599.99, 150.0, "OVERNIGHT"},
            {"shopper-004", 799.99, 30.0, "PICKUP"},
        };
    }

    @DataProvider(name = "orderWithTaxData")
    public Object[][] orderWithTaxData() {
        return new Object[][] {
            {"shopper-001", 1000.0, 100.0, "CREDIT_CARD"},  // 10% tax
            {"shopper-002", 1000.0, 80.0, "DEBIT_CARD"},    // 8% tax
            {"shopper-003", 1000.0, 120.0, "NET_BANKING"},  // 12% tax
            {"shopper-004", 1000.0, 50.0, "UPI"},           // 5% tax
        };
    }
}
