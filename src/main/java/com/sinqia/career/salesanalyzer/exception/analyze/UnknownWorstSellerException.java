package com.sinqia.career.salesanalyzer.exception.analyze;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerRuntimeException;

public class UnknownWorstSellerException extends SalesAnalyzerRuntimeException {

    public UnknownWorstSellerException() {
        super("Could not determine the worst seller");
    }

}
