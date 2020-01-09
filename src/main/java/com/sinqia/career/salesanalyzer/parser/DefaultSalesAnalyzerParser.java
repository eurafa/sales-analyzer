package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.config.FileInputDelimiterConfiguration;
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

    private final FileInputDelimiterConfiguration delimiterConfiguration;

    private final SellerDataParser sellerParser;

    private final ClientDataParser clientParser;

    private final SaleDataParser saleParser;

    public DefaultSalesAnalyzerParser(final FileInputLayoutFormatConfiguration layoutFormatConfiguration,
                                      final FileInputDelimiterConfiguration delimiterConfiguration,
                                      final SellerDataParser sellerParser,
                                      final ClientDataParser clientParser,
                                      final SaleDataParser saleParser) {
        this.layoutFormatConfiguration = layoutFormatConfiguration;
        this.delimiterConfiguration = delimiterConfiguration;
        this.sellerParser = sellerParser;
        this.clientParser = clientParser;
        this.saleParser = saleParser;
    }

    @Override
    public SalesDataDTO parse(final List<String> content) {
        final Map<String, List<String>> linesByLayoutFormat = content.stream().collect(Collectors.groupingBy(s -> s.substring(0, s.indexOf(delimiterConfiguration.getDataDelimiter()))));

        final List<String> sellers = linesByLayoutFormat.get(layoutFormatConfiguration.getLayoutFormatSeller());
        final List<String> clients = linesByLayoutFormat.get(layoutFormatConfiguration.getLayoutFormatClient());
        final List<String> sales = linesByLayoutFormat.get(layoutFormatConfiguration.getLayoutFormatSale());

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
