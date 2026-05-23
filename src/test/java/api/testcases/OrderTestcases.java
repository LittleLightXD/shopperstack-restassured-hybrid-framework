package api.testcases;

import api.base.BaseTest;
import api.dataproviders.OrderDataProvider;
import api.endpoints.OrderEndpoints;
import api.logging.CustomLogger;
import api.payload.OrderPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();



    @Test(priority = 1, description = "Create order with valid data")
    public void createOrderWithValidDataTest() {
        logger.startTestCase("createOrderWithValidDataTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setDiscountAmount(100.0);
        payload.setTaxAmount(80.0);
        payload.setShippingCost(50.0);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);
        logger.logAPIResponse(response.getStatusCode(), response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("orderId"));

        logger.endTestCase("createOrderWithValidDataTest");
    }

    @Test(priority = 2, description = "Get all orders for shopper")
    public void getShopperOrdersTest() {
        logger.startTestCase("getShopperOrdersTest");

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getShopperOrders(shopperId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getShopperOrdersTest");
    }

    @Test(priority = 3, description = "Get order by ID")
    public void getOrderByIdTest() {
        logger.startTestCase("getOrderByIdTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.getOrderById(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("orderId"), orderId);

        logger.endTestCase("getOrderByIdTest");
    }

    @Test(priority = 4, description = "Update order details")
    public void updateOrderTest() {
        logger.startTestCase("updateOrderTest");

        String shopperId = "shopper-123456";
        OrderPayload createPayload = new OrderPayload();
        createPayload.setShopperId(shopperId);
        createPayload.setTotalAmount(999.99);
        createPayload.setPaymentMethod("CREDIT_CARD");
        createPayload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, createPayload);

        OrderPayload updatePayload = new OrderPayload();
        updatePayload.setDeliveryAddress("Updated Address");

        Response response = OrderEndpoints.updateOrder(shopperId, orderId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("updateOrderTest");
    }

    @Test(priority = 5, description = "Get order by status")
    public void getOrdersByStatusTest() {
        logger.startTestCase("getOrdersByStatusTest");

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrdersByStatus(shopperId, "PENDING");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrdersByStatusTest");
    }

    @Test(priority = 6, description = "Get order by payment status")
    public void getOrdersByPaymentStatusTest() {
        logger.startTestCase("getOrdersByPaymentStatusTest");

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrdersByPaymentStatus(shopperId, "PAID");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrdersByPaymentStatusTest");
    }

    @Test(priority = 7, description = "Get order invoice")
    public void getOrderInvoiceTest() {
        logger.startTestCase("getOrderInvoiceTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.getOrderInvoice(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrderInvoiceTest");
    }

    @Test(priority = 8, description = "Track order")
    public void trackOrderTest() {
        logger.startTestCase("trackOrderTest");

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.trackOrder(shopperId, "TRACK123456");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("trackOrderTest");
    }



    @Test(priority = 10, dataProvider = "validOrderData", dataProviderClass = OrderDataProvider.class,
            description = "Create order with data provider")
    public void createOrderDataDrivenTest(String shopperId, Double totalAmount, String paymentMethod) {
        logger.startTestCase("createOrderDataDrivenTest");

        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(totalAmount);
        payload.setPaymentMethod(paymentMethod);
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createOrderDataDrivenTest");
    }



    @Test(priority = 20, description = "Create order with missing shopper ID")
    public void createOrderWithMissingShopperIdTest() {
        logger.startTestCase("createOrderWithMissingShopperIdTest");

        String shopperId = "";
        OrderPayload payload = new OrderPayload();
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createOrderWithMissingShopperIdTest");
    }

    @Test(priority = 21, description = "Create order with invalid payment method")
    public void createOrderWithInvalidPaymentMethodTest() {
        logger.startTestCase("createOrderWithInvalidPaymentMethodTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("INVALID_PAYMENT");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createOrderWithInvalidPaymentMethodTest");
    }

    @Test(priority = 22, description = "Get order with invalid order ID")
    public void getOrderWithInvalidIdTest() {
        logger.startTestCase("getOrderWithInvalidIdTest");

        String shopperId = "shopper-123456";
        Response response = OrderEndpoints.getOrderById(shopperId, "invalid-order-id");

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("getOrderWithInvalidIdTest");
    }

    @Test(priority = 23, description = "Cancel order")
    public void cancelOrderTest() {
        logger.startTestCase("cancelOrderTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.cancelOrder(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("cancelOrderTest");
    }

    @Test(priority = 24, description = "Create order with negative amount")
    public void createOrderWithNegativeAmountTest() {
        logger.startTestCase("createOrderWithNegativeAmountTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(-999.99);
        payload.setPaymentMethod("CREDIT_CARD");

        Response response = OrderEndpoints.createOrder(shopperId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createOrderWithNegativeAmountTest");
    }

    @Test(priority = 25, description = "Delete order")
    public void deleteOrderTest() {
        logger.startTestCase("deleteOrderTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.deleteOrder(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("deleteOrderTest");
    }

    @Test(priority = 26, description = "Generate order invoice PDF")
    public void generateInvoicePDFTest() {
        logger.startTestCase("generateInvoicePDFTest");

        String shopperId = "shopper-123456";
        OrderPayload payload = new OrderPayload();
        payload.setShopperId(shopperId);
        payload.setTotalAmount(999.99);
        payload.setPaymentMethod("CREDIT_CARD");
        payload.setDeliveryAddress(FakeDataGenerator.getAddress());

        String orderId = OrderEndpoints.createOrderAndGetId(shopperId, payload);
        Response response = OrderEndpoints.generateInvoicePDF(shopperId, orderId);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("generateInvoicePDFTest");
    }
}

