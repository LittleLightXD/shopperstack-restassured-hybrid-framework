package api.endpoints;

import api.payload.ShopperPayload;
import api.specs.ReusableRequestSpec;
import api.utils.TokenManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ShopperEndpoints - REST API endpoints for Shopper module
 * Provides all shopper-related operations (CRUD, address, bank account, wishlist, cart)
 */
public class ShopperEndpoints {

    /**
     * Create a new shopper
     */
    public static Response createShopper(ShopperPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER)
                .then()
                .extract()
                .response();
    }

    /**
     * Create shopper and extract shopper ID for API chaining
     */
    public static String createShopperAndGetId(ShopperPayload payload) {
        Response response = createShopper(payload);
        return response.jsonPath().getString("shopperId");
    }

    /**
     * Get shopper by ID
     */
    public static Response getShopperById(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Update shopper details
     */
    public static Response updateShopper(String shopperId, ShopperPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(payload)
                .when()
                .put(Routes.UPDATE_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Delete shopper account
     */
    public static Response deleteShopper(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.UPDATE_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Add address to shopper account
     */
    public static Response addShopperAddress(String shopperId, ShopperPayload.AddressDetails addressDetails) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(addressDetails)
                .when()
                .post(Routes.POST_SHOPPER_ADDRESS.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get shopper address by ID
     */
    public static Response getShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }

    /**
     * Update shopper address
     */
    public static Response updateShopperAddress(String shopperId, String addressId, ShopperPayload.AddressDetails addressDetails) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(addressDetails)
                .when()
                .put(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }

    /**
     * Delete shopper address
     */
    public static Response deleteShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }

    /**
     * Add bank account to shopper
     */
    public static Response addBankAccount(ShopperPayload.BankAccountDetails bankDetails) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .body(bankDetails)
                .when()
                .post(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }

    /**
     * Add wishlist item for shopper
     */
    public static Response addToWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("productId", productId)
                .when()
                .post(Routes.POST_SHOPPER_WISHLIST.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get shopper wishlist
     */
    public static Response getWishlist(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_SHOPPER_WISHLIST.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Remove from wishlist
     */
    public static Response removeFromWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.DELETE_SHOPPER_WISHLIST.replace("{shopperId}", shopperId)
                        .replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Add item to cart
     */
    public static Response addToCart(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("productId", productId)
                .queryParam("quantity", quantity)
                .when()
                .post(Routes.POST_SHOPPER_CART.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Get shopper cart
     */
    public static Response getCart(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .get(Routes.GET_SHOPPER_CART.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }

    /**
     * Update cart item quantity
     */
    public static Response updateCartItem(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .queryParam("quantity", quantity)
                .when()
                .put(Routes.UPDATE_SHOPPER_CART.replace("{shopperId}", shopperId)
                        .replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    /**
     * Remove item from cart
     */
    public static Response removeFromCart(String shopperId, String itemId) {
        return given()
                .spec(ReusableRequestSpec.buildAuthenticatedRequestSpec(TokenManager.getToken()))
                .when()
                .delete(Routes.DELETE_SHOPPER_CART.replace("{shopperId}", shopperId)
                        .replace("{itemId}", itemId))
                .then()
                .extract()
                .response();
    }
}
