package com.sinqia.career.salesanalyzer.business;

import com.sinqia.career.salesanalyzer.dto.SaleDTO;
import com.sinqia.career.salesanalyzer.dto.SaleItemDTO;
import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;
import com.sinqia.career.salesanalyzer.dto.SellerDTO;
import com.sinqia.career.salesanalyzer.exception.analyze.UnknownBestSaleException;
import com.sinqia.career.salesanalyzer.exception.analyze.UnknownWorstSellerException;
import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SalesDataAnalyzerBusiness {

    public AnalyzeResultDTO analyzeResult(final SalesDataDTO salesData) {
        final int clientCount = salesData.getClients().size();
        final int sellerCount = salesData.getSellers().size();
        final String worstSellerName = collectWorstSellerName(salesData.getSales(), salesData.getSellers());
        final String bestSaleId = collectBestSaleId(salesData.getSales());
        return new AnalyzeResultDTO(clientCount, sellerCount, bestSaleId, worstSellerName);
    }

    private String collectBestSaleId(final List<SaleDTO> sales) {
        final Map<String, Double> salesTotalMap = sales.stream().collect(Collectors.toMap(SaleDTO::getId, this::calculateSaleTotal));
        return salesTotalMap.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(UnknownBestSaleException::new);
    }

    private String collectWorstSellerName(final List<SaleDTO> sales, final List<SellerDTO> sellers) {
        final Set<String> sellersWithSale = sales.stream().map(SaleDTO::getSellerName).collect(Collectors.toSet());
        return sellers.stream()
                .filter(seller -> !sellersWithSale.contains(seller.getName()))
                .map(SellerDTO::getName)
                .findFirst()
                .orElseGet(() -> collectWorstSellerName(sales));
    }

    private String collectWorstSellerName(final List<SaleDTO> sales) {
        final Map<String, Double> salesTotalBySeller = sales.stream()
                .collect(Collectors.groupingBy(SaleDTO::getSellerName, Collectors.summingDouble(this::calculateSaleTotal)));
        return salesTotalBySeller.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(UnknownWorstSellerException::new);
    }

    private double calculateSaleItem(final SaleItemDTO saleItem) {
        return saleItem.getPrice() * saleItem.getQuantity();
    }

    private double calculateSaleTotal(final SaleDTO sale) {
        return sale.getItems().stream().mapToDouble(this::calculateSaleItem).sum();
    }

}
