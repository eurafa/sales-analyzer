package com.sinqia.career.salesanalyzer.exception.io;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerException;

public class SalesAnalyzerIOException extends SalesAnalyzerException {

    public SalesAnalyzerIOException(final Exception cause) {
        super("I/O error", cause);
    }

}
