package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.SellerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerDataParser implements DataParser<SellerDTO> {

    @Override
    public List<SellerDTO> parse(final List<String> content) {
        return content.stream().map(this::parseLine).collect(Collectors.toList());
    }

    private SellerDTO parseLine(final String line) {
        final String[] split = line.split("ç");
        if (split.length == 4) {
            final String cpf = split[1];
            final String name = split[2];
            final double salary = Double.parseDouble(split[3]);
            return new SellerDTO(cpf, name, salary);
        } else {
            return null;
        }
    }

}
