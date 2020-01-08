package com.sinqia.career.salesanalyzer.io.output.file;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.config.FileOutputReportInfoConfiguration;
import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.dto.ReportDataDTO;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SalesAnalyzerFileOutput implements SalesAnalyzerOutput {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DirectoryConfiguration directoryConfiguration;

    private final FileOutputReportInfoConfiguration fileOutputReportInfoConfiguration;

    private final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    public SalesAnalyzerFileOutput(final DirectoryConfiguration directoryConfiguration,
                                   final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration,
                                   final FileOutputReportInfoConfiguration fileOutputReportInfoConfiguration) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileExtensionConfiguration = fileExtensionConfiguration;
        this.fileOutputReportInfoConfiguration = fileOutputReportInfoConfiguration;
    }

    @Override
    public void write(final String inputFileName, final ReportDataDTO reportData) {
        final String outputFileName = inputFileName.substring(0, inputFileName.length() - 4) + fileExtensionConfiguration.getOutputExtension();
        logger.info("Generating report file {}", outputFileName);

        final String baseDir = System.getenv(directoryConfiguration.getEnvVar());
        final Path outputDir = Paths.get(baseDir + "/" + directoryConfiguration.getOutputDir());
        final Path outputFile = outputDir.resolve(outputFileName);

        final List<String> content = Arrays.asList(
                String.format(fileOutputReportInfoConfiguration.getReportClientCountInfo(), reportData.getClientCount()),
                String.format(fileOutputReportInfoConfiguration.getReportSellerCountInfo(), reportData.getSellerCount()),
                String.format(fileOutputReportInfoConfiguration.getReportBestSaleIdInfo(), reportData.getBestSaleId()),
                String.format(fileOutputReportInfoConfiguration.getReportWorstSellerInfo(), reportData.getWorstSeller()));

        try (final FileOutputStream fileOutputStream = new FileOutputStream(outputFile.toFile())) {
            IOUtils.writeLines(content, null, fileOutputStream, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            logger.error("Error writing report file {}", outputFile, ex);
        }

    }

}
