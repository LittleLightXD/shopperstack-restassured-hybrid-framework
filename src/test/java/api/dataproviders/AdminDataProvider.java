package api.dataproviders;

import org.testng.annotations.DataProvider;
import api.utils.FakeDataGenerator;

public class AdminDataProvider {


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


    @DataProvider(name = "missingFields")
    public Object[][] missingFields() {
        return new Object[][] {

            {null, FakeDataGenerator.getLastName(),
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},


            {FakeDataGenerator.getFirstName(), null,
             FakeDataGenerator.getUniqueEmail(), FakeDataGenerator.getPhoneNumber()},


            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(),
             null, FakeDataGenerator.getPhoneNumber()},


            {FakeDataGenerator.getFirstName(), FakeDataGenerator.getLastName(),
             FakeDataGenerator.getUniqueEmail(), null},
        };
    }


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

