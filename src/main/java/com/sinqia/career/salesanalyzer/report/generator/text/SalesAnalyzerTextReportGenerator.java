package com.sinqia.career.salesanalyzer.report.generator.text;

import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzeReport;
import com.sinqia.career.salesanalyzer.report.generator.SalesAnalyzerReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Deprecated
public class SalesAnalyzerTextReportGenerator implements SalesAnalyzerReportGenerator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesAnalyzerInput input;

    private final SalesAnalyzerParser parser;

    private final SalesAnalyzerOutput output;

    private final SalesAnalyzeReport report;

    public SalesAnalyzerTextReportGenerator(final SalesAnalyzerInput input,
                                            final SalesAnalyzerParser parser,
                                            final SalesAnalyzerOutput output,
                                            final SalesAnalyzeReport report) {
        this.input = input;
        this.parser = parser;
        this.output = output;
        this.report = report;
    }

    @Override
    public void generate(final String filename) {
        logger.info("Reading file {}...", filename);
        final List<String> fileData = input.read(filename);

        logger.info("Parsing file {}...", filename);
        final SalesDataDTO salesData = parser.parse(fileData);

        logger.info("Analyzing file {}...", filename);
        //final ResumeReportDataDTO reportData = report.analyze(salesData);

        logger.info("Generating report for file {}...", filename);
        //output.write(filename, reportData);

        logger.info("Report successfully generated for file {}", filename);
    }

}
