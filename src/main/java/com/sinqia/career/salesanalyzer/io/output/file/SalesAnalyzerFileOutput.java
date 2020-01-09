package com.sinqia.career.salesanalyzer.io.output.file;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.config.FileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.io.output.SalesAnalyzerOutput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SalesAnalyzerFileOutput implements SalesAnalyzerOutput {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DirectoryConfiguration directoryConfiguration;

    private final FileExtensionConfiguration fileExtensionConfiguration;

    public SalesAnalyzerFileOutput(final DirectoryConfiguration directoryConfiguration,
                                   final FileExtensionConfiguration fileExtensionConfiguration) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileExtensionConfiguration = fileExtensionConfiguration;
    }

    @Override
    public void write(final String inputFileName, final List<String> reportData) {
        final String outputFileName = inputFileName.substring(0, inputFileName.length() - 4) + fileExtensionConfiguration.getOutputExtension();
        logger.info("Generating report file {}", outputFileName);

        final String baseDir = System.getenv(directoryConfiguration.getEnvVar());
        final Path outputDir = Paths.get(baseDir + "/" + directoryConfiguration.getOutputDir());
        final Path outputFile = outputDir.resolve(outputFileName);

        try (final FileOutputStream fileOutputStream = new FileOutputStream(outputFile.toFile())) {
            IOUtils.writeLines(reportData, null, fileOutputStream, StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            logger.error("Error writing report file {}", outputFile, ex);
        }

    }

}
