package com.sinqia.career.salesanalyzer.exception.io;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerException;

public class FileException extends SalesAnalyzerException {

    public FileException(final Exception cause) {
        super("I/O error", cause);
    }

    public FileException(final String message, final Exception cause) {
        super(message, cause);
    }

}
