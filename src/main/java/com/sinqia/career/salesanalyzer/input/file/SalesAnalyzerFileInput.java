package com.sinqia.career.salesanalyzer.input.file;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerDirectoryConfiguration;
import com.sinqia.career.salesanalyzer.input.SalesAnalyzerInput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SalesAnalyzerFileInput implements SalesAnalyzerInput {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesAnalyzerDirectoryConfiguration directoryConfiguration;

    public SalesAnalyzerFileInput(final SalesAnalyzerDirectoryConfiguration directoryConfiguration) {
        this.directoryConfiguration = directoryConfiguration;
    }

    @Override
    public List<String> read(final String filename) {
        logger.info("Reading file %{}%/{}/{}", directoryConfiguration.getEnvVar(), directoryConfiguration.getInputDir(), filename);
        final String baseDir = System.getenv(directoryConfiguration.getEnvVar());
        final Path inputDir = Paths.get(baseDir + "/" + directoryConfiguration.getInputDir());

        try (FileInputStream fileInputStream = new FileInputStream(inputDir.resolve(filename).toFile())) {
            return IOUtils.readLines(fileInputStream, StandardCharsets.ISO_8859_1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
