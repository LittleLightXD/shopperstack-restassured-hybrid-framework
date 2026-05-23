package api.dataproviders;

import org.testng.annotations.DataProvider;
import api.utils.FakeDataGenerator;

public class MerchantDataProvider {


    @DataProvider(name = "validMerchantData")
    public Object[][] validMerchantData() {
        return new Object[][] {
            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(),
             FakeDataGenerator.getPhoneNumber(), "RETAIL"},

            {"ABC Corporation", FakeDataGenerator.getUniqueEmail(),
             "9876543210", "WHOLESALE"},

            {"XYZ Traders", FakeDataGenerator.getUniqueEmail(),
             "8765432109", "MANUFACTURING"},

            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(),
             FakeDataGenerator.getPhoneNumber(), "DISTRIBUTOR"},
        };
    }


    @DataProvider(name = "merchantWithCompanyDetails")
    public Object[][] merchantWithCompanyDetails() {
        return new Object[][] {
            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(),
             "RETAIL", "18AABCU1234H1Z0"},

            {"Premium Stores", FakeDataGenerator.getUniqueEmail(),
             "WHOLESALE", "18AABCU1234H1Z1"},
        };
    }


    @DataProvider(name = "merchantWithAddressData")
    public Object[][] merchantWithAddressData() {
        return new Object[][] {
            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getAddress(),
             FakeDataGenerator.getCity(), "Karnataka"},

            {"Tech Company", FakeDataGenerator.getAddress(),
             "Delhi", "Delhi"},

            {"E-commerce Store", FakeDataGenerator.getAddress(),
             "Mumbai", "Maharashtra"},
        };
    }


    @DataProvider(name = "invalidMerchantData")
    public Object[][] invalidMerchantData() {
        return new Object[][] {

            {FakeDataGenerator.getCompanyName(), "invalid-email",
             FakeDataGenerator.getPhoneNumber(), "RETAIL"},


            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(),
             "123", "RETAIL"},


            {"", FakeDataGenerator.getUniqueEmail(),
             FakeDataGenerator.getPhoneNumber(), "RETAIL"},
        };
    }


    @DataProvider(name = "merchantStatusData")
    public Object[][] merchantStatusData() {
        return new Object[][] {
            {"ACTIVE"},
            {"INACTIVE"},
            {"SUSPENDED"},
            {"PENDING_APPROVAL"},
        };
    }


    @DataProvider(name = "passwordValidationData")
    public Object[][] passwordValidationData() {
        return new Object[][] {
            {FakeDataGenerator.getPassword(), FakeDataGenerator.getPassword()},
            {"short", "short"},
            {"Password@123", "DifferentPassword@123"},
        };
    }


    @DataProvider(name = "gstValidationData")
    public Object[][] gstValidationData() {
        return new Object[][] {
            {"18AABCU1234H1Z0"},
            {"27AABCU1234H1Z0"},
            {"INVALID"},
            {""},
        };
    }


    @DataProvider(name = "businessTypeData")
    public Object[][] businessTypeData() {
        return new Object[][] {
            {"RETAIL"},
            {"WHOLESALE"},
            {"MANUFACTURING"},
            {"DISTRIBUTOR"},
            {"e-COMMERCE"},
            {"FRANCHISE"},
        };
    }
}

