package com.sinqia.career.salesanalyzer.dto;

public class SaleItemDTO {

    private final int id;

    private final int quantity;

    private final double price;

    public SaleItemDTO(final int id, final int quantity, final double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
