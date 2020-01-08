package com.sinqia.career.salesanalyzer.output;

import com.sinqia.career.salesanalyzer.dto.ReportDataDTO;

public interface SalesAnalyzerOutput {

    void write(String sourceFileName, ReportDataDTO reportData);

}
