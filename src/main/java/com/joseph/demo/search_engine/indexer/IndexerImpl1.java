package com.joseph.demo.search_engine.indexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.joseph.demo.search_engine.utils.Utils;

public class IndexerImpl1 implements Indexer<Long, String> {
	private Map<Long, Record> documentsCollection = new HashMap<>();
	private Map<String, Long> totalWordCount = new HashMap<>();
	private Map<String, Collection<Long>> indexByWord = new HashMap<>();
	private long documentIdCount = 0;

	@Override
	public Long getCorpusCount() {
		return (long) documentsCollection.size();
	}

	@Override
	public Double getDocumentAverageLength() {
		double sum = 0.0;

		for (Record record : documentsCollection.values()) {
			for (Long count : record.getWordCount().values()) {
				sum += count;
			}
		}

		return sum / this.getCorpusCount();
	}

	@Override
	public Double getDocumentFrequency(String word) {
		return (double) ((indexByWord.containsKey(word)) ? indexByWord.get(word).size() : 0)
				/ documentsCollection.size();
	}

	@Override
	public Long index(String document) {
		Record newRecord = new Record();
		newRecord.setRawContent(document);
		newRecord.setWordCount(Utils.wordCount(document));

		long newId = this.documentIdCount++;

		documentsCollection.put(newId, newRecord);

		for (Entry<String, Long> entry : newRecord.getWordCount().entrySet()) {
			String word = entry.getKey();
			Long count = entry.getValue();
			totalWordCount.put(word, (totalWordCount.containsKey(word)) ? totalWordCount.get(word) : 0l + count);

			if (indexByWord.containsKey(word)) {
			} else {
				indexByWord.put(word, new ArrayList<>());
			}
			indexByWord.get(word).add(newId);
		}

		return newId;
	}

	@Override
	public String get(Long id) {
		return documentsCollection.get(id).getRawContent();
	}

	@Override
	public void update(Long id, String documentUpdate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long[] index(String... documents) {
		Long[] newIds = new Long[documents.length];
		for (int i = 0; i < documents.length; i++) {
			newIds[i] = this.index(documents[i]);
		}
		return newIds;
	}

}
