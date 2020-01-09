package com.sinqia.career.salesanalyzer.processor.discovery;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.exception.io.FileException;
import com.sinqia.career.salesanalyzer.io.FileExtensionResolver;
import com.sinqia.career.salesanalyzer.io.FileExtensionValidator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileDiscovery {

    private final DirectoryConfiguration pathConfiguration;

    private final FileExtensionResolver fileExtensionResolver;

    private final FileExtensionValidator fileExtensionValidator;

    public FileDiscovery(final DirectoryConfiguration pathConfiguration, final FileExtensionResolver fileExtensionResolver, final FileExtensionValidator fileExtensionValidator) {
        this.pathConfiguration = pathConfiguration;
        this.fileExtensionResolver = fileExtensionResolver;
        this.fileExtensionValidator = fileExtensionValidator;
    }

    public List<String> discoverFiles() throws FileException {
        final List<String> allInputFiles = getAllInputFiles();
        final List<String> allOutputFiles = getAllOutputFiles();
        final List<String> allOutputOriginalFiles = allOutputFiles.stream()
                .map(fileExtensionResolver::asInput)
                .collect(Collectors.toList());
        return allInputFiles.stream()
                .filter(filename -> !allOutputOriginalFiles.contains(filename))
                .collect(Collectors.toList());
    }

    private List<String> getAllInputFiles() throws FileException {
        try (final Stream<Path> walk = filesWalk(pathConfiguration.getInputDir())) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile().getName())
                    .filter(fileExtensionValidator::isInput)
                    .collect(Collectors.toList());
        } catch (final IOException ex) {
            throw new FileException(ex);
        }
    }

    Stream<Path> filesWalk(final String innerDirectory) throws IOException {
        return Files.walk(Paths.get(System.getenv(pathConfiguration.getEnvVar()) + "/" + innerDirectory));
    }

    private List<String> getAllOutputFiles() throws FileException {
        try (final Stream<Path> walk = filesWalk(pathConfiguration.getOutputDir())) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile().getName())
                    .filter(fileExtensionValidator::isOutput)
                    .collect(Collectors.toList());
        } catch (final IOException ex) {
            throw new FileException(ex);
        }
    }

}
