package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectoryConfiguration {

    @Value("${dir.env}")
    private String envVar;

    @Value("${dir.input}")
    private String inputDir;

    @Value("${dir.output}")
    private String outputDir;

    public String getEnvVar() {
        return envVar;
    }

    public String getInputDir() {
        return inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }
}
