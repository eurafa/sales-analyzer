package com.sinqia.career.salesanalyzer.parser.data;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SaleItemDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SaleDataParser implements DataParser<SaleDTO> {

    private final FileInputDelimiterConfiguration delimiterConfiguration;

    public SaleDataParser(final FileInputDelimiterConfiguration delimiterConfiguration) {
        this.delimiterConfiguration = delimiterConfiguration;
    }

    @Override
    public List<SaleDTO> parse(final List<String> content) {
        return Optional.ofNullable(content).map(c -> c.stream().map(this::parseLine).collect(Collectors.toList())).orElseGet(Collections::emptyList);
    }

    private SaleDTO parseLine(final String line) {
        final String[] split = line.split(delimiterConfiguration.getDataDelimiter());

        final String layoutFormatId = split[0];
        final String id = split[1];
        final String items = split[2];
        final String sellerName = split.length == 4 ? split[3] : line.substring(layoutFormatId.length() + 1 + id.length() + 1 + items.length() + 1);

        return new SaleDTO(id, parseItems(items), sellerName);
    }

    private List<SaleItemDTO> parseItems(final String items) {
        final String normalizedItems = trimBraces(items);
        return Optional.of(normalizedItems)
                .filter(String::isEmpty)
                .map(s -> Collections.<SaleItemDTO>emptyList())
                .orElseGet(() -> Arrays.stream(normalizedItems.split(delimiterConfiguration.getItemsDelimiter()))
                        .map(this::parseItem)
                        .collect(Collectors.toList()));
    }

    private String trimBraces(final String items) {
        return items.substring(1, items.length() - 1);
    }

    private SaleItemDTO parseItem(final String item) {
        final String[] itemData = item.split(delimiterConfiguration.getItemsDataDelimiter());
        final int id = Integer.parseInt(itemData[0]);
        final int quantity = Integer.parseInt(itemData[1]);
        final double price = Double.parseDouble(itemData[2]);
        return new SaleItemDTO(id, quantity, price);
    }

}
