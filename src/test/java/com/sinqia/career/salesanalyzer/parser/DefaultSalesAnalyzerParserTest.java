package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.config.FileInputLayoutFormatConfiguration;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.parser.data.ClientDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SaleDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SellerDataParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultSalesAnalyzerParserTest {

    private final DefaultSalesAnalyzerParser parser;

    DefaultSalesAnalyzerParserTest() {
        final FileInputLayoutFormatConfiguration layoutFormatConfiguration = new FileInputLayoutFormatConfiguration("001", "002", "003");
        final FileInputDelimiterConfiguration delimiterConfiguration = new FileInputDelimiterConfiguration("ç", ",", "-");
        final SellerDataParser sellerParser = new SellerDataParser(delimiterConfiguration);
        final ClientDataParser clientDataParser = new ClientDataParser(delimiterConfiguration);
        final SaleDataParser saleDataParser = new SaleDataParser(delimiterConfiguration);
        parser = new DefaultSalesAnalyzerParser(layoutFormatConfiguration, delimiterConfiguration, sellerParser, clientDataParser, saleDataParser);
    }

    @Test
    void testParseFullSuccess() {
        // Given
        final String seller = "001ç1çSellerç2500";
        final String client = "002ç2çClientçRural";
        final String sale = "003ç03ç[]çSeller";
        final List<String> content = Arrays.asList(seller, client, sale);

        // When
        final SalesDataDTO salesDataDTO = parser.parse(content);

        // Then
        assertThat(salesDataDTO).isNotNull();
        assertThat(salesDataDTO.getSellers()).isNotNull().hasSize(1);
        assertThat(salesDataDTO.getClients()).isNotNull().hasSize(1);
        assertThat(salesDataDTO.getSales()).isNotNull().hasSize(1);
    }

    @Test
    void testParseOnlySellerSuccess() {
        // Given
        final String seller = "001ç1çSellerç2500";
        final List<String> content = Collections.singletonList(seller);

        // When
        final SalesDataDTO salesDataDTO = parser.parse(content);

        // Then
        assertThat(salesDataDTO).isNotNull();
        assertThat(salesDataDTO.getSellers()).isNotNull().hasSize(1);
        assertThat(salesDataDTO.getClients()).isNotNull().isEmpty();
        assertThat(salesDataDTO.getSales()).isNotNull().isEmpty();
    }

    @Test
    void testParseOnlyClientSuccess() {
        // Given
        final String client = "002ç2çClientçRural";
        final List<String> content = Collections.singletonList(client);

        // When
        final SalesDataDTO salesDataDTO = parser.parse(content);

        // Then
        assertThat(salesDataDTO).isNotNull();
        assertThat(salesDataDTO.getSellers()).isNotNull().isEmpty();
        assertThat(salesDataDTO.getClients()).isNotNull().hasSize(1);
        assertThat(salesDataDTO.getSales()).isNotNull().isEmpty();
    }

    @Test
    void testParseOnlySaleSuccess() {
        // Given
        final String sale = "003ç03ç[]çSeller";
        final List<String> content = Collections.singletonList(sale);

        // When
        final SalesDataDTO salesDataDTO = parser.parse(content);

        // Then
        assertThat(salesDataDTO).isNotNull();
        assertThat(salesDataDTO.getSellers()).isNotNull().isEmpty();
        assertThat(salesDataDTO.getClients()).isNotNull().isEmpty();
        assertThat(salesDataDTO.getSales()).isNotNull().hasSize(1);
    }

}