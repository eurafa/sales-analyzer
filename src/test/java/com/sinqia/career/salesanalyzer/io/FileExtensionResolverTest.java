package com.sinqia.career.salesanalyzer.io;

import com.sinqia.career.salesanalyzer.config.FileExtensionConfiguration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileExtensionResolverTest {

    private static final String INPUT_EXTENSION = ".in";

    private static final String OUTPUT_EXTENSION = ".out";

    private final FileExtensionResolver fileExtensionResolver;

    FileExtensionResolverTest() {
        final FileExtensionConfiguration config = new FileExtensionConfiguration(INPUT_EXTENSION, OUTPUT_EXTENSION);
        fileExtensionResolver = new FileExtensionResolver(config);
    }

    @Test
    void testAsInputSimpleSuccess() {
        // Given
        final String filename = "file";
        final String outputFilename = filename + OUTPUT_EXTENSION;

        // When
        final String inputFilename = fileExtensionResolver.asInput(outputFilename);

        // Then
        final String expectedInputFilename = filename + INPUT_EXTENSION;
        assertThat(inputFilename).isEqualTo(expectedInputFilename);
    }

    @Test
    void testAsInputWithExtensionAsPartOfNameSuccess() {
        // Given
        final String filename = "file";
        final String outputFilename = filename + OUTPUT_EXTENSION + OUTPUT_EXTENSION;

        // When
        final String inputFilename = fileExtensionResolver.asInput(outputFilename);

        // Then
        final String expectedInputFilename = filename + OUTPUT_EXTENSION + INPUT_EXTENSION;
        assertThat(inputFilename).isEqualTo(expectedInputFilename);
    }

    @Test
    void testAsInputStartingWithExtensionSuccess() {
        // Given
        final String filename = "file";
        final String outputFilename = OUTPUT_EXTENSION + filename + OUTPUT_EXTENSION;

        // When
        final String inputFilename = fileExtensionResolver.asInput(outputFilename);

        // Then
        final String expectedInputFilename = OUTPUT_EXTENSION + filename + INPUT_EXTENSION;
        assertThat(inputFilename).isEqualTo(expectedInputFilename);
    }

    @Test
    void testAsOutputSimpleSuccess() {
        // Given
        final String filename = "file";
        final String inputFilename = filename + INPUT_EXTENSION;

        // When
        final String outputFilename = fileExtensionResolver.asOutput(inputFilename);

        // Then
        final String expectedOutputFilename = filename + OUTPUT_EXTENSION;
        assertThat(outputFilename).isEqualTo(expectedOutputFilename);
    }

    @Test
    void testAsOutputWithExtensionAsPartOfNameSuccess() {
        // Given
        final String filename = "file";
        final String inputFilename = filename + INPUT_EXTENSION + INPUT_EXTENSION;

        // When
        final String outputFilename = fileExtensionResolver.asOutput(inputFilename);

        // Then
        final String expectedOutputFilename = filename + INPUT_EXTENSION + OUTPUT_EXTENSION;
        assertThat(outputFilename).isEqualTo(expectedOutputFilename);
    }

    @Test
    void testAsOutputStartingWithExtensionSuccess() {
        // Given
        final String filename = "file";
        final String inputFilename = INPUT_EXTENSION + filename + INPUT_EXTENSION;

        // When
        final String outputFilename = fileExtensionResolver.asOutput(inputFilename);

        // Then
        final String expectedOutputFilename = INPUT_EXTENSION + filename + OUTPUT_EXTENSION;
        assertThat(outputFilename).isEqualTo(expectedOutputFilename);
    }

}