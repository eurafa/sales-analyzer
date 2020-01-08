package com.sinqia.career.salesanalyzer.parser;

import com.sinqia.career.salesanalyzer.dto.SalesDataDTO;

import java.util.List;

public interface SalesAnalyzerParser {

    SalesDataDTO parse(List<String> data);

}
