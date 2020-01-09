package com.sinqia.career.salesanalyzer.parser.data;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SaleItemDTO;
import com.sinqia.career.salesanalyzer.parser.data.SaleDataParser;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SaleDataParserTest {

    private static final String FORMAT_ID = "003";

    private static final String DELIMITER = "ç";

    private static final String ITEMS_DELIMITER = ",";

    private static final String ITEM_DATA_DELIMITER = "-";

    private final SaleDataParser parser;

    public SaleDataParserTest() {
        parser = new SaleDataParser(new FileInputDelimiterConfiguration(DELIMITER, ITEMS_DELIMITER, ITEM_DATA_DELIMITER));
    }

    @Test
    void parseSuccess() {
        // Given
        final String pedroSaleId = "10";
        final String pedroName = "Pedro";

        final int pedroSaleItem1Id = 1;
        final int pedroSaleItem1Quantity = 10;
        final double pedroSaleItem1Price = 100d;
        final String pedroSaleItem1 = pedroSaleItem1Id + ITEM_DATA_DELIMITER + pedroSaleItem1Quantity + ITEM_DATA_DELIMITER + pedroSaleItem1Price;

        final int pedroSaleItem2Id = 2;
        final int pedroSaleItem2Quantity = 30;
        final double pedroSaleItem2Price = 2.5d;
        final String pedroSaleItem2 = pedroSaleItem2Id + ITEM_DATA_DELIMITER + pedroSaleItem2Quantity + ITEM_DATA_DELIMITER + pedroSaleItem2Price;

        final int pedroSaleItem3Id = 3;
        final int pedroSaleItem3Quantity = 40;
        final double pedroSaleItem3Price = 3.1d;
        final String pedroSaleItem3 = pedroSaleItem3Id + ITEM_DATA_DELIMITER + pedroSaleItem3Quantity + ITEM_DATA_DELIMITER + pedroSaleItem3Price;

        final StringJoiner pedroSaleJoiner = new StringJoiner(ITEMS_DELIMITER, "[", "]");
        Stream.of(pedroSaleItem1, pedroSaleItem2, pedroSaleItem3).forEach(pedroSaleJoiner::add);
        final String pedroSaleItems = pedroSaleJoiner.toString();
        final String pedroSale = String.join(DELIMITER, FORMAT_ID, pedroSaleId, pedroSaleItems, pedroName);

        final SaleItemDTO pedroSaleItemDTO1 = new SaleItemDTO(pedroSaleItem1Id, pedroSaleItem1Quantity, pedroSaleItem1Price);
        final SaleItemDTO pedroSaleItemDTO2 = new SaleItemDTO(pedroSaleItem2Id, pedroSaleItem2Quantity, pedroSaleItem2Price);
        final SaleItemDTO pedroSaleItemDTO3 = new SaleItemDTO(pedroSaleItem3Id, pedroSaleItem3Quantity, pedroSaleItem3Price);
        final List<SaleItemDTO> pedroSaleItemDTOs = Arrays.asList(pedroSaleItemDTO1, pedroSaleItemDTO2, pedroSaleItemDTO3);
        final SaleDTO pedroSaleDTO = new SaleDTO(pedroSaleId, pedroSaleItemDTOs, pedroName);

        final String pauloSaleId = "08";
        final String pauloName = "Paulo";

        final int pauloSaleItem1Id = 1;
        final int pauloSaleItem1Quantity = 34;
        final double pauloSaleItem1Price = 10d;
        final String pauloSaleItem1 = pauloSaleItem1Id + ITEM_DATA_DELIMITER + pauloSaleItem1Quantity + ITEM_DATA_DELIMITER + pauloSaleItem1Price;

        final int pauloSaleItem2Id = 2;
        final int pauloSaleItem2Quantity = 33;
        final double pauloSaleItem2Price = 1.5d;
        final String pauloSaleItem2 = pauloSaleItem2Id + ITEM_DATA_DELIMITER + pauloSaleItem2Quantity + ITEM_DATA_DELIMITER + pauloSaleItem2Price;

        final int pauloSaleItem3Id = 3;
        final int pauloSaleItem3Quantity = 40;
        final double pauloSaleItem3Price = .1d;
        final String pauloSaleItem3 = pauloSaleItem3Id + ITEM_DATA_DELIMITER + pauloSaleItem3Quantity + ITEM_DATA_DELIMITER + pauloSaleItem3Price;

        final StringJoiner pauloSaleJoiner = new StringJoiner(ITEMS_DELIMITER, "[", "]");
        Stream.of(pauloSaleItem1, pauloSaleItem2, pauloSaleItem3).forEach(pauloSaleJoiner::add);
        final String pauloSaleItems = pauloSaleJoiner.toString();

        final String pauloSale = String.join(DELIMITER, FORMAT_ID, pauloSaleId, pauloSaleItems, pauloName);

        final SaleItemDTO pauloSaleItemDTO1 = new SaleItemDTO(pauloSaleItem1Id, pauloSaleItem1Quantity, pauloSaleItem1Price);
        final SaleItemDTO pauloSaleItemDTO2 = new SaleItemDTO(pauloSaleItem2Id, pauloSaleItem2Quantity, pauloSaleItem2Price);
        final SaleItemDTO pauloSaleItemDTO3 = new SaleItemDTO(pauloSaleItem3Id, pauloSaleItem3Quantity, pauloSaleItem3Price);
        final List<SaleItemDTO> pauloSaleItemDTOs = Arrays.asList(pauloSaleItemDTO1, pauloSaleItemDTO2, pauloSaleItemDTO3);

        final SaleDTO pauloSaleDTO = new SaleDTO(pauloSaleId, pauloSaleItemDTOs, pauloName);

        final List<String> content = Arrays.asList(pedroSale, pauloSale);

        // When
        final List<SaleDTO> sales = parser.parse(content);

        // Then
        assertThat(sales).isNotNull().hasSize(content.size());

        sales.sort(Comparator.comparing(SaleDTO::getSellerName));
        assertThat(sales.get(0)).isNotNull()
                .isEqualToComparingOnlyGivenFields(pauloSaleDTO, "id", "sellerName");
        assertThat(sales.get(0).getItems()).isNotNull();
        assertThat(sales.get(0).getItems())
                .extracting(SaleItemDTO::getId)
                .containsExactlyInAnyOrder(pauloSaleItemDTO1.getId(), pauloSaleItemDTO2.getId(), pauloSaleItemDTO3.getId());
        assertThat(sales.get(0).getItems())
                .extracting(SaleItemDTO::getQuantity)
                .containsExactlyInAnyOrder(pauloSaleItemDTO1.getQuantity(), pauloSaleItemDTO2.getQuantity(), pauloSaleItemDTO3.getQuantity());
        assertThat(sales.get(0).getItems())
                .extracting(SaleItemDTO::getPrice)
                .containsExactlyInAnyOrder(pauloSaleItemDTO1.getPrice(), pauloSaleItemDTO2.getPrice(), pauloSaleItemDTO3.getPrice());
        assertThat(sales.get(1)).isNotNull()
                .isEqualToComparingOnlyGivenFields(pedroSaleDTO, "id", "sellerName");
        assertThat(sales.get(1).getItems()).isNotNull();
        assertThat(sales.get(1).getItems())
                .extracting(SaleItemDTO::getId)
                .containsExactlyInAnyOrder(pedroSaleItemDTO1.getId(), pedroSaleItemDTO2.getId(), pedroSaleItemDTO3.getId());
        assertThat(sales.get(1).getItems())
                .extracting(SaleItemDTO::getQuantity)
                .containsExactlyInAnyOrder(pedroSaleItemDTO1.getQuantity(), pedroSaleItemDTO2.getQuantity(), pedroSaleItemDTO3.getQuantity());
        assertThat(sales.get(1).getItems())
                .extracting(SaleItemDTO::getPrice)
                .containsExactlyInAnyOrder(pedroSaleItemDTO1.getPrice(), pedroSaleItemDTO2.getPrice(), pedroSaleItemDTO3.getPrice());
    }

    @Test
    void parseWithDelimiterAsPartOfNameSuccess() {
        // Given
        final String saleId = "10";
        final String seller = "Açucena";
        final String sale = String.join(DELIMITER, FORMAT_ID, saleId, "[]", seller);

        final SaleDTO saleDTO = new SaleDTO(saleId, Collections.emptyList(), seller);

        final List<String> content = Collections.singletonList(sale);

        // When
        final List<SaleDTO> sales = parser.parse(content);

        // Then
        assertThat(sales).isNotNull().hasSize(content.size());

        sales.sort(Comparator.comparing(SaleDTO::getSellerName));
        assertThat(sales.get(0)).isNotNull()
                .isEqualToComparingOnlyGivenFields(saleDTO, "id", "sellerName");
        assertThat(sales.get(0).getItems()).isNotNull().isEmpty();
    }

}