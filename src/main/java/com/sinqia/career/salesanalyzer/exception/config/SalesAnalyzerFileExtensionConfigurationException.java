package com.sinqia.career.salesanalyzer.exception.config;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerRuntimeException;

public class SalesAnalyzerFileExtensionConfigurationException extends SalesAnalyzerRuntimeException {

    public SalesAnalyzerFileExtensionConfigurationException() {
        super("Bad configuration for input/output file extension");
    }

}
