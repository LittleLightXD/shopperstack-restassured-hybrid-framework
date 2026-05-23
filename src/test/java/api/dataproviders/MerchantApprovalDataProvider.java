package api.dataproviders;

import org.testng.annotations.DataProvider;

public class MerchantApprovalDataProvider {

    @DataProvider(name = "merchantApprovalData")
    public Object[][] getMerchantApprovalData() {
        return new Object[][] {
                {"merchant-001", "APPROVED", "Documents verified"},
                {"merchant-002", "APPROVED", "KYC completed"},
                {"merchant-003", "REJECTED", "Invalid documents"},
                {"merchant-004", "BLOCKED", "Suspicious activity"}
        };
    }
}

