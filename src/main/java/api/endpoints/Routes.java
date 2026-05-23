package api.endpoints;

public class Routes {

    public static final String BASE_URL = "https://www.shoppersstack.com/shopping";


    public static final String CREATE_ADMIN = BASE_URL + "/admin";
    public static final String GET_ADMIN = BASE_URL + CREATE_ADMIN + "/{adminId}";
    public static final String UPDATE_ADMIN = BASE_URL + CREATE_ADMIN + "/{adminId}";


    public static final String CREATE_MERCHANT = BASE_URL + "/merchants";
    public static final String GET_MERCHANT = CREATE_MERCHANT;
    public static final String UPDATE_MERCHANT = CREATE_MERCHANT + "/{merchantId}";


    public static final String UPDATE_MERCHANT_STATUS = UPDATE_MERCHANT + "/status";
    public static final String GET_MERCHANT_ZONEID = UPDATE_MERCHANT_STATUS + "/zoneId";


    public static final String PRODUCT = BASE_URL + "/products";
    public static final String SINGLE_PRODUCT = PRODUCT + "/{productId}";
    public static final String MERCHANT_PRODUCT = PRODUCT + "/merchants" + "/{merchantId}";
    public static final String ALL_PRODUCT = PRODUCT + "/alpha";


    public static final String POST_SHOPPER = BASE_URL + "/shoppers";
    public static final String GET_SHOPPER = POST_SHOPPER + "/{shopperId}";
    public static final String UPDATE_SHOPPER = POST_SHOPPER + "/{shopperId}";


    public static final String USERS = BASE_URL + "/users";
    public static final String LOGIN = USERS + "/login";
    public static final String FORGOT_PASSWORD = USERS + "/forgot-password";
    public static final String RESET_PASSWORD = USERS + "/reset-password";
    public static final String VERIFY_ACCOUNT = USERS + "/verify-account";


    public static final String POST_SHOPPER_ADDRESS = GET_SHOPPER + "/address";
    public static final String GET_SHOPPER_ADDRESS = POST_SHOPPER_ADDRESS + "/{addressId}";


    public static final String POST_SHOPPER_BANK_ACCOUNT = BASE_URL + "/bankaccounts";
    public static final String LOGIN_SHOPPER_BANK_ACCOUNT = POST_SHOPPER_BANK_ACCOUNT + "/login";


    public static final String POST_SHOPPER_BANK_CARD = BASE_URL + "/cards";
    public static final String SHOPPER_BANK_CARDS_TRANSACTION = POST_SHOPPER_BANK_CARD + "/transaction";
    public static final String VERIFY_SHOPPER_BANK_CARD = POST_SHOPPER_BANK_CARD + "/verify";


    public static final String POST_SHOPPER_PROFILE_CARD = BASE_URL + "/cards";
    public static final String GET_SHOPPER_PROFILE_CARD = POST_SHOPPER_PROFILE_CARD + "/{shopperId}";
    public static final String DELETE_SHOPPER_PROFILE_CARD = POST_SHOPPER_PROFILE_CARD + "/{cardId}";
    public static final String GET_ALL_PROFILE_CARDS = BASE_URL + "/shoppers/cards";


    public static final String POST_SHOPPER_WISHLIST = GET_SHOPPER + "/wishlist";
    public static final String GET_SHOPPER_WISHLIST = GET_SHOPPER + "/wishlist";
    public static final String DELETE_SHOPPER_WISHLIST = POST_SHOPPER_WISHLIST + "/{productId}";


    public static final String POST_SHOPPER_CART = GET_SHOPPER + "/carts";
    public static final String GET_SHOPPER_CART = GET_SHOPPER + "/carts";
    public static final String DELETE_SHOPPER_CART = GET_SHOPPER_CART + "/{itemId}";
    public static final String UPDATE_SHOPPER_CART = GET_SHOPPER_CART + "/{productId}";


    public static final String POST_ORDER = GET_SHOPPER + "/orders";
    public static final String GET_ORDER = GET_SHOPPER + "/orders";
    public static final String UPDATE_ORDER = GET_ORDER + "/{orderId}";
    public static final String GET_ORDER_INVOICE = UPDATE_ORDER + "/invoice";


    public static final String POST_REVIEW = BASE_URL + "/reviews";
    public static final String GET_REVIEW = POST_REVIEW + "/{productId}";
    public static final String UPDATE_REVIEW = POST_REVIEW + "/{reviewId}";
    public static final String DELETE_REVIEW = POST_REVIEW + "/{reviewId}";
}

