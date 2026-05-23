package api.utils;

import com.github.javafaker.Faker;
import java.util.UUID;

public class FakeDataGenerator {

    private static Faker faker = new Faker();


    public static String getFirstName() {
        return faker.name().firstName();
    }


    public static String getLastName() {
        return faker.name().lastName();
    }


    public static String getEmail() {
        return faker.internet().emailAddress();
    }


    public static String getUniqueEmail() {
        return "user" + System.currentTimeMillis() + "@" + faker.internet().domainName();
    }


    public static String getPassword() {
        return faker.internet().password(8, 16, true, true);
    }


    public static String getStrongPassword() {
        return faker.internet().password(10, 20, true, true) + "!@#";
    }


    public static String getPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }


    public static String getUsername() {
        return faker.name().username();
    }


    public static String getCompanyName() {
        return faker.company().name();
    }


    public static String getAddress() {
        return faker.address().fullAddress();
    }


    public static String getCity() {
        return faker.address().city();
    }


    public static String getCountry() {
        return faker.address().country();
    }


    public static String getPostalCode() {
        return faker.address().zipCode();
    }


    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    public static int getRandomNumber(int max) {
        return faker.number().numberBetween(1, max);
    }


    public static boolean getRandomBoolean() {
        return faker.bool().bool();
    }
}
