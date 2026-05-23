package api.dataproviders;

import api.utils.FakeDataGenerator;
import org.testng.annotations.DataProvider;

public class ShopperDataProvider {

    @DataProvider(name = "validShopperData")
    public Object[][] validShopperData() {
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(),
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            {"John", "Doe", FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            {"Jane", "Smith", FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            {"Robert", "Johnson", FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
        };
    }

    @DataProvider(name = "shopperWithAddressData")
    public Object[][] shopperWithAddressData() {
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(),
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getAddress(), FakeDataGenerator.getCity()},
            {"John", "Doe", FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getAddress(), FakeDataGenerator.getCity()},
            {"Jane", "Smith", FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getAddress(), FakeDataGenerator.getCity()},
        };
    }

    @DataProvider(name = "invalidShopperEmailData")
    public Object[][] invalidShopperEmailData() {
        return new Object[][] {
            {"John", "Doe", "invalidemail", FakeDataGenerator.getPhoneNumber()},
            {"Jane", "Smith", "test@", FakeDataGenerator.getPhoneNumber()},
            {"Robert", "Johnson", "@invalid.com", FakeDataGenerator.getPhoneNumber()},
        };
    }

    @DataProvider(name = "shopperPhoneValidationData")
    public Object[][] shopperPhoneValidationData() {
        return new Object[][] {
            {"John", "Doe", FakeDataGenerator.getUniqueEmail(), "123"},
            {"Jane", "Smith", FakeDataGenerator.getUniqueEmail(), "12345"},
            {"Robert", "Johnson", FakeDataGenerator.getUniqueEmail(), "abc123def45"},
        };
    }

    @DataProvider(name = "shopperPasswordValidationData")
    public Object[][] shopperPasswordValidationData() {
        return new Object[][] {
            {"John", "Doe", FakeDataGenerator.getUniqueEmail(), "123", "123"},
            {"Jane", "Smith", FakeDataGenerator.getUniqueEmail(), "abc123", "abc123"},
            {"Robert", "Johnson", FakeDataGenerator.getUniqueEmail(), "Pass123!", "Pass456!"},
        };
    }

    @DataProvider(name = "shopperGenderData")
    public Object[][] shopperGenderData() {
        return new Object[][] {
            {"John", "Doe", FakeDataGenerator.getUniqueEmail(), "MALE"},
            {"Jane", "Smith", FakeDataGenerator.getUniqueEmail(), "FEMALE"},
            {"Alex", "Taylor", FakeDataGenerator.getUniqueEmail(), "OTHER"},
        };
    }

    @DataProvider(name = "addressTypeData")
    public Object[][] addressTypeData() {
        return new Object[][] {
            {FakeDataGenerator.getAddress(), "HOME", true},
            {FakeDataGenerator.getAddress(), "WORK", true},
            {FakeDataGenerator.getAddress(), "OTHER", false},
            {FakeDataGenerator.getAddress(), "TEMPORARY", false},
        };
    }
}

