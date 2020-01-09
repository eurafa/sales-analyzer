package com.sinqia.career.salesanalyzer.io;

import com.sinqia.career.salesanalyzer.config.FileExtensionConfiguration;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionValidator {

    private final FileExtensionConfiguration fileExtensionConfiguration;

    public FileExtensionValidator(final FileExtensionConfiguration fileExtensionConfiguration) {
        this.fileExtensionConfiguration = fileExtensionConfiguration;
    }

    public boolean isInput(final String filename) {
        return filename.endsWith(fileExtensionConfiguration.getInputExtension());
    }

    public boolean isOutput(final String filename) {
        return filename.endsWith(fileExtensionConfiguration.getOutputExtension());
    }

}
