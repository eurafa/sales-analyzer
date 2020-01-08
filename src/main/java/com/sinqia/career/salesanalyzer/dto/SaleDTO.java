package com.sinqia.career.salesanalyzer.dto;

import java.util.List;

public class SaleDTO {

    private final String id;

    private final List<SaleItemDTO> items;

    private final String sellerName;

    public SaleDTO(final String id, final List<SaleItemDTO> items, final String sellerName) {
        this.id = id;
        this.items = items;
        this.sellerName = sellerName;
    }

    public String getId() {
        return id;
    }

    public List<SaleItemDTO> getItems() {
        return items;
    }

    public String getSellerName() {
        return sellerName;
    }

}
