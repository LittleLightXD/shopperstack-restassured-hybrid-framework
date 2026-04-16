package api.dataproviders;

import org.testng.annotations.DataProvider;
import api.utils.FakeDataGenerator;

/**
 * AdminDataProvider - DataProvider for admin test data
 */
public class AdminDataProvider {

    /**
     * DataProvider for valid admin data
     */
    @DataProvider(name = "validAdminData")
    public Object[][] validAdminData() {
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            
            {"John", "Doe", 
             FakeDataGenerator.getUniqueEmail(), "9876543210"},
            
            {"Jane", "Smith", 
             FakeDataGenerator.getUniqueEmail(), "8765432109"},
        };
    }

    /**
     * DataProvider for invalid email data
     */
    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() {
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             "invalid-email", FakeDataGenerator.getPhoneNumber()},
            
            {"John", "Doe", 
             "test@invalid", "9876543210"},
            
            {"Jane", "Smith", 
             "plaintext", "8765432109"},
        };
    }

    /**
     * DataProvider for missing required fields
     */
    @DataProvider(name = "missingFields")
    public Object[][] missingFields() {
        return new Object[][] {
            // Missing firstName
            {null, FakeDataGenerator.getLastName(), 
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            
            // Missing lastName
            {FakeDataGenerator.getFirstName(), null, 
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},
            
            // Missing email
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             null, FakeDataGenerator.getPhoneNumber()},
            
            // Missing phone
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             FakeDataGenerator.getUniqueEmail(), null},
        };
    }

    /**
     * DataProvider for invalid phone numbers
     */
    @DataProvider(name = "invalidPhoneData")
    public Object[][] invalidPhoneData() {
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             FakeDataGenerator.getUniqueEmail(), "123"},
            
            {"John", "Doe", 
             FakeDataGenerator.getUniqueEmail(), "abcdefghij"},
            
            {"Jane", "Smith", 
             FakeDataGenerator.getUniqueEmail(), ""},
        };
    }

    /**
     * DataProvider for duplicate email testing
     */
    @DataProvider(name = "duplicateEmailData")
    public Object[][] duplicateEmailData() {
        String duplicateEmail = FakeDataGenerator.getUniqueEmail();
        return new Object[][] {
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             duplicateEmail, FakeDataGenerator.getPhoneNumber()},
            
            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(), 
             duplicateEmail, FakeDataGenerator.getPhoneNumber()},
        };
    }
}
