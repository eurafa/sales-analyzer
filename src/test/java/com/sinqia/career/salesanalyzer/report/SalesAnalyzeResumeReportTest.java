package com.sinqia.career.salesanalyzer.report;

import com.sinqia.career.salesanalyzer.config.FileOutputReportInfoConfiguration;
import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalesAnalyzeResumeReportTest {

    private static final String REPORT_INFO_CLIENT_COUNT_PREFIX = "Client count ";

    private static final String REPORT_INFO_SELLER_COUNT_PREFIX = "Seller count ";

    private static final String REPORT_INFO_BEST_SALE_ID_PREFIX = "Best sale id ";

    private static final String REPORT_INFO_WORST_SELLER_PREFIX = "Worst seller name ";

    private final SalesAnalyzeResumeReport report;

    SalesAnalyzeResumeReportTest() {
        final String clientCountInfo = REPORT_INFO_CLIENT_COUNT_PREFIX + "%s";
        final String sellerCountInfo = REPORT_INFO_SELLER_COUNT_PREFIX + "%s";
        final String bestSaleIdInfo = REPORT_INFO_BEST_SALE_ID_PREFIX + "%s";
        final String worstSellerInfo = REPORT_INFO_WORST_SELLER_PREFIX + "%s";
        report = new SalesAnalyzeResumeReport(new FileOutputReportInfoConfiguration(clientCountInfo, sellerCountInfo, bestSaleIdInfo, worstSellerInfo));
    }

    @Test
    void testGenerateReportSuccess() {
        // Given
        final int clientCount = 1;
        final int sellerCount = 2;
        final String bestSaleId = "99";
        final String worstSeller = "Worst";
        final AnalyzeResultDTO analyzeResult = new AnalyzeResultDTO(clientCount, sellerCount, bestSaleId, worstSeller);

        // When
        final List<String> reportContent = report.generateReport(analyzeResult);

        // Then
        assertThat(reportContent)
                .isNotNull()
                .containsExactlyInAnyOrder(REPORT_INFO_CLIENT_COUNT_PREFIX + clientCount,
                        REPORT_INFO_SELLER_COUNT_PREFIX + sellerCount,
                        REPORT_INFO_BEST_SALE_ID_PREFIX + bestSaleId,
                        REPORT_INFO_WORST_SELLER_PREFIX + worstSeller);
    }

}