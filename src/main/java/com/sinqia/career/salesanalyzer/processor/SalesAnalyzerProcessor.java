package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerDirectoryConfiguration;
import com.sinqia.career.salesanalyzer.exception.io.SalesAnalyzerIOException;
import com.sinqia.career.salesanalyzer.processor.discovery.SalesAnalyzerProcessorFileDiscovery;
import com.sinqia.career.salesanalyzer.processor.listener.SalesAnalyzerListener;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzerReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class SalesAnalyzerProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesAnalyzerDirectoryConfiguration pathConfiguration;

    private final SalesAnalyzerProcessorFileDiscovery fileDiscovery;

    private final SalesAnalyzerReportGenerator reportGenerator;

    private final SalesAnalyzerListener listener;

    public SalesAnalyzerProcessor(final SalesAnalyzerDirectoryConfiguration pathConfiguration, final SalesAnalyzerProcessorFileDiscovery fileDiscovery, final SalesAnalyzerReportGenerator reportGenerator, final SalesAnalyzerListener listener) {
        this.pathConfiguration = pathConfiguration;
        this.fileDiscovery = fileDiscovery;
        this.reportGenerator = reportGenerator;
        this.listener = listener;
    }

    @PostConstruct
    public void process() {
        logger.info("Processing files from %{}%/{}", pathConfiguration.getEnvVar(), pathConfiguration.getInputDir());
        try {
            final List<String> remainingFiles = fileDiscovery.discoverRemainingFiles();
            logger.info("{} new files found", remainingFiles.size());

            remainingFiles.forEach(reportGenerator::generate);
            logger.info("Reports processed successfully!");

            Optional.ofNullable(listener).ifPresent(SalesAnalyzerListener::listen);
        } catch (final SalesAnalyzerIOException ex) {
            logger.error("Error processing files!", ex);
        }
    }

}
