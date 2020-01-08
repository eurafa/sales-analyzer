package com.sinqia.career.salesanalyzer.io;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionResolver {

    private final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    public FileExtensionResolver(final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration) {
        this.fileExtensionConfiguration = fileExtensionConfiguration;
    }

    public String asInput(final String outputFilename) {
        return outputFilename.substring(0, outputFilename.length() - fileExtensionConfiguration.getOutputExtension().length()) + fileExtensionConfiguration.getInputExtension();
    }

    public String asOutput(final String inputFilename) {
        return inputFilename.substring(0, inputFilename.length() - fileExtensionConfiguration.getInputExtension().length()) + fileExtensionConfiguration.getOutputExtension();
    }

}
