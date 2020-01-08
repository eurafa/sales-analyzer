package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientDataParser implements DataParser<ClientDTO> {

    @Override
    public List<ClientDTO> parse(final List<String> content) {
        return content.stream().map(this::parseLine).collect(Collectors.toList());
    }

    private ClientDTO parseLine(final String line) {
        final String[] split = line.split("ç");
        if (split.length == 4) {
            final String cnpj = split[1];
            final String name = split[2];
            final String businessArea = split[3];
            return new ClientDTO(cnpj, name, businessArea);
        } else {
            return null;
        }
    }

}
