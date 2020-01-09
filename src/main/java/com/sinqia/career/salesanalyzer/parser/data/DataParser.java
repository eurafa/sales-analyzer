package com.sinqia.career.salesanalyzer.parser.data;

import java.util.List;

public interface DataParser<T> {

    List<T> parse(List<String> data);

}
