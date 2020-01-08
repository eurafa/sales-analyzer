package com.sinqia.career.salesanalyzer.config;

import com.sinqia.career.salesanalyzer.input.SalesAnalyzerInput;
import com.sinqia.career.salesanalyzer.input.file.SalesAnalyzerFileInput;
import com.sinqia.career.salesanalyzer.parser.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesAnalyzerInputConfiguration {

    @Bean
    public SalesAnalyzerInput inputConfig(final SalesAnalyzerDirectoryConfiguration directoryConfiguration) {
        return new SalesAnalyzerFileInput(directoryConfiguration);
    }

    @Bean
    public SalesAnalyzerParser parserConfig(final SellerDataParser sellerParser, final ClientDataParser clientParser, final SaleDataParser saleParser) {
        return new DefaultSalesAnalyzerParser(sellerParser, clientParser, saleParser);
    }

}
