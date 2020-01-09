package com.sinqia.career.salesanalyzer.exception.io;

public class FileReadException extends FileException {

    public FileReadException(final String filename, final Exception cause) {
        super(String.format("Error reading file %s", filename), cause);
    }

}
