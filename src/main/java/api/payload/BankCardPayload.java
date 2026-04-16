package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BankCardPayload - POJO for Bank Account operations
 * Contains bank account fields for creation, verification, and transactions
 */
public class BankCardPayload {

    @JsonProperty("bankAccountId")
    private String bankAccountId;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("bankName")
    private String bankName;

    @JsonProperty("ifscCode")
    private String ifscCode;

    @JsonProperty("accountType")
    private String accountType;  // SAVINGS, CURRENT, etc

    @JsonProperty("isVerified")
    private Boolean isVerified;

    @JsonProperty("verificationStatus")
    private String verificationStatus;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("isDefault")
    private Boolean isDefault;

    @JsonProperty("createdDateTime")
    private String createdDateTime;

    @JsonProperty("updatedDateTime")
    private String updatedDateTime;

    // Nested Transaction class
    public static class TransactionDetails {
        @JsonProperty("transactionId")
        private String transactionId;

        @JsonProperty("transactionType")
        private String transactionType;  // DEBIT, CREDIT

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("description")
        private String description;

        @JsonProperty("transactionDate")
        private String transactionDate;

        @JsonProperty("status")
        private String status;  // SUCCESS, FAILED, PENDING

        // Constructors
        public TransactionDetails() {}

        public TransactionDetails(String transactionType, Double amount) {
            this.transactionType = transactionType;
            this.amount = amount;
        }

        // Getters and Setters
        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    // Constructors
    public BankCardPayload() {}

    public BankCardPayload(String accountNumber, String accountHolderName, String bankName, String ifscCode) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
    }

    // Getters and Setters
    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
