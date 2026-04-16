package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WalletPayload - POJO for Wallet operations (balance, transactions)
 * Contains wallet balance and transaction fields for payment processing
 */
public class WalletPayload {

    @JsonProperty("walletId")
    private String walletId;

    @JsonProperty("shopperId")
    private String shopperId;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("totalCredit")
    private Double totalCredit;

    @JsonProperty("totalDebit")
    private Double totalDebit;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;  // ACTIVE, INACTIVE, BLOCKED

    @JsonProperty("lastTransactionDate")
    private String lastTransactionDate;

    @JsonProperty("createdDateTime")
    private String createdDateTime;

    @JsonProperty("updatedDateTime")
    private String updatedDateTime;

    // Nested WalletTransaction class
    public static class WalletTransaction {
        @JsonProperty("transactionId")
        private String transactionId;

        @JsonProperty("transactionType")
        private String transactionType;  // ADD_FUNDS, PURCHASE, REFUND, WITHDRAWAL

        @JsonProperty("amount")
        private Double amount;

        @JsonProperty("description")
        private String description;

        @JsonProperty("status")
        private String status;  // SUCCESS, PENDING, FAILED

        @JsonProperty("transactionDate")
        private String transactionDate;

        @JsonProperty("relatedOrderId")
        private String relatedOrderId;

        // Constructors
        public WalletTransaction() {}

        public WalletTransaction(String transactionType, Double amount) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getRelatedOrderId() {
            return relatedOrderId;
        }

        public void setRelatedOrderId(String relatedOrderId) {
            this.relatedOrderId = relatedOrderId;
        }
    }

    // Constructors
    public WalletPayload() {}

    public WalletPayload(String shopperId, Double balance) {
        this.shopperId = shopperId;
        this.balance = balance;
    }

    // Getters and Setters
    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(String lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
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
