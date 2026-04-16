package api.dataproviders;

import org.testng.annotations.DataProvider;
import api.utils.FakeDataGenerator;

/**
 * MerchantDataProvider - DataProvider for merchant test data
 * Matches Postman collection test scenarios
 */
public class MerchantDataProvider {

    /**
     * DataProvider for valid merchant data
     */
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

    /**
     * DataProvider for merchant registration with complete details
     */
    @DataProvider(name = "merchantWithCompanyDetails")
    public Object[][] merchantWithCompanyDetails() {
        return new Object[][] {
            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(), 
             "RETAIL", "18AABCU1234H1Z0"},
            
            {"Premium Stores", FakeDataGenerator.getUniqueEmail(), 
             "WHOLESALE", "18AABCU1234H1Z1"},
        };
    }

    /**
     * DataProvider for merchant with address variations
     */
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

    /**
     * DataProvider for invalid merchant data (negative testing)
     */
    @DataProvider(name = "invalidMerchantData")
    public Object[][] invalidMerchantData() {
        return new Object[][] {
            // Invalid email
            {FakeDataGenerator.getCompanyName(), "invalid-email", 
             FakeDataGenerator.getPhoneNumber(), "RETAIL"},
            
            // Invalid phone
            {FakeDataGenerator.getCompanyName(), FakeDataGenerator.getUniqueEmail(), 
             "123", "RETAIL"},
            
            // Missing business name
            {"", FakeDataGenerator.getUniqueEmail(), 
             FakeDataGenerator.getPhoneNumber(), "RETAIL"},
        };
    }

    /**
     * DataProvider for merchant status updates
     */
    @DataProvider(name = "merchantStatusData")
    public Object[][] merchantStatusData() {
        return new Object[][] {
            {"ACTIVE"},
            {"INACTIVE"},
            {"SUSPENDED"},
            {"PENDING_APPROVAL"},
        };
    }

    /**
     * DataProvider for password validation scenarios
     */
    @DataProvider(name = "passwordValidationData")
    public Object[][] passwordValidationData() {
        return new Object[][] {
            {FakeDataGenerator.getPassword(), FakeDataGenerator.getPassword()},  // Valid - matching
            {"short", "short"},  // Invalid - too short
            {"Password@123", "DifferentPassword@123"},  // Invalid - mismatch
        };
    }

    /**
     * DataProvider for GST validation
     */
    @DataProvider(name = "gstValidationData")
    public Object[][] gstValidationData() {
        return new Object[][] {
            {"18AABCU1234H1Z0"},  // Valid European format
            {"27AABCU1234H1Z0"},  // Invalid - wrong state code
            {"INVALID"},  // Invalid format
            {""},  // Empty
        };
    }

    /**
     * DataProvider for business type variations
     */
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
