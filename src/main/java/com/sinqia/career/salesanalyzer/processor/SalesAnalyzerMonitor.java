package com.sinqia.career.salesanalyzer.processor;

import com.sinqia.career.salesanalyzer.config.DirectoryConfiguration;
import com.sinqia.career.salesanalyzer.config.FileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.exception.processor.ListenerException;
import com.sinqia.career.salesanalyzer.processor.SalesAnalyzerFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

@Component
public class SalesAnalyzerMonitor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DirectoryConfiguration directoryConfiguration;

    private final FileExtensionConfiguration fileExtensionConfiguration;

    private final SalesAnalyzerFileProcessor processor;

    public SalesAnalyzerMonitor(final DirectoryConfiguration directoryConfiguration,
                                final FileExtensionConfiguration fileExtensionConfiguration,
                                final SalesAnalyzerFileProcessor processor) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileExtensionConfiguration = fileExtensionConfiguration;
        this.processor = processor;
    }

    public void activate() {
        logger.info("Listening new files from %{}%/{}", directoryConfiguration.getEnvVar(), directoryConfiguration.getInputDir());
        try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
            final Path path = Paths.get(System.getenv(directoryConfiguration.getEnvVar()) + "/" + directoryConfiguration.getInputDir());
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            do {
                key = watchService.take();
                for (final WatchEvent<?> event : key.pollEvents()) {
                    final String filename = event.context().toString();
                    if (filename.endsWith(fileExtensionConfiguration.getInputExtension())) {
                        logger.info("Received a new file: {}", filename);
                        processor.processFile(filename);
                    }
                }
            } while (key.reset());
        } catch (final IOException | InterruptedException ex) {
            logger.error("Error listening input directory", ex);
            throw new ListenerException(ex);
        }

    }

}
