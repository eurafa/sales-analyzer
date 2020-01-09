package com.sinqia.career.salesanalyzer.report;

import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;

import java.util.List;

public interface SalesAnalyzeReport {

    List<String> generateReport(final AnalyzeResultDTO analyzeResult);

}
