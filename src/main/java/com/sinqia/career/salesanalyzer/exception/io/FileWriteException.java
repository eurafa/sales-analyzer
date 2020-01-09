package com.sinqia.career.salesanalyzer.exception.io;

public class FileWriteException extends FileException {

    public FileWriteException(final String filename, final Exception cause) {
        super(String.format("Error writing file %s", filename), cause);
    }

}
