package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.business.SalesDataAnalyzer;
import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.exception.io.SalesAnalyzerIOException;
import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.processor.discovery.FileDiscovery;
import com.sinqia.career.salesanalyzer.processor.listener.SalesAnalyzerListener;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzeReport;
import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;
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

    private final SalesAnalyzerInput input;

    private final SalesAnalyzerParser parser;

    private final SalesDataAnalyzer analyzer;

    private final SalesAnalyzeReport report;

    private final SalesAnalyzerOutput output;

    private final SalesAnalyzerReportGenerator reportGenerator;

    private final SalesAnalyzerListener listener;

    public SalesAnalyzerProcessor(final DirectoryConfiguration directoryConfiguration,
                                  final FileDiscovery fileDiscovery,
                                  final SalesAnalyzerInput input,
                                  final SalesAnalyzerParser parser,
                                  final SalesDataAnalyzer analyzer,
                                  final SalesAnalyzeReport report,
                                  final SalesAnalyzerOutput output,
                                  final SalesAnalyzerReportGenerator reportGenerator,
                                  final SalesAnalyzerListener listener) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileDiscovery = fileDiscovery;
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

            filesToAnalyze.forEach(this::processFile);

            //filesToAnalyze.forEach(reportGenerator::generate);
            //logger.info("Reports processed successfully!");

            Optional.ofNullable(listener).ifPresent(SalesAnalyzerListener::listen);
        } catch (final SalesAnalyzerIOException ex) {
            logger.error("Error processing files!", ex);
        }
    }

    private void processFile(final String filename) {
        logger.info("[{}] Reading...", filename);
        final List<String> fileData = input.read(filename);

        logger.info("[{}] Parsing...", filename);
        final SalesDataDTO salesData = parser.parse(fileData);

        logger.info("[{}] Analyzing...", filename);
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(salesData);

        logger.info("[{}] Generating report...", filename);
        final List<String> reportData = report.generateReport(analyzeResult);

        logger.info("[{}] Saving report...", filename);
        output.write(filename, reportData);

        logger.info("[{}] Process finished!", filename);
    }

}
