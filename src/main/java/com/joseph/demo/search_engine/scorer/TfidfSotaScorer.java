package com.joseph.demo.search_engine.scorer;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

import com.joseph.demo.search_engine.indexer.Indexer;
import com.joseph.demo.search_engine.utils.Utils;

public class TfidfSotaScorer implements Scorer<Map<String, Long>, Long> {
	private Indexer<Long, String> indexer;
	private double squashingConstant = 1;

	@Override
	public Double apply(Map<String, Long> query, Long documentId) {
		double score = 0;
		String documentString = indexer.get(documentId);
		Map<String, Long> documentMap = Utils.wordCount(documentString);
		for (Entry<String, Long> entry : query.entrySet()) {
			String word = entry.getKey();
			long tfwq = entry.getValue();
			if (documentMap.containsKey(word)) {
				long tfwd = (documentMap.containsKey(word)) ? documentMap.get(word) : 0;
				long documentLength = documentMap.values().stream().reduce(new BinaryOperator<Long>() {

					@Override
					public Long apply(Long t, Long u) {
						// TODO Auto-generated method stub
						return t + u;
					}
				}).get();
				double squashingFactor = squashingConstant * documentLength / indexer.getDocumentAverageLength();
				double weightedTf = tfwd / (tfwd + squashingFactor);

				double dfwc = indexer.getDocumentFrequency(word);
				double weightedIdf = Math.log(indexer.getCorpusCount() / dfwc);

				score += tfwq * weightedTf * weightedIdf;
			}
		}
		return score;
	}

	public void setIndexer(Indexer<Long, String> indexer) {
		this.indexer = indexer;
	}

	public void setSquashingConstant(double squashingConstant) {
		this.squashingConstant = squashingConstant;
	}

}
