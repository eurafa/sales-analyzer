package com.sinqia.career.salesanalyzer.exception.analyze;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerException;

public class UnknownWorstSellerException extends SalesAnalyzerException {

    public UnknownWorstSellerException() {
        super("Could not determine the worst seller");
    }

}
