package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.io.output.file.SalesAnalyzerFileOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerOutputConfiguration {

    @Bean
    public SalesAnalyzerOutput outputConfig(final DirectoryConfiguration directoryConfiguration,
                                            final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration,
                                            final FileOutputReportInfoConfiguration fileOutputReportInfoConfiguration) {
        return new SalesAnalyzerFileOutput(directoryConfiguration, fileExtensionConfiguration, fileOutputReportInfoConfiguration);
    }

}
