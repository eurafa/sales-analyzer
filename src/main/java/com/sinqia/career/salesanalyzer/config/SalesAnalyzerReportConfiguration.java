package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.input.file.SalesAnalyzerFileInput;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.io.output.file.SalesAnalyzerFileOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzeReport;
import com.sinqia.career.salesanalyzer.report.generator.SalesAnalyzerReportGenerator;
import com.sinqia.career.salesanalyzer.report.generator.text.SalesAnalyzerTextReportGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerReportConfiguration {

    @Bean
    public SalesAnalyzerReportGenerator reportConfig(final DirectoryConfiguration directoryConfiguration,
                                                     final SalesAnalyzerParser parser,
                                                     final FileExtensionConfiguration fileExtensionConfiguration,
                                                     final SalesAnalyzeReport report) {
        final SalesAnalyzerInput input = new SalesAnalyzerFileInput(directoryConfiguration);
        final SalesAnalyzerOutput output = new SalesAnalyzerFileOutput(directoryConfiguration, fileExtensionConfiguration);
        return new SalesAnalyzerTextReportGenerator(input, parser, output, report);
    }

}
