package com.sinqia.career.salesanalyzer.exception.analyze;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerException;

public class UnknownBestSaleException extends SalesAnalyzerException {

    public UnknownBestSaleException() {
        super("Could not determine the best sale");
    }

}
