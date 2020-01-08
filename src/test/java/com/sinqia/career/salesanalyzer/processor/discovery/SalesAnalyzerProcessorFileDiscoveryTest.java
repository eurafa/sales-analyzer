package com.sinqia.career.salesanalyzer.processor.discovery;

import com.sinqia.career.salesanalyzer.config.SalesAnalyzerFileExtensionConfiguration;
import com.sinqia.career.salesanalyzer.exception.config.SalesAnalyzerFileExtensionConfigurationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SalesAnalyzerFileExtensionConfiguration.class)
@TestPropertySource(properties = {"input.extension=.dat", "output.extension=.done.dat"})
public class SalesAnalyzerProcessorFileDiscoveryTest {

    @Autowired
    private SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration;

    @Test
    void discoverRemainingFiles() {
    }

    @Test
    void testOriginalFilenameSuccess() {
        final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration = new SalesAnalyzerFileExtensionConfiguration(".dat", ".done.dat");
        final SalesAnalyzerProcessorFileDiscovery fileDiscovery = new SalesAnalyzerProcessorFileDiscovery(null, fileExtensionConfiguration);
        assertThat(fileDiscovery.originalFilename("filename.done.dat")).isEqualTo("filename.dat");
    }

    @Test
    void testOriginalFilenameFailure() {
        final SalesAnalyzerFileExtensionConfiguration fileExtensionConfiguration = new SalesAnalyzerFileExtensionConfiguration(".something", ".some");
        final SalesAnalyzerProcessorFileDiscovery fileDiscovery = new SalesAnalyzerProcessorFileDiscovery(null, fileExtensionConfiguration);
        final Throwable thrown = Assertions.catchThrowable(() -> fileDiscovery.originalFilename("filename.some"));
        assertThat(thrown)
                .isInstanceOf(SalesAnalyzerFileExtensionConfigurationException.class)
                .hasNoCause();
    }

    @Test
    void filesWalk() {
    }

}