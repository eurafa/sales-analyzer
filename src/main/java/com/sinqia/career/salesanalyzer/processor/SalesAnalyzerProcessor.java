package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.business.SalesDataAnalyzer;
import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.exception.io.FileException;
import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.processor.discovery.FileDiscovery;
import com.sinqia.career.salesanalyzer.processor.listener.SalesAnalyzerMonitor;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzeReport;
import com.sinqia.career.salesanalyzer.report.generator.SalesAnalyzerReportGenerator;
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

    private final SalesAnalyzerFileProcessor processor;

    private final SalesAnalyzerInput input;

    private final SalesAnalyzerParser parser;

    private final SalesDataAnalyzer analyzer;

    private final SalesAnalyzeReport report;

    private final SalesAnalyzerOutput output;

    private final SalesAnalyzerReportGenerator reportGenerator;

    private final SalesAnalyzerMonitor listener;

    public SalesAnalyzerProcessor(final DirectoryConfiguration directoryConfiguration,
                                  final FileDiscovery fileDiscovery,
                                  final SalesAnalyzerFileProcessor processor,
                                  final SalesAnalyzerInput input,
                                  final SalesAnalyzerParser parser,
                                  final SalesDataAnalyzer analyzer,
                                  final SalesAnalyzeReport report,
                                  final SalesAnalyzerOutput output,
                                  final SalesAnalyzerReportGenerator reportGenerator,
                                  final SalesAnalyzerMonitor listener) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileDiscovery = fileDiscovery;
        this.processor = processor;
        this.input = input;
        this.parser = parser;
        this.analyzer = analyzer;
        this.report = report;
        this.output = output;
        this.reportGenerator = reportGenerator;
        this.listener = listener;
    }

    @PostConstruct
    public void process() {
        logger.info("Processing files from %{}%/{}", directoryConfiguration.getEnvVar(), directoryConfiguration.getInputDir());
        try {
            final List<String> filesToAnalyze = fileDiscovery.discoverFiles();
            logger.info("{} new files found", filesToAnalyze.size());

            filesToAnalyze.forEach(processor::processFile);

            Optional.ofNullable(listener).ifPresent(SalesAnalyzerMonitor::activate);
        } catch (final FileException ex) {
            logger.error("Error processing files!", ex);
        }
    }

}
