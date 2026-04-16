package api.testcases;

import api.base.BaseTest;
import api.dataproviders.MerchantApprovalDataProvider;
import api.endpoints.MerchantApprovalEndpoints;
import api.logging.CustomLogger;
import api.payload.MerchantApprovalPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * MerchantApprovalTestcases - Test scenarios for Merchant Approval/Management module
 * Covers approval, rejection, blocking, and merchant status management by Admin
 */
public class MerchantApprovalTestcases extends BaseTest {

    CustomLogger logger = new CustomLogger();

    // ==================== POSITIVE TEST CASES ====================

    @Test(priority = 1, description = "Get pending merchants for approval")
    public void getPendingMerchantsTest() {
        logger.startTestCase("getPendingMerchantsTest");
        
        Response response = MerchantApprovalEndpoints.getPendingMerchants();
        logger.logAPIResponse(response.getStatusCode(), response.getBody().asString());
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getPendingMerchantsTest");
    }

    @Test(priority = 2, description = "Approve merchant")
    public void approveMerchantTest() {
        logger.startTestCase("approveMerchantTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setApprovalStatus("APPROVED");
        payload.setComments("Merchant verified and documents approved");

        Response response = MerchantApprovalEndpoints.approveMerchant("merchant-123456", payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("approveMerchantTest");
    }

    @Test(priority = 3, description = "Reject merchant approval")
    public void rejectMerchantTest() {
        logger.startTestCase("rejectMerchantTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setApprovalStatus("REJECTED");
        payload.setRejectionReason("Incomplete documentation provided");

        Response response = MerchantApprovalEndpoints.rejectMerchant("merchant-123456", payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("rejectMerchantTest");
    }

    @Test(priority = 4, description = "Block merchant account")
    public void blockMerchantTest() {
        logger.startTestCase("blockMerchantTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setApprovalStatus("BLOCKED");
        payload.setBlockReason("Fraudulent activities detected");

        Response response = MerchantApprovalEndpoints.blockMerchant("merchant-123456", payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("blockMerchantTest");
    }

    @Test(priority = 5, description = "Get approved merchants")
    public void getApprovedMerchantsTest() {
        logger.startTestCase("getApprovedMerchantsTest");
        
        Response response = MerchantApprovalEndpoints.getApprovedMerchants();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getApprovedMerchantsTest");
    }

    @Test(priority = 6, description = "Get blocked merchants")
    public void getBlockedMerchantsTest() {
        logger.startTestCase("getBlockedMerchantsTest");
        
        Response response = MerchantApprovalEndpoints.getBlockedMerchants();
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getBlockedMerchantsTest");
    }

    @Test(priority = 7, description = "Get merchant approval history")
    public void getMerchantApprovalHistoryTest() {
        logger.startTestCase("getMerchantApprovalHistoryTest");
        
        Response response = MerchantApprovalEndpoints.getMerchantApprovalHistory("merchant-123456");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("getMerchantApprovalHistoryTest");
    }

    // ==================== DATA-DRIVEN TEST CASES ====================

    @Test(priority = 10, dataProvider = "merchantApprovalData", dataProviderClass = MerchantApprovalDataProvider.class,
            description = "Approve merchant with data provider")
    public void approveMerchantDataDrivenTest(String merchantId, String status, String comments) {
        logger.startTestCase("approveMerchantDataDrivenTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setMerchantId(merchantId);
        payload.setApprovalStatus(status);
        payload.setComments(comments);

        Response response = MerchantApprovalEndpoints.approveMerchant(merchantId, payload);
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("approveMerchantDataDrivenTest");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test(priority = 20, description = "Approve non-existent merchant")
    public void approveNonExistentMerchantTest() {
        logger.startTestCase("approveNonExistentMerchantTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setApprovalStatus("APPROVED");

        Response response = MerchantApprovalEndpoints.approveMerchant("non-existent-id", payload);
        
        Assert.assertEquals(response.getStatusCode(), 404);
        
        logger.endTestCase("approveNonExistentMerchantTest");
    }

    @Test(priority = 21, description = "Approve with missing approval status")
    public void approveWithMissingStatusTest() {
        logger.startTestCase("approveWithMissingStatusTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setComments("Missing status field");

        Response response = MerchantApprovalEndpoints.approveMerchant("merchant-123456", payload);
        
        Assert.assertEquals(response.getStatusCode(), 400);
        
        logger.endTestCase("approveWithMissingStatusTest");
    }

    @Test(priority = 22, description = "Block already blocked merchant")
    public void blockAlreadyBlockedMerchantTest() {
        logger.startTestCase("blockAlreadyBlockedMerchantTest");
        
        MerchantApprovalPayload payload = new MerchantApprovalPayload();
        payload.setApprovalStatus("BLOCKED");
        payload.setBlockReason("Already blocked");

        Response response = MerchantApprovalEndpoints.blockMerchant("blocked-merchant-id", payload);
        
        Assert.assertEquals(response.getStatusCode(), 409);
        
        logger.endTestCase("blockAlreadyBlockedMerchantTest");
    }

    @Test(priority = 23, description = "Unauthorized merchant approval access")
    public void unauthorizedApprovalAccessTest() {
        logger.startTestCase("unauthorizedApprovalAccessTest");
        
        Response response = MerchantApprovalEndpoints.getPendingMerchants();
        
        Assert.assertEquals(response.getStatusCode(), 401);
        
        logger.endTestCase("unauthorizedApprovalAccessTest");
    }

    @Test(priority = 24, description = "Unblock merchant")
    public void unblockMerchantTest() {
        logger.startTestCase("unblockMerchantTest");
        
        Response response = MerchantApprovalEndpoints.unblockMerchant("merchant-123456");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("unblockMerchantTest");
    }

    @Test(priority = 25, description = "Delete merchant permanently")
    public void deleteMerchantPermanentlyTest() {
        logger.startTestCase("deleteMerchantPermanentlyTest");
        
        Response response = MerchantApprovalEndpoints.deleteMerchantPermanently("merchant-123456");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.endTestCase("deleteMerchantPermanentlyTest");
    }
}
