package com.sinqia.career.salesanalyzer.exception.analyze;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerRuntimeException;

public class UnknownBestSaleException extends SalesAnalyzerRuntimeException {

    public UnknownBestSaleException() {
        super("Could not determine the best sale");
    }

}
