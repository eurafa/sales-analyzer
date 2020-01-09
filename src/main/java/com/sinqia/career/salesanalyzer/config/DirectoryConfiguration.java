package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectoryConfiguration {

    private final String envVar;

    private final String inputDir;

    private final String outputDir;

    private final boolean monitor;

    public DirectoryConfiguration(@Value("${dir.env}") final String envVar,
                                  @Value("${dir.input}") final String inputDir,
                                  @Value("${dir.output}") final String outputDir,
                                  @Value("${dir.monitor}") final boolean monitor) {
        this.envVar = envVar;
        this.inputDir = inputDir;
        this.outputDir = outputDir;
        this.monitor = monitor;
    }

    public String getEnvVar() {
        return envVar;
    }

    public String getInputDir() {
        return inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public boolean isMonitor() {
        return monitor;
    }
}
