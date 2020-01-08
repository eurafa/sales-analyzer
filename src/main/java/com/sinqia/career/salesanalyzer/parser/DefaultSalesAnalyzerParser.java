package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.ClientDTO;
import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.dto.SellerDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultSalesAnalyzerParser implements SalesAnalyzerParser {

    private final DataParser<SellerDTO> sellerParser;

    private final DataParser<ClientDTO> clientParser;

    private final DataParser<SaleDTO> saleParser;

    public DefaultSalesAnalyzerParser(final SellerDataParser sellerParser, final ClientDataParser clientParser, final SaleDataParser saleParser) {
        this.sellerParser = sellerParser;
        this.clientParser = clientParser;
        this.saleParser = saleParser;
    }

    @Override
    public SalesDataDTO parse(final List<String> content) {
        final String LINE_FORMAT_SELLER = "001"; //FIXME
        final String LINE_FORMAT_CLIENT = "002";
        final String LINE_FORMAT_SALE = "003";

        final Map<String, List<String>> linesByFormatId = content.stream().collect(Collectors.groupingBy(s -> s.substring(0, 3)));

        final List<String> sellers = linesByFormatId.get(LINE_FORMAT_SELLER);
        final List<String> clients = linesByFormatId.get(LINE_FORMAT_CLIENT);
        final List<String> sales = linesByFormatId.get(LINE_FORMAT_SALE);

        return new SalesDataDTO(parseSellers(sellers), parseClients(clients), parseSales(sales));
    }

    private List<SellerDTO> parseSellers(final List<String> sellers) {
        return sellerParser.parse(sellers);
    }

    private List<ClientDTO> parseClients(final List<String> clients) {
        return clientParser.parse(clients);
    }

    private List<SaleDTO> parseSales(final List<String> sales) {
        return saleParser.parse(sales);
    }

}
