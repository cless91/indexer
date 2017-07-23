package com.joseph.demo.search_engine.scorer;

import java.util.function.BiFunction;

public interface Scorer<TQuery, TDocument> extends BiFunction<TQuery, TDocument, Double> {
}
