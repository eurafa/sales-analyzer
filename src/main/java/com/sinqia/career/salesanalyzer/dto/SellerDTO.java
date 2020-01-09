package com.sinqia.career.salesanalyzer.dto;

public class SellerDTO {

    private final String cpf;

    private final String name;

    private final double salary;

    public SellerDTO(final String cpf, final String name, final double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
