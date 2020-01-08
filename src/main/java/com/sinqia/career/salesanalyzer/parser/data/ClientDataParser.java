package com.sinqia.career.salesanalyzer.parser.data;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
import com.sinqia.career.salesanalyzer.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientDataParser implements DataParser<ClientDTO> {

    private final FileInputDelimiterConfiguration delimiterConfiguration;

    public ClientDataParser(final FileInputDelimiterConfiguration delimiterConfiguration) {
        this.delimiterConfiguration = delimiterConfiguration;
    }

    @Override
    public List<ClientDTO> parse(final List<String> content) {
        return Optional.ofNullable(content).map(c -> c.stream().map(this::parseLine).collect(Collectors.toList())).orElseGet(Collections::emptyList);
    }

    private ClientDTO parseLine(final String line) {
        final String[] split = line.split(delimiterConfiguration.getDataDelimiter());

        final String layoutFormatId = split[0];
        final String cnpj = split[1];
        final String businessArea = split[split.length - 1];

        final String name = split.length == 4 ? split[2] : line.substring(layoutFormatId.length() + 1 + cnpj.length() + 1, line.length() - businessArea.length() - 1);

        return new ClientDTO(cnpj, name, businessArea);
    }

}
