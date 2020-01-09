package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.input.file.SalesAnalyzerFileInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileInputConfiguration {

    @Bean
    public SalesAnalyzerInput inputConfig(final DirectoryConfiguration directoryConfiguration) {
        return new SalesAnalyzerFileInput(directoryConfiguration);
    }

}
