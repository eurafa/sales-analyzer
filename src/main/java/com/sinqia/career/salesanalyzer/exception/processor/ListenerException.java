package com.sinqia.career.salesanalyzer.exception.processor;

import com.sinqia.career.salesanalyzer.exception.SalesAnalyzerException;

public class ListenerException extends SalesAnalyzerException {

    public ListenerException(final Exception cause) {
        super("Error listening input directory", cause);
    }

}
