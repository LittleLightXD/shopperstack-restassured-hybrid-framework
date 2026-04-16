# REST Assured Hybrid Framework - ShopperStack API Automation

A professional, scalable REST API automation framework built with Java, REST Assured, TestNG, and Maven. This framework is designed for SDET and API automation engineers to create maintainable, data-driven, and well-reported API tests.

## 📋 Table of Contents

- [Framework Architecture](#framework-architecture)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [API Chaining](#api-chaining)
- [Best Practices](#best-practices)

## 🏗️ Framework Architecture

### Overall Structure

```
src/
├── main/
│   ├── java/
│   │   └── api/
│   │       ├── endpoints/          # API endpoint classes
│   │       ├── payload/            # POJO request/response models
│   │       ├── specs/              # RequestSpec & ResponseSpec builders
│   │       ├── utils/              # Utility classes
│   │       └── logging/            # Logging utilities
│   └── resources/
│       ├── config.properties       # Configuration file
│       └── log4j2.properties       # Logging configuration
└── test/
    └── java/
        └── api/
            ├── base/               # BaseTest class
            ├── testcases/          # Test case classes
            └── dataproviders/      # TestNG DataProviders
```

## 🛠️ Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming Language |
| **REST Assured** | 6.0.0 | API Testing Library |
| **TestNG** | 7.10.0 | Test Framework |
| **Maven** | 3.9+ | Build Tool |
| **Log4j2** | 3.0+ | Logging |
| **JavaFaker** | 1.0.2 | Random Data Generation |
| **ExtentReports** | 5.1.2 | Test Reporting |
| **Apache POI** | 5.5.1 | Excel Operations |

## ✨ Features Implemented

### 1. **Request Specification Builder**
- Centralized request configuration with base URI
- JSON content-type and accept headers
- Automatic logging filter setup
- Reusable request specifications
- Easy integration of authentication tokens

**Usage Example:**
```java
RequestSpecification spec = ReusableRequestSpec.buildRequestSpec();
RequestSpecification authSpec = ReusableRequestSpec.buildAuthenticatedRequestSpec(token);
```

### 2. **Response Specification Builder**
- Predefined response specs for common HTTP status codes
- Status code validation (200, 201, 400, 401, 404, 409, 500)
- Content-type validation
- Custom status code specifications

**Usage Example:**
```java
Response response = given()
    .spec(ReusableRequestSpec.buildRequestSpec())
    .body(payload)
    .when()
    .post(Routes.CREATE_ADMIN)
    .then()
    .spec(ReusableResponseSpec.buildSuccessResponseSpec())
    .extract()
    .response();
```

### 3. **Token Manager**
- Single login, reusable token across tests
- Singleton pattern for token storage
- Automatic token extraction from login response
- User ID extraction and storage
- Token refresh capabilities

**Usage Example:**
```java
TokenManager.generateToken();
String token = TokenManager.getToken();
TokenManager.clearToken();
```

### 4. **Faker Random Data Utility**
- Generate realistic test data:
  - Names (first, last)
  - Email addresses (unique)
  - Passwords (strong, with special characters)
  - Phone numbers
  - Company names
  - Addresses, cities, countries
  - UUIDs and random numbers

**Usage Example:**
```java
String email = FakeDataGenerator.getUniqueEmail();
String password = FakeDataGenerator.getStrongPassword();
String phone = FakeDataGenerator.getPhoneNumber();
```

### 5. **API Chaining**
- Extract values from one API response
- Use extracted values in subsequent requests
- Maintain object dependencies across API calls

**Usage Example:**
```java
// Create admin and extract ID
String adminId = AdminEndpoints.createAdminAndGetId(payload);

// Use extracted ID in next request
Response getResponse = AdminEndpoints.getAdminById(adminId);
```

### 6. **Logging**
- Request and response logging
- Test case lifecycle logging
- API call tracking with method, endpoint, and body
- Log4j2 integration with file and console output
- Centralized CustomLogger class

**Log Output:**
```
[INFO] ========== Test Setup Started ==========
[INFO] Base URL: https://api.shopperstacks.com
[INFO] API Request: POST /api/admin/login
[INFO] Request Body: {...}
[INFO] API Response Status Code: 200
[INFO] ✓ Admin created successfully
```

### 7. **Data-Driven Testing**
- TestNG DataProvider integration
- Multiple test datasets for same test
- Support for null/empty value testing

**DataProviders Included:**
- `validAdminData` - Valid test data
- `invalidEmailData` - Invalid email formats
- `missingFields` - Missing required fields
- `invalidPhoneData` - Invalid phone numbers
- `duplicateEmailData` - Duplicate data testing

### 8. **Test Scenarios**

#### Positive Tests:
- ✅ Create admin with valid data
- ✅ Get admin by valid ID
- ✅ Update admin details
- ✅ API chaining capabilities

#### Negative Tests:
- ❌ Invalid email format
- ❌ Missing required fields
- ❌ Invalid admin ID (404)
- ❌ Duplicate email validation
- ❌ Unauthorized access

### 9. **Config Reader**
- Centralized configuration management
- Singleton pattern
- Support for default values
- Easy environment switching

**Configuration Options:**
```properties
baseURL=https://api.shopperstacks.com
environment=dev
admin_username=admin@example.com
admin_password=Admin@123
request_timeout=10
response_timeout=15
enable_logging=true
extent_report_path=./test-output/extent-reports/
```

### 10. **BaseTest Setup**
- Common setup for all test classes
- Automatic ExtentReports initialization
- Token generation before tests
- Cleanup after tests
- Config reader access

### 11. **Extent Reports**
- HTML test execution reports
- Test status tracking (Pass/Fail)
- System information in reports
- Test timestamps
- Auto-generated in `./test-output/extent-reports/`

### 12. **GitHub Documentation**
- Complete README with examples
- Architecture diagrams
- Installation instructions
- Contribution guidelines

## 📂 Project Structure

```
shopperstacks/
├── src/
│   ├── main/
│   │   ├── java/api/
│   │   │   ├── endpoints/
│   │   │   │   ├── AdminEndpoints.java
│   │   │   │   └── Routes.java
│   │   │   ├── payload/
│   │   │   │   ├── AdminPayload.java
│   │   │   │   └── LoginAdminPayload.java
│   │   │   ├── specs/
│   │   │   │   ├── ReusableRequestSpec.java
│   │   │   │   └── ReusableResponseSpec.java
│   │   │   ├── utils/
│   │   │   │   ├── ConfigReader.java
│   │   │   │   ├── FakeDataGenerator.java
│   │   │   │   └── TokenManager.java
│   │   │   └── logging/
│   │   │       └── CustomLogger.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.properties
│   └── test/
│       └── java/api/
│           ├── base/
│           │   └── BaseTest.java
│           ├── testcases/
│           │   └── Admintestcases.java
│           └── dataproviders/
│               └── AdminDataProvider.java
├── pom.xml
├── testng.xml
├── run-tests.bat
├── run-tests.sh
└── README.md
```

## 📦 Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: 3.8.0 or higher
- **Git**: For version control

## 🚀 Installation

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/RestAssured-HybridFramework.git
cd RestAssured-HybridFramework/shopperstacks
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Update Configuration
Edit `src/main/resources/config.properties`:
```properties
baseURL=https://api.shopperstacks.com
admin_username=your_username
admin_password=your_password
```

## ⚙️ Configuration

### config.properties
All API configurations are managed in `src/main/resources/config.properties`:

```properties
# Base Configuration
baseURL=https://api.shopperstacks.com
environment=dev

# Admin User Credentials
admin_username=admin@example.com
admin_password=Admin@123

# Timeouts
request_timeout=10
response_timeout=15

# Logging
enable_logging=true

# Reports
extent_report_path=./test-output/extent-reports/
```

### Accessing Configuration in Tests
```java
ConfigReader config = ConfigReader.getInstance();
String baseURL = config.getBaseURL();
String username = config.getAdminUsername();
```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=Admintestcases
```

### Run Specific Test Method
```bash
mvn test -Dtest=Admintestcases#createAdminWithValidDataTest
```

### Run Tests with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run with Maven Surefire
```bash
mvn clean test -DskipTests=false
```

## 📊 Test Reports

### Extent Reports
After test execution, open the HTML report:
```
./test-output/extent-reports/extent-report.html
```

### Log Files
- **Request Logs**: `./test-output/request-logs.txt`
- **Response Logs**: `./test-output/response-logs.txt`
- **Application Logs**: `./test-output/app.log`

## 🔗 API Chaining Example

### Scenario: Create Admin, Get Admin, Update Admin

```java
// Step 1: Create Admin and extract ID
AdminPayload payload = new AdminPayload();
payload.setFirstName("John");
payload.setLastName("Doe");
payload.setEmail(FakeDataGenerator.getUniqueEmail());
payload.setPassword(FakeDataGenerator.getPassword());

String adminId = AdminEndpoints.createAdminAndGetId(payload);
Assert.assertNotNull(adminId);

// Step 2: Get the created admin
Response getResponse = AdminEndpoints.getAdminById(adminId);
Assert.assertEquals(getResponse.getStatusCode(), 200);
String retrievedEmail = getResponse.jsonPath().getString("email");

// Step 3: Update the admin
AdminPayload updatePayload = new AdminPayload();
updatePayload.setFirstName("Jane");
updatePayload.setLastName("Smith");

Response updateResponse = AdminEndpoints.updateAdmin(adminId, updatePayload);
Assert.assertEquals(updateResponse.getStatusCode(), 200);
```

## 🎯 Best Practices

### 1. Use Data-Driven Tests
```java
@Test(dataProvider = "validAdminData", dataProviderClass = AdminDataProvider.class)
public void testWithMultipleDatasets(String firstName, String lastName, String email, String phone) {
    // Test implementation
}
```

### 2. Leverage Token Manager
```java
@BeforeClass
public void setUp() {
    TokenManager.generateToken();  // Generate once
}

@AfterClass
public void tearDown() {
    TokenManager.clearToken();     // Cleanup
}
```

### 3. Use Fake Data Generation
```java
// Don't use hardcoded email
String email = FakeDataGenerator.getUniqueEmail();  // Always unique

// Use proper password
String password = FakeDataGenerator.getStrongPassword();  // With special chars
```

### 4. Implement Proper Logging
```java
CustomLogger.startTestCase("descriptiveTestName");
CustomLogger.info("Creating admin with email: " + email);
CustomLogger.logAPIRequest("POST", "/api/admin", payloadString);
CustomLogger.endTestCase("descriptiveTestName");
```

### 5. Separate Positive and Negative Tests
```java
// Positive scenarios
@Test(priority = 1)
public void validDataTest() { }

// Negative scenarios
@Test(priority = 10)
public void invalidDataTest() { }
```

### 6. Use Meaningful Assertions
```java
Assert.assertEquals(response.getStatusCode(), 201, 
    "Admin creation should return status 201");
Assert.assertNotNull(response.jsonPath().getString("adminId"), 
    "Response should contain admin ID");
```

## 🔐 Security

- Store sensitive credentials in `config.properties` (local only)
- Use environment variables for CI/CD pipelines
- Never commit passwords to GitHub
- Use `.gitignore` to exclude config files

## 📚 Additional Resources

### Useful Links
- [REST Assured Documentation](http://rest-assured.io/)
- [TestNG Documentation](https://testng.org/)
- [Maven Documentation](https://maven.apache.org/)
- [Log4j2 Documentation](https://logging.apache.org/log4j/2.x/)
- [ExtentReports Documentation](https://www.extentreports.com/)

### Example Test Methods
All test examples are available in `src/test/java/api/testcases/Admintestcases.java`

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License.

## 👨‍💼 Author

**REST Assured Hybrid Framework**
- Professional API Automation Framework
- Suitable for SDET/QA Engineer portfolios
- Scalable and maintainable architecture

---

## 🚀 Quick Start

```bash
# Clone project
git clone <repo-url>
cd shopperstacks

# Install dependencies
mvn clean install

# Update configuration
# Edit src/main/resources/config.properties

# Run tests
mvn test

# View report
# Open ./test-output/extent-reports/extent-report.html
```

---

**Happy Testing! 🎉**
