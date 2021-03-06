package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.io.output.file.SalesAnalyzerFileOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileOutputConfiguration {

    @Bean
    public SalesAnalyzerOutput outputConfig(final DirectoryConfiguration directoryConfiguration,
                                            final FileExtensionConfiguration fileExtensionConfiguration) {
        return new SalesAnalyzerFileOutput(directoryConfiguration, fileExtensionConfiguration);
    }

}
