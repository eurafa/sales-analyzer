package com.sinqia.career.salesanalyzer.report.dto;

public class AnalyzeResultDTO {

    private final int clientCount;

    private final int sellerCount;

    private final String bestSaleId;

    private final String worstSeller;

    public AnalyzeResultDTO(final int clientCount, final int sellerCount, final String bestSaleId, final String worstSeller) {
        this.clientCount = clientCount;
        this.sellerCount = sellerCount;
        this.bestSaleId = bestSaleId;
        this.worstSeller = worstSeller;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getSellerCount() {
        return sellerCount;
    }

    public String getBestSaleId() {
        return bestSaleId;
    }

    public String getWorstSeller() {
        return worstSeller;
    }

}
