package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.exception.io.SalesAnalyzerIOException;
import com.sinqia.career.salesanalyzer.processor.discovery.FileDiscovery;
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

    private final DirectoryConfiguration pathConfiguration;

    private final FileDiscovery fileDiscovery;

    private final SalesAnalyzerReportGenerator reportGenerator;

    private final SalesAnalyzerListener listener;

    public SalesAnalyzerProcessor(final DirectoryConfiguration pathConfiguration, final FileDiscovery fileDiscovery, final SalesAnalyzerReportGenerator reportGenerator, final SalesAnalyzerListener listener) {
        this.pathConfiguration = pathConfiguration;
        this.fileDiscovery = fileDiscovery;
        this.reportGenerator = reportGenerator;
        this.listener = listener;
    }

    @PostConstruct
    public void process() {
        logger.info("Processing files from %{}%/{}", pathConfiguration.getEnvVar(), pathConfiguration.getInputDir());
        try {
            final List<String> filesToAnalyze = fileDiscovery.discoverFiles();
            logger.info("{} new files found", filesToAnalyze.size());

            filesToAnalyze.forEach(reportGenerator::generate);
            logger.info("Reports processed successfully!");

            Optional.ofNullable(listener).ifPresent(SalesAnalyzerListener::listen);
        } catch (final SalesAnalyzerIOException ex) {
            logger.error("Error processing files!", ex);
        }
    }

}
