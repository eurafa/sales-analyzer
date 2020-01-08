package com.sinqia.career.salesanalyzer.dto;

public class ClientDTO {

    private final String cnpj;

    private final String name;

    private final String businessArea;

    public ClientDTO(final String cnpj, final String name, final String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

}
