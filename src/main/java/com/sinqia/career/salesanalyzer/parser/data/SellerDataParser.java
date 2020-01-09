package com.sinqia.career.salesanalyzer.parser.data;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.dto.SellerDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SellerDataParser implements DataParser<SellerDTO> {

    private final FileInputDelimiterConfiguration delimiterConfiguration;

    public SellerDataParser(final FileInputDelimiterConfiguration delimiterConfiguration) {
        this.delimiterConfiguration = delimiterConfiguration;
    }

    @Override
    public List<SellerDTO> parse(final List<String> content) {
        return Optional.ofNullable(content).map(c -> c.stream().map(this::parseLine).collect(Collectors.toList())).orElseGet(Collections::emptyList);
    }

    private SellerDTO parseLine(final String line) {
        final String[] split = line.split(delimiterConfiguration.getDataDelimiter());

        final String layoutFormatId = split[0];
        final String cpf = split[1];
        final String salaryAsString = split[split.length - 1];
        final double salary = Double.parseDouble(salaryAsString);

        final String name = split.length == 4 ? split[2] : line.substring(layoutFormatId.length() + 1 + cpf.length() + 1, line.length() - salaryAsString.length() - 1);

        return new SellerDTO(cpf, name, salary);
    }

}
