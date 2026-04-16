package api.utils;

import com.github.javafaker.Faker;
import java.util.UUID;

/**
 * FakeDataGenerator class to generate random test data using JavaFaker
 */
public class FakeDataGenerator {

    private static Faker faker = new Faker();

    /**
     * Generate random first name
     */
    public static String getFirstName() {
        return faker.name().firstName();
    }

    /**
     * Generate random last name
     */
    public static String getLastName() {
        return faker.name().lastName();
    }

    /**
     * Generate random email address
     */
    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generate random unique email address
     */
    public static String getUniqueEmail() {
        return "user" + System.currentTimeMillis() + "@" + faker.internet().domainName();
    }

    /**
     * Generate random password
     */
    public static String getPassword() {
        return faker.internet().password(8, 16, true, true);
    }

    /**
     * Generate strong password with special characters
     */
    public static String getStrongPassword() {
        return faker.internet().password(10, 20, true, true) + "!@#";
    }

    /**
     * Generate random phone number
     */
    public static String getPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    /**
     * Generate random username
     */
    public static String getUsername() {
        return faker.name().username();
    }

    /**
     * Generate random company name
     */
    public static String getCompanyName() {
        return faker.company().name();
    }

    /**
     * Generate random address
     */
    public static String getAddress() {
        return faker.address().fullAddress();
    }

    /**
     * Generate random city
     */
    public static String getCity() {
        return faker.address().city();
    }

    /**
     * Generate random country
     */
    public static String getCountry() {
        return faker.address().country();
    }

    /**
     * Generate random postal code
     */
    public static String getPostalCode() {
        return faker.address().zipCode();
    }

    /**
     * Generate random UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate random number between 0 and max
     */
    public static int getRandomNumber(int max) {
        return faker.number().numberBetween(1, max);
    }

    /**
     * Generate random boolean
     */
    public static boolean getRandomBoolean() {
        return faker.bool().bool();
    }
}