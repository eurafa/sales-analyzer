package com.sinqia.career.salesanalyzer.dto;

import java.util.List;

public class SalesDataDTO {

    private final List<SellerDTO> sellers;

    private final List<ClientDTO> clients;

    private final List<SaleDTO> sales;

    public SalesDataDTO(final List<SellerDTO> sellers, final List<ClientDTO> clients, final List<SaleDTO> sales) {
        this.sellers = sellers;
        this.clients = clients;
        this.sales = sales;
    }

    public List<SellerDTO> getSellers() {
        return sellers;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public List<SaleDTO> getSales() {
        return sales;
    }

}
