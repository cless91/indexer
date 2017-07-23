package com.joseph.demo.search_engine.scorer;

import java.util.Map;
import java.util.Map.Entry;

public class TfScorer implements Scorer<Map<String, Long>, Map<String, Long>> {

	@Override
	public Double apply(Map<String, Long> query, Map<String, Long> document) {
		double score = 0;
		for (Entry<String, Long> entry : query.entrySet()) {
			if (document.containsKey(entry.getKey()))
				score += entry.getValue() * document.get(entry.getKey());
		}
		return score;
	}

}
