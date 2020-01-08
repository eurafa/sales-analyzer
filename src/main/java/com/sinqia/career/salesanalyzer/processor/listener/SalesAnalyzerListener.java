package com.sinqia.career.salesanalyzer.processor.listener;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerDirectoryConfiguration;
import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.report.SalesAnalyzerReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

@Component
public class SalesAnalyzerListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesAnalyzerDirectoryConfiguration directoryConfiguration;

    private final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    private final SalesAnalyzerReportGenerator reportGenerator;

    public SalesAnalyzerListener(final SalesAnalyzerDirectoryConfiguration directoryConfiguration, final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration, final SalesAnalyzerReportGenerator reportGenerator) {
        this.directoryConfiguration = directoryConfiguration;
        this.fileExtensionConfiguration = fileExtensionConfiguration;
        this.reportGenerator = reportGenerator;
    }

    public void listen() {
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
                        reportGenerator.generate(filename);
                    }
                }
            } while (key.reset());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
