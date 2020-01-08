package com.sinqia.career.salesanalyzer.processor.discovery;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerDirectoryConfiguration;
import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.exception.config.SalesAnalyzerFileExtensionConfigurationException;
import com.sinqia.career.salesanalyzer.exception.io.SalesAnalyzerIOException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SalesAnalyzerProcessorFileDiscovery {

    private final SalesAnalyzerDirectoryConfiguration pathConfiguration;

    private final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    public SalesAnalyzerProcessorFileDiscovery(final SalesAnalyzerDirectoryConfiguration pathConfiguration, final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration) {
        this.pathConfiguration = pathConfiguration;
        this.fileExtensionConfiguration = fileExtensionConfiguration;
    }

    public List<String> discoverRemainingFiles() throws SalesAnalyzerIOException {
        final List<String> allInputFiles = getAllInputFiles();
        final List<String> allOutputFiles = getAllOutputFiles();
        final List<String> allOutputOriginalFiles = allOutputFiles.stream()
                .map(this::originalFilename)
                .collect(Collectors.toList());
        return allInputFiles.stream()
                .filter(filename -> !allOutputOriginalFiles.contains(filename))
                .collect(Collectors.toList());
    }

    String originalFilename(final String outputFilename) {
        if (fileExtensionConfiguration.getOutputExtension().length() < fileExtensionConfiguration.getInputExtension().length()) {
            throw new SalesAnalyzerFileExtensionConfigurationException();
        }
        return outputFilename.substring(0, outputFilename.length() - fileExtensionConfiguration.getOutputExtension().length()) + fileExtensionConfiguration.getInputExtension();
    }

    private List<String> getAllInputFiles() throws SalesAnalyzerIOException {
        try (final Stream<Path> walk = filesWalk(pathConfiguration.getInputDir())) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile().getName())
                    .filter(file -> file.endsWith(fileExtensionConfiguration.getInputExtension()))
                    .collect(Collectors.toList());
        } catch (final IOException ex) {
            throw new SalesAnalyzerIOException(ex);
        }
    }

    Stream<Path> filesWalk(final String innerDirectory) throws IOException {
        return Files.walk(Paths.get(System.getenv(pathConfiguration.getEnvVar()) + "/" + innerDirectory));
    }

    private List<String> getAllOutputFiles() throws SalesAnalyzerIOException {
        try (final Stream<Path> walk = filesWalk(pathConfiguration.getOutputDir())) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile().getName())
                    .filter(file -> file.endsWith(fileExtensionConfiguration.getOutputExtension()))
                    .collect(Collectors.toList());
        } catch (final IOException ex) {
            throw new SalesAnalyzerIOException(ex);
        }
    }

}
