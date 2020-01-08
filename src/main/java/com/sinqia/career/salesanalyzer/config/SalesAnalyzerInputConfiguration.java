package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.io.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.io.input.file.SalesAnalyzerFileInput;
import com.sinqia.career.salesanalyzer.parser.DefaultSalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.parser.SalesAnalyzerParser;
import com.sinqia.career.salesanalyzer.parser.data.ClientDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SaleDataParser;
import com.sinqia.career.salesanalyzer.parser.data.SellerDataParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerInputConfiguration {

    @Bean
    public SalesAnalyzerInput inputConfig(final DirectoryConfiguration directoryConfiguration) {
        return new SalesAnalyzerFileInput(directoryConfiguration);
    }

    @Bean
    public SalesAnalyzerParser parserConfig(final FileInputLayoutFormatConfiguration layoutFormatConfiguration, final SellerDataParser sellerParser, final ClientDataParser clientParser, final SaleDataParser saleParser) {
        return new DefaultSalesAnalyzerParser(layoutFormatConfiguration, sellerParser, clientParser, saleParser);
    }

}
