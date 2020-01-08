package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.ClientDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClientDataParserTest {

    private static final String FORMAT_ID = "002";

    private final ClientDataParser parser;

    public ClientDataParserTest() {
        parser = new ClientDataParser();
    }

    @Test
    void parseSuccess() {
        // Given
        final String joseCnpj = "2345675434544345";
        final String joseName = "Jose da Silva";
        final String joseBusinessArea = "Rural";
        final ClientDTO jose = new ClientDTO(joseCnpj, joseName, joseBusinessArea);

        final String eduardoCnpj = "2345675433444345";
        final String eduardoName = "Eduardo Pereira";
        final String eduardoBusinessArea = "Rural";
        final ClientDTO eduardo = new ClientDTO(eduardoCnpj, eduardoName, eduardoBusinessArea);

        final String delimiter = "ç";
        final List<String> content = Arrays.asList(
                String.join(delimiter, FORMAT_ID, joseCnpj, joseName, joseBusinessArea),
                String.join(delimiter, FORMAT_ID, eduardoCnpj, eduardoName, eduardoBusinessArea));

        // When
        final List<ClientDTO> clients = parser.parse(content);

        // Then
        assertThat(clients).isNotNull().hasSize(content.size());

        clients.sort(Comparator.comparing(ClientDTO::getName));
        assertThat(clients.get(0)).isNotNull().isEqualToComparingFieldByField(eduardo);
        assertThat(clients.get(1)).isNotNull().isEqualToComparingFieldByField(jose);
    }

}