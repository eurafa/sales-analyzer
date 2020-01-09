package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileOutputReportInfoConfiguration {

    private final String reportClientCountInfo;

    private final String reportSellerCountInfo;

    private final String reportBestSaleIdInfo;

    private final String reportWorstSellerInfo;

    public FileOutputReportInfoConfiguration(@Value("${file.output.report.simple.info.clientCount:clientCount}") final String reportClientCountInfo,
                                             @Value("${file.output.report.simple.info.sellerCount:sellerCount}") final String reportSellerCountInfo,
                                             @Value("${file.output.report.simple.info.bestSaleId:bestSaleId}") final String reportBestSaleIdInfo,
                                             @Value("${file.output.report.simple.info.worstSeller:worstSeller}") final String reportWorstSellerInfo) {
        this.reportClientCountInfo = reportClientCountInfo;
        this.reportSellerCountInfo = reportSellerCountInfo;
        this.reportBestSaleIdInfo = reportBestSaleIdInfo;
        this.reportWorstSellerInfo = reportWorstSellerInfo;
    }

    public String getReportClientCountInfo() {
        return reportClientCountInfo;
    }

    public String getReportSellerCountInfo() {
        return reportSellerCountInfo;
    }

    public String getReportBestSaleIdInfo() {
        return reportBestSaleIdInfo;
    }

    public String getReportWorstSellerInfo() {
        return reportWorstSellerInfo;
    }

}
