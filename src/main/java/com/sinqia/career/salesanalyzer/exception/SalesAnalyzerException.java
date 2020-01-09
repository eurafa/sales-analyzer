package com.sinqia.career.salesanalyzer.exception;

public abstract class SalesAnalyzerException extends RuntimeException {

    public SalesAnalyzerException(final String message) {
        super(message);
    }

    public SalesAnalyzerException(final String message, final Exception cause) {
        super(message, cause);
    }

}
