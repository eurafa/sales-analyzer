package com.sinqia.career.salesanalyzer.io;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionValidator {

    private final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    public FileExtensionValidator(final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration) {
        this.fileExtensionConfiguration = fileExtensionConfiguration;
    }

    public boolean isInput(final String filename) {
        return filename.endsWith(fileExtensionConfiguration.getInputExtension());
    }

    public boolean isOutput(final String filename) {
        return filename.endsWith(fileExtensionConfiguration.getOutputExtension());
    }

}
