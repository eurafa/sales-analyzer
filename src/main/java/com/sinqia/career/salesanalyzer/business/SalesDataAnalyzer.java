package com.sinqia.career.salesanalyzer.business;

import com.sinqia.career.salesanalyzer.dto.ReportDataDTO;
import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SaleItemDTO;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SalesDataAnalyzer {

    public ReportDataDTO analyze(final SalesDataDTO salesData) {
        final int clientCount = salesData.getClients().size();
        final int sellerCount = salesData.getSellers().size();
        final String bestSaleId = collectBestSaleId(salesData.getSales());
        final String worstSellerName = collectWorstSellerName(salesData.getSales());
        return new ReportDataDTO(clientCount, sellerCount, bestSaleId, worstSellerName);
    }

    private String collectBestSaleId(final List<SaleDTO> sales) {
        final Map<String, Double> salesTotalMap = sales.stream().collect(Collectors.toMap(SaleDTO::getId, this::calculateSaleTotal));
        return salesTotalMap.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException());
    }

    private String collectWorstSellerName(final List<SaleDTO> sales) {
        final Map<String, Double> salesTotalBySeller = sales.stream()
                .collect(Collectors.groupingBy(SaleDTO::getSellerName, Collectors.summingDouble(this::calculateSaleTotal)));
        return salesTotalBySeller.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException());
    }

    private double calculateSaleItem(final SaleItemDTO saleItem) {
        return saleItem.getPrice() * saleItem.getQuantity();
    }

    private double calculateSaleTotal(final SaleDTO sale) {
        return sale.getItems().stream().mapToDouble(this::calculateSaleItem).sum();
    }

}
