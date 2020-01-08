package com.sinqia.career.salesanalyzer.report.text;

import com.sinqia.career.salesanalyzer.business.SalesDataAnalyzer;
import com.sinqia.career.salesanalyzer.dto.ReportDataDTO;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzerReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SalesAnalyzerTextReportGenerator implements SalesAnalyzerReportGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesAnalyzerInput input;

    private final SalesAnalyzerParser parser;

    private final SalesAnalyzerOutput output;

    private final SalesDataAnalyzer dataAnalyzer;

    public SalesAnalyzerTextReportGenerator(final SalesAnalyzerInput input, final SalesAnalyzerParser parser, final SalesAnalyzerOutput output, final SalesDataAnalyzer dataAnalyzer) {
        this.input = input;
        this.parser = parser;
        this.output = output;
        this.dataAnalyzer = dataAnalyzer;
    }

    @Override
    public void generate(final String filename) {
        logger.info("Reading file {}...", filename);
        final List<String> fileData = input.read(filename);

        logger.info("Parsing file {}...", filename);
        final SalesDataDTO salesData = parser.parse(fileData);

        logger.info("Analyzing file {}...", filename);
        final ReportDataDTO reportData = dataAnalyzer.analyze(salesData);

        logger.info("Generating report for file {}...", filename);
        output.write(filename, reportData);

        logger.info("Report successfully generated for file {}", filename);
    }

}
