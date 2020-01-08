package com.sinqia.career.salesanalyzer.exception;

public abstract class SalesAnalyzerRuntimeException extends RuntimeException {

    public SalesAnalyzerRuntimeException(final String message) {
        super(message);
    }

    public SalesAnalyzerRuntimeException(final String message, final Exception cause) {
        super(message, cause);
    }

}
