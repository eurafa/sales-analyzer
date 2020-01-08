package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.output.file.SalesAnalyzerFileOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerOutputConfiguration {

    @Bean
    public SalesAnalyzerOutput outputConfig(final SalesAnalyzerDirectoryConfiguration directoryConfiguration, final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration) {
        return new SalesAnalyzerFileOutput(directoryConfiguration, fileExtensionConfiguration);
    }

}
