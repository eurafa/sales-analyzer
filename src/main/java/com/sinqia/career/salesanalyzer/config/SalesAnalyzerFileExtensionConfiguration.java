package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerFileExtensionConfiguration {

    private final String inputExtension;

    private final String outputExtension;

    public SalesAnalyzerFileExtensionConfiguration(@Value("${file.input.extension:.dat}") final String inputExtension,
                                                   @Value("${file.output.extension:.done.dat}") final String outputExtension) {
        this.inputExtension = inputExtension;
        this.outputExtension = outputExtension;
    }

    public String getInputExtension() {
        return inputExtension;
    }

    public String getOutputExtension() {
        return outputExtension;
    }

}
