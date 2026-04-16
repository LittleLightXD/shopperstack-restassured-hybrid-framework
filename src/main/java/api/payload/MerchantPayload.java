package api.payload;

/**
 * MerchantPayload - POJO for Merchant request body
 */
public class MerchantPayload {

    private String businessType;
    private String businessName;
    private String businessEmail;
    private String businessPhone;
    private String gstNumber;
    private String businessAddress;
    private String businessCity;
    private String businessState;
    private String businessCountry;
    private String businessZipCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private CompanyDetails companyDetails;
    private AddressDetails addressDetails;

    // Constructors
    public MerchantPayload() {
    }

    public MerchantPayload(String businessName, String businessEmail, String email, String phone) {
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessCountry() {
        return businessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }

    public String getBusinessZipCode() {
        return businessZipCode;
    }

    public void setBusinessZipCode(String businessZipCode) {
        this.businessZipCode = businessZipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }

    /**
     * Nested class for Company Details
     */
    public static class CompanyDetails {
        private String companyName;
        private String businessType;
        private String gst;

        public CompanyDetails() {
        }

        public CompanyDetails(String companyName, String businessType, String gst) {
            this.companyName = companyName;
            this.businessType = businessType;
            this.gst = gst;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getBusinessType() {
            return businessType;
        }

        public void setBusinessType(String businessType) {
            this.businessType = businessType;
        }

        public String getGst() {
            return gst;
        }

        public void setGst(String gst) {
            this.gst = gst;
        }
    }

    /**
     * Nested class for Address Details
     */
    public static class AddressDetails {
        private String address;
        private String city;
        private String state;
        private String country;
        private String zipCode;

        public AddressDetails() {
        }

        public AddressDetails(String address, String city, String state, String country, String zipCode) {
            this.address = address;
            this.city = city;
            this.state = state;
            this.country = country;
            this.zipCode = zipCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
