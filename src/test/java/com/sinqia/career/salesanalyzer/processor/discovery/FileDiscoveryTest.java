package com.sinqia.career.salesanalyzer.processor.discovery;

import com.sinqia.career.salesanalyzer.config.FileExtensionConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FileExtensionConfiguration.class)
@TestPropertySource(properties = {"input.extension=.dat", "output.extension=.done.dat"})
class FileDiscoveryTest {

    @Autowired
    private FileExtensionConfiguration fileExtensionConfiguration;

    @Test
    void discoverRemainingFiles() {
    }

    @Test
    void filesWalk() {
    }

}