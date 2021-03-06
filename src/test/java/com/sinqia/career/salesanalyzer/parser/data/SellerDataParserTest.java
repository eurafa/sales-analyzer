package com.sinqia.career.salesanalyzer.parser.data;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.dto.SellerDTO;
import com.sinqia.career.salesanalyzer.parser.data.SellerDataParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SellerDataParserTest {

    private static final String FORMAT_ID = "001";

    private static final String DELIMITER = "ç";

    private final SellerDataParser parser;

    public SellerDataParserTest() {
        parser = new SellerDataParser(new FileInputDelimiterConfiguration(DELIMITER, null, null));
    }

    @Test
    void parseSuccess() {
        // Given
        final String pedroCpf = "1234567891234";
        final String pedroName = "Pedro";
        final double pedroSalary = 50000d;
        final SellerDTO pedro = new SellerDTO(pedroCpf, pedroName, pedroSalary);

        final String pauloCpf = "3245678865434";
        final String pauloName = "Paulo";
        final double pauloSalary = 40000.99d;
        final SellerDTO paulo = new SellerDTO(pauloCpf, pauloName, pauloSalary);

        final List<String> content = Arrays.asList(
                String.join(DELIMITER, FORMAT_ID, pedroCpf, pedroName, String.valueOf(pedroSalary)),
                String.join(DELIMITER, FORMAT_ID, pauloCpf, pauloName, String.valueOf(pauloSalary)));

        // When
        final List<SellerDTO> sellers = parser.parse(content);

        // Then
        assertThat(sellers).isNotNull().hasSize(content.size());

        sellers.sort(Comparator.comparing(SellerDTO::getName));
        assertThat(sellers.get(0)).isNotNull().isEqualToComparingFieldByField(paulo);
        assertThat(sellers.get(1)).isNotNull().isEqualToComparingFieldByField(pedro);
    }

    @Test
    void parseWithDelimiterAsPartOfNameSuccess() {
        // Given
        final String cpf = "1234567891234";
        final String name = "Lourenço Viçoza";
        final double salary = 10000d;
        final SellerDTO seller = new SellerDTO(cpf, name, salary);

        final List<String> content = Collections.singletonList(String.join(DELIMITER, FORMAT_ID, cpf, name, String.valueOf(salary)));

        // When
        final List<SellerDTO> sellers = parser.parse(content);

        // Then
        assertThat(sellers).isNotNull().hasSize(content.size());
        assertThat(sellers.get(0)).isNotNull().isEqualToComparingFieldByField(seller);
    }

}