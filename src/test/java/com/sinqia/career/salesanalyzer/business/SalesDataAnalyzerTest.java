package com.sinqia.career.salesanalyzer.business;

import com.sinqia.career.salesanalyzer.dto.*;
import com.sinqia.career.salesanalyzer.exception.analyze.UnknownBestSaleException;
import com.sinqia.career.salesanalyzer.exception.analyze.UnknownWorstSellerException;
import com.sinqia.career.salesanalyzer.report.dto.AnalyzeResultDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class SalesDataAnalyzerTest {

    private final SalesDataAnalyzer analyzer;

    SalesDataAnalyzerTest() {
        analyzer = new SalesDataAnalyzer();
    }

    @Test
    void testAnalyzeResultSuccess() {
        // Given
        final List<SellerDTO> sellers = Collections.singletonList(new SellerDTO("cpf", "seller", 1d));
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));
        final List<SaleItemDTO> saleItems = Collections.singletonList(new SaleItemDTO(1, 1, 1d));
        final SaleDTO sale = new SaleDTO("99", saleItems, "seller");
        final List<SaleDTO> sales = Collections.singletonList(sale);
        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(data);

        // Then
        assertThat(analyzeResult).isNotNull();
        assertThat(analyzeResult.getClientCount()).isEqualTo(clients.size());
        assertThat(analyzeResult.getSellerCount()).isEqualTo(sellers.size());
        assertThat(analyzeResult.getBestSaleId()).isEqualTo(sale.getId());
        assertThat(analyzeResult.getWorstSeller()).isEqualTo(sale.getSellerName());
    }

    @Test
    void testAnalyzeResultBestSaleFailure() {
        // Given
        final List<SellerDTO> sellers = Collections.singletonList(new SellerDTO("cpf", "seller", 1d));
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final List<SaleDTO> sales = Collections.emptyList();

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final Throwable throwable = catchThrowable(() -> analyzer.analyzeResult(data));

        // Then
        assertThat(throwable).isNotNull().isInstanceOf(UnknownBestSaleException.class).hasNoCause();
    }

    @Test
    void testAnalyzeResultBestSaleUniqueItemSuccess() {
        // Given
        final List<SellerDTO> sellers = Collections.singletonList(new SellerDTO("cpf", "seller", 1d));
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final SaleItemDTO poorSaleItem = new SaleItemDTO(1, 1, .01d);
        final SaleDTO poorSale = new SaleDTO("99", Collections.singletonList(poorSaleItem), "seller");

        final SaleItemDTO goodSaleItem = new SaleItemDTO(2, 1000, 69d);
        final SaleDTO goodSale = new SaleDTO("100", Collections.singletonList(goodSaleItem), "seller");

        final SaleItemDTO bestSaleItem = new SaleItemDTO(3, 1001, 69d);
        final SaleDTO bestSale = new SaleDTO("101", Collections.singletonList(bestSaleItem), "seller");

        final List<SaleDTO> sales = Arrays.asList(poorSale, goodSale, bestSale);

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(data);

        // Then
        assertThat(analyzeResult).isNotNull();
        assertThat(analyzeResult.getBestSaleId()).isEqualTo(bestSale.getId());
    }

    @Test
    void testAnalyzeResultBestSaleMultipleItemsSuccess() {
        // Given
        final List<SellerDTO> sellers = Collections.singletonList(new SellerDTO("cpf", "seller", 1d));
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final SaleItemDTO poorSaleItem1 = new SaleItemDTO(1, 1, 1d);
        final SaleDTO poorSale = new SaleDTO("99", Collections.singletonList(poorSaleItem1), "seller");

        final SaleItemDTO goodSaleItem1 = new SaleItemDTO(2, 1, 1d);
        final SaleItemDTO goodSaleItem2 = new SaleItemDTO(3, 2, 1d);
        final SaleDTO goodSale = new SaleDTO("100", Arrays.asList(goodSaleItem1, goodSaleItem2), "seller");

        final SaleItemDTO bestSaleItem1 = new SaleItemDTO(4, 2, 1d);
        final SaleItemDTO bestSaleItem2 = new SaleItemDTO(5, 2, 1d);
        final SaleDTO bestSale = new SaleDTO("101", Arrays.asList(bestSaleItem1, bestSaleItem2), "seller");

        final List<SaleDTO> sales = Arrays.asList(poorSale, goodSale, bestSale);

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(data);

        // Then
        assertThat(analyzeResult).isNotNull();
        assertThat(analyzeResult.getBestSaleId()).isEqualTo(bestSale.getId());
    }

    @Test
    void testAnalyzeResultWorstFailure() {
        // Given
        final List<SellerDTO> sellers = Collections.emptyList();
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final List<SaleDTO> sales = Collections.emptyList();

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final Throwable throwable = catchThrowable(() -> analyzer.analyzeResult(data));

        // Then
        assertThat(throwable).isNotNull().isInstanceOf(UnknownWorstSellerException.class).hasNoCause();
    }

    @Test
    void testAnalyzeResultWorstSellerSuccess() {
        // Given
        final SellerDTO seller = new SellerDTO("cpf", "seller", 1d);
        final SellerDTO worst = new SellerDTO("cpf-2", "worst", 1d);
        final List<SellerDTO> sellers = Arrays.asList(seller, worst);
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final SaleDTO sale = new SaleDTO("99", Collections.singletonList(new SaleItemDTO(1, 2, .01d)), "seller");
        final SaleDTO worstSale = new SaleDTO("100", Collections.singletonList(new SaleItemDTO(1, 1, .01d)), "worst");
        final List<SaleDTO> sales = Arrays.asList(sale, worstSale);

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(data);

        // Then
        assertThat(analyzeResult).isNotNull();
        assertThat(analyzeResult.getWorstSeller()).isEqualTo(worst.getName());
    }

    @Test
    void testAnalyzeResultWorstSellerWithoutAnySaleSuccess() {
        // Given
        final SellerDTO seller = new SellerDTO("cpf", "seller", 1d);
        final SellerDTO worst = new SellerDTO("cpf-2", "worst", 1d);
        final List<SellerDTO> sellers = Arrays.asList(seller, worst);
        final List<ClientDTO> clients = Collections.singletonList(new ClientDTO("cnpj", "client", "businessArea"));

        final SaleDTO sale = new SaleDTO("99", Collections.singletonList(new SaleItemDTO(1, 2, .01d)), "seller");
        final List<SaleDTO> sales = Collections.singletonList(sale);

        final SalesDataDTO data = new SalesDataDTO(sellers, clients, sales);

        // When
        final AnalyzeResultDTO analyzeResult = analyzer.analyzeResult(data);

        // Then
        assertThat(analyzeResult).isNotNull();
        assertThat(analyzeResult.getWorstSeller()).isEqualTo(worst.getName());
    }

}