package com.sinqia.career.salesanalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileInputDelimiterConfiguration {

    private final String dataDelimiter;

    private final String itemsDelimiter;

    private final String itemsDataDelimiter;

    public FileInputDelimiterConfiguration(@Value("${file.input.delimiter.data}") final String dataDelimiter,
                                           @Value("${file.input.delimiter.items}") final String itemsDelimiter,
                                           @Value("${file.input.delimiter.itemData}") final String itemsDataDelimiter) {
        this.dataDelimiter = dataDelimiter;
        this.itemsDelimiter = itemsDelimiter;
        this.itemsDataDelimiter = itemsDataDelimiter;
    }

    public String getDataDelimiter() {
        return dataDelimiter;
    }

    public String getItemsDelimiter() {
        return itemsDelimiter;
    }

    public String getItemsDataDelimiter() {
        return itemsDataDelimiter;
    }

}
