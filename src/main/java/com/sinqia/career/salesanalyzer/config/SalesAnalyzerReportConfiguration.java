package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.business.SalesDataAnalyzer;
import com.sinqia.career.salesanalyzer.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.input.file.SalesAnalyzerFileInput;
import com.sinqia.career.salesanalyzer.output.SalesAnalyzerOutput;
import com.sinqia.career.salesanalyzer.output.file.SalesAnalyzerFileOutput;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzerReportGenerator;
import com.sinqia.career.salesanalyzer.report.text.SalesAnalyzerTextReportGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerReportConfiguration {

    @Bean
    public SalesAnalyzerReportGenerator reportConfig(final SalesAnalyzerDirectoryConfiguration directoryConfiguration, final SalesAnalyzerParser parser, final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration, final SalesDataAnalyzer dataAnalyzer) {
        final SalesAnalyzerInput input = new SalesAnalyzerFileInput(directoryConfiguration);
        final SalesAnalyzerOutput output = new SalesAnalyzerFileOutput(directoryConfiguration, fileExtensionConfiguration);
        return new SalesAnalyzerTextReportGenerator(input, parser, output, dataAnalyzer);
    }

}
