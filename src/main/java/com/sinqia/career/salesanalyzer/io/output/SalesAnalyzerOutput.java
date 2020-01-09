package com.sinqia.career.salesanalyzer.io.output;

import java.util.List;

public interface SalesAnalyzerOutput {

    void write(String sourceFileName, List<String> reportData);

}
