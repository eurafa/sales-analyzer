package com.sinqia.career.salesanalyzer.report;

import com.sinqia.career.salesanalyzer.config.FileOutputReportInfoConfiguration;
import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SalesAnalyzeResumeReport implements SalesAnalyzeReport {

    private final FileOutputReportInfoConfiguration fileOutputReportInfoConfiguration;

    public SalesAnalyzeResumeReport(final FileOutputReportInfoConfiguration fileOutputReportInfoConfiguration) {
        this.fileOutputReportInfoConfiguration = fileOutputReportInfoConfiguration;
    }

    @Override
    public List<String> generateReport(final AnalyzeResultDTO analyzeResult) {
        return Arrays.asList(
                String.format(fileOutputReportInfoConfiguration.getReportClientCountInfo(), analyzeResult.getClientCount()),
                String.format(fileOutputReportInfoConfiguration.getReportSellerCountInfo(), analyzeResult.getSellerCount()),
                String.format(fileOutputReportInfoConfiguration.getReportBestSaleIdInfo(), analyzeResult.getBestSaleId()),
                String.format(fileOutputReportInfoConfiguration.getReportWorstSellerInfo(), analyzeResult.getWorstSeller()));
    }

}
