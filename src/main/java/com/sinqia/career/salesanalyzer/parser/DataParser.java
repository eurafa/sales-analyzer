package com.sinqia.career.salesanalyzer.parser;

import java.util.List;

public interface DataParser<T> {

    List<T> parse(List<String> data);

}
