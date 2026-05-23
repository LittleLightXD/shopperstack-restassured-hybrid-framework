package api.testcases;

import api.base.BaseTest;
import api.dataproviders.OrderManagementDataProvider;
import api.endpoints.OrderManagementEndpoints;
import api.logging.CustomLogger;
import api.payload.OrderManagementPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderManagementTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();



    @Test(priority = 1, description = "Get all orders")
    public void getAllOrdersTest() {
        logger.startTestCase("getAllOrdersTest");

        Response response = OrderManagementEndpoints.getAllOrders();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getAllOrdersTest");
    }

    @Test(priority = 2, description = "Get order by ID")
    public void getOrderByIdTest() {
        logger.startTestCase("getOrderByIdTest");

        Response response = OrderManagementEndpoints.getOrderById("order-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrderByIdTest");
    }

    @Test(priority = 3, description = "Create new order")
    public void createOrderTest() {
        logger.startTestCase("createOrderTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCustomerId("customer-123");
        payload.setOrderDate("2024-04-07");
        payload.setStatus("PENDING");
        payload.setTotalAmount(499.99);

        Response response = OrderManagementEndpoints.createOrder(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createOrderTest");
    }

    @Test(priority = 4, description = "Update order status")
    public void updateOrderStatusTest() {
        logger.startTestCase("updateOrderStatusTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setStatus("CONFIRMED");

        Response response = OrderManagementEndpoints.updateOrderStatus("order-123", payload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("updateOrderStatusTest");
    }

    @Test(priority = 5, description = "Cancel order")
    public void cancelOrderTest() {
        logger.startTestCase("cancelOrderTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCancellationReason("Customer requested cancellation");

        Response response = OrderManagementEndpoints.cancelOrder("order-123", payload);

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("cancelOrderTest");
    }

    @Test(priority = 6, description = "Get orders by customer")
    public void getOrdersByCustomerTest() {
        logger.startTestCase("getOrdersByCustomerTest");

        Response response = OrderManagementEndpoints.getOrdersByCustomer("customer-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrdersByCustomerTest");
    }

    @Test(priority = 7, description = "Get orders by date range")
    public void getOrdersByDateRangeTest() {
        logger.startTestCase("getOrdersByDateRangeTest");

        Response response = OrderManagementEndpoints.getOrdersByDateRange("2024-01-01", "2024-12-31");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrdersByDateRangeTest");
    }

    @Test(priority = 8, description = "Get order items")
    public void getOrderItemsTest() {
        logger.startTestCase("getOrderItemsTest");

        Response response = OrderManagementEndpoints.getOrderItems("order-123");

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.endTestCase("getOrderItemsTest");
    }

    @Test(priority = 9, description = "Add order item")
    public void addOrderItemTest() {
        logger.startTestCase("addOrderItemTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setProductId("product-456");
        payload.setQuantity(2);
        payload.setPrice(149.99);

        Response response = OrderManagementEndpoints.addOrderItem("order-123", payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("addOrderItemTest");
    }



    @Test(priority = 10, dataProvider = "orderData", dataProviderClass = OrderManagementDataProvider.class,
            description = "Create order with data provider")
    public void createOrderDataDrivenTest(String customerId, String status, double totalAmount) {
        logger.startTestCase("createOrderDataDrivenTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCustomerId(customerId);
        payload.setStatus(status);
        payload.setTotalAmount(totalAmount);

        Response response = OrderManagementEndpoints.createOrder(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

        logger.endTestCase("createOrderDataDrivenTest");
    }



    @Test(priority = 20, description = "Get non-existent order")
    public void getNonExistentOrderTest() {
        logger.startTestCase("getNonExistentOrderTest");

        Response response = OrderManagementEndpoints.getOrderById("non-existent-id");

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("getNonExistentOrderTest");
    }

    @Test(priority = 21, description = "Create order with missing customer ID")
    public void createOrderWithMissingCustomerIdTest() {
        logger.startTestCase("createOrderWithMissingCustomerIdTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setStatus("PENDING");
        payload.setTotalAmount(499.99);

        Response response = OrderManagementEndpoints.createOrder(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createOrderWithMissingCustomerIdTest");
    }

    @Test(priority = 22, description = "Create order with invalid customer")
    public void createOrderWithInvalidCustomerTest() {
        logger.startTestCase("createOrderWithInvalidCustomerTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCustomerId("invalid-customer-id");
        payload.setStatus("PENDING");
        payload.setTotalAmount(499.99);

        Response response = OrderManagementEndpoints.createOrder(payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("createOrderWithInvalidCustomerTest");
    }

    @Test(priority = 23, description = "Cancel already cancelled order")
    public void cancelAlreadyCancelledOrderTest() {
        logger.startTestCase("cancelAlreadyCancelledOrderTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCancellationReason("Already cancelled");

        Response response = OrderManagementEndpoints.cancelOrder("cancelled-order-id", payload);

        Assert.assertEquals(response.getStatusCode(), 409);

        logger.endTestCase("cancelAlreadyCancelledOrderTest");
    }

    @Test(priority = 24, description = "Update order with invalid status")
    public void updateOrderWithInvalidStatusTest() {
        logger.startTestCase("updateOrderWithInvalidStatusTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setStatus("INVALID_STATUS");

        Response response = OrderManagementEndpoints.updateOrderStatus("order-123", payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("updateOrderWithInvalidStatusTest");
    }

    @Test(priority = 25, description = "Add item to non-existent order")
    public void addItemToNonExistentOrderTest() {
        logger.startTestCase("addItemToNonExistentOrderTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setProductId("product-456");
        payload.setQuantity(2);

        Response response = OrderManagementEndpoints.addOrderItem("non-existent-order-id", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("addItemToNonExistentOrderTest");
    }

    @Test(priority = 26, description = "Create order with negative amount")
    public void createOrderWithNegativeAmountTest() {
        logger.startTestCase("createOrderWithNegativeAmountTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setCustomerId("customer-123");
        payload.setStatus("PENDING");
        payload.setTotalAmount(-100.00);

        Response response = OrderManagementEndpoints.createOrder(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

        logger.endTestCase("createOrderWithNegativeAmountTest");
    }

    @Test(priority = 27, description = "Add item with invalid product")
    public void addItemWithInvalidProductTest() {
        logger.startTestCase("addItemWithInvalidProductTest");

        OrderManagementPayload payload = new OrderManagementPayload();
        payload.setProductId("invalid-product-id");
        payload.setQuantity(2);

        Response response = OrderManagementEndpoints.addOrderItem("order-123", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

        logger.endTestCase("addItemWithInvalidProductTest");
    }
}

