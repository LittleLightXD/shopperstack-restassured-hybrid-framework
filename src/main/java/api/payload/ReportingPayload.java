package api.payload;

public class ReportingPayload {
    private String reportType;
    private String startDate;
    private String endDate;
    private String format;
    private String merchantId;
    private int limit;


    public ReportingPayload() {}

    public ReportingPayload(String reportType, String startDate, String endDate) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

