package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.config.FileInputLayoutFormatConfiguration;
import com.sinqia.career.salesanalyzer.dto.ClientDTO;
import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.dto.SellerDTO;
import com.sinqia.career.salesanalyzer.parser.data.ClientDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SaleDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SellerDataParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultSalesAnalyzerParser implements SalesAnalyzerParser {

    private final FileInputLayoutFormatConfiguration layoutFormatConfiguration;

    private final SellerDataParser sellerParser;

    private final ClientDataParser clientParser;

    private final SaleDataParser saleParser;

    public DefaultSalesAnalyzerParser(final FileInputLayoutFormatConfiguration layoutFormatConfiguration,
                                      final SellerDataParser sellerParser,
                                      final ClientDataParser clientParser,
                                      final SaleDataParser saleParser) {
        this.layoutFormatConfiguration = layoutFormatConfiguration;
        this.sellerParser = sellerParser;
        this.clientParser = clientParser;
        this.saleParser = saleParser;
    }

    @Override
    public SalesDataDTO parse(final List<String> content) {
        final Map<String, List<String>> linesByFormatId = content.stream().collect(Collectors.groupingBy(s -> s.substring(0, 3)));

        final List<String> sellers = linesByFormatId.get(layoutFormatConfiguration.getLayoutFormatSeller());
        final List<String> clients = linesByFormatId.get(layoutFormatConfiguration.getLayoutFormatClient());
        final List<String> sales = linesByFormatId.get(layoutFormatConfiguration.getLayoutFormatSale());

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
