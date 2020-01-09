package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.processor.SalesAnalyzerFileProcessor;
import com.sinqia.career.salesanalyzer.processor.listener.SalesAnalyzerMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SalesAnalyzerListenerConfiguration {

    @Bean
    @Primary
    public SalesAnalyzerMonitor listenerConfig(final DirectoryConfiguration directoryConfiguration,
                                               final FileExtensionConfiguration fileExtensionConfiguration,
                                               final SalesAnalyzerFileProcessor processor) {
        return new SalesAnalyzerMonitor(directoryConfiguration, fileExtensionConfiguration, processor);
    }

}
