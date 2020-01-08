package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SaleItemDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleDataParser implements DataParser<SaleDTO> {

    @Override
    public List<SaleDTO> parse(final List<String> content) {
        return content.stream().map(this::parseLine).collect(Collectors.toList());
    }

    private SaleDTO parseLine(final String line) {
        final String[] split = line.split("ç");
        if (split.length == 4) {
            final String id = split[1];
            final String items = split[2];
            final String sellerName = split[3];
            return new SaleDTO(id, parseItems(items), sellerName);
        } else {
            return null;
        }
    }

    private List<SaleItemDTO> parseItems(final String items) {
        return Arrays.stream(trimBraces(items).split(","))
                .map(this::parseItem)
                .collect(Collectors.toList());
    }

    private String trimBraces(final String items) {
        return items.substring(1, items.length() - 1);
    }

    private SaleItemDTO parseItem(final String item) {
        final String[] itemData = item.split("-");
        final int id = Integer.parseInt(itemData[0]);
        final int quantity = Integer.parseInt(itemData[1]);
        final double price = Double.parseDouble(itemData[2]);
        return new SaleItemDTO(id, quantity, price);
    }

}
