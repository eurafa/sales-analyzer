package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.processor.listener.SalesAnalyzerListener;
import com.sinqia.career.salesanalyzer.report.generator.SalesAnalyzerReportGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SalesAnalyzerListenerConfiguration {

    @Bean
    @Primary
    public SalesAnalyzerListener listenerConfig(final DirectoryConfiguration directoryConfiguration, final FileExtensionConfiguration fileExtensionConfiguration, final SalesAnalyzerReportGenerator reportGenerator) {
        return new SalesAnalyzerListener(directoryConfiguration, fileExtensionConfiguration, reportGenerator);
    }

}
