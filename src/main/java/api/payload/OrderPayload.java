package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * OrderPayload - POJO for Order API request/response
 * Contains all order-related fields including items, pricing, and delivery details
 */
public class OrderPayload {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("shopperId")
    private String shopperId;

    @JsonProperty("orderDate")
    private String orderDate;

    @JsonProperty("orderStatus")
    private String orderStatus;

    @JsonProperty("totalAmount")
    private Double totalAmount;

    @JsonProperty("discountAmount")
    private Double discountAmount;

    @JsonProperty("taxAmount")
    private Double taxAmount;

    @JsonProperty("shippingCost")
    private Double shippingCost;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("paymentStatus")
    private String paymentStatus;

    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @JsonProperty("deliveryDate")
    private String deliveryDate;

    @JsonProperty("trackingNumber")
    private String trackingNumber;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("items")
    private List<OrderItem> items;

    @JsonProperty("createdDateTime")
    private String createdDateTime;

    @JsonProperty("updatedDateTime")
    private String updatedDateTime;

    // Nested OrderItem class
    public static class OrderItem {
        @JsonProperty("productId")
        private String productId;

        @JsonProperty("productName")
        private String productName;

        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("discount")
        private Double discount;

        @JsonProperty("totalPrice")
        private Double totalPrice;

        // Constructors
        public OrderItem() {}

        public OrderItem(String productId, Integer quantity, Double price) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
        }

        // Getters and Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getDiscount() {
            return discount;
        }

        public void setDiscount(Double discount) {
            this.discount = discount;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    // Nested ShippingDetails class
    public static class ShippingDetails {
        @JsonProperty("shippingAddress")
        private String shippingAddress;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("country")
        private String country;

        @JsonProperty("zipCode")
        private String zipCode;

        @JsonProperty("shippingMethod")
        private String shippingMethod;

        @JsonProperty("estimatedDelivery")
        private String estimatedDelivery;

        // Constructors
        public ShippingDetails() {}

        public ShippingDetails(String shippingAddress, String city, String zipCode) {
            this.shippingAddress = shippingAddress;
            this.city = city;
            this.zipCode = zipCode;
        }

        // Getters and Setters
        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public String getEstimatedDelivery() {
            return estimatedDelivery;
        }

        public void setEstimatedDelivery(String estimatedDelivery) {
            this.estimatedDelivery = estimatedDelivery;
        }
    }

    // Constructors
    public OrderPayload() {}

    public OrderPayload(String shopperId, Double totalAmount, String paymentMethod) {
        this.shopperId = shopperId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
