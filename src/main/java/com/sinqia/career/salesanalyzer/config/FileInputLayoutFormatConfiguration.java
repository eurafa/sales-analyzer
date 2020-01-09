package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileInputLayoutFormatConfiguration {

    private final String layoutFormatSeller;

    private final String layoutFormatClient;

    private final String layoutFormatSale;

    public FileInputLayoutFormatConfiguration(@Value("${file.input.layout.format.seller}") final String layoutFormatSeller,
                                              @Value("${file.input.layout.format.client}") final String layoutFormatClient,
                                              @Value("${file.input.layout.format.sale}") final String layoutFormatSale) {
        this.layoutFormatSeller = layoutFormatSeller;
        this.layoutFormatClient = layoutFormatClient;
        this.layoutFormatSale = layoutFormatSale;
    }

    public String getLayoutFormatSeller() {
        return layoutFormatSeller;
    }

    public String getLayoutFormatClient() {
        return layoutFormatClient;
    }

    public String getLayoutFormatSale() {
        return layoutFormatSale;
    }

}
