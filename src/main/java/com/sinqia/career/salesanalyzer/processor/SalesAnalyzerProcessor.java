package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.exception.io.FileException;
import com.sinqia.career.salesanalyzer.processor.discovery.FileDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class SalesAnalyzerProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DirectoryConfiguration directoryConfiguration;

    private final FileDiscovery fileDiscovery;

    private final SalesAnalyzerFileProcessor fileProcessor;

    private final SalesAnalyzerMonitor monitor;

    public SalesAnalyzerProcessor(final DirectoryConfiguration directoryConfiguration,
                                  final FileDiscovery fileDiscovery,
                                  final SalesAnalyzerFileProcessor fileProcessor,
                                  final SalesAnalyzerMonitor monitor) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileDiscovery = fileDiscovery;
        this.fileProcessor = fileProcessor;
        this.monitor = monitor;
    }

    @PostConstruct
    public void process() {
        logger.info("Processing files from %{}%/{}", directoryConfiguration.getEnvVar(), directoryConfiguration.getInputDir());
        try {
            final List<String> filesToAnalyze = fileDiscovery.discoverFiles();
            logger.info("{} new files found", filesToAnalyze.size());

            filesToAnalyze.forEach(fileProcessor::processFile);

            Optional.ofNullable(monitor)
                    .filter(m -> directoryConfiguration.isMonitor())
                    .ifPresent(SalesAnalyzerMonitor::activate);
        } catch (final FileException ex) {
            logger.error("Error processing files!", ex);
        }
    }

}
