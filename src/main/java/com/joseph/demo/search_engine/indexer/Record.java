package com.joseph.demo.search_engine.indexer;

import java.util.Map;

public class Record {
	private String rawContent;
	private Map<String, Long> wordCount;

	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

	public Map<String, Long> getWordCount() {
		return wordCount;
	}

	public void setWordCount(Map<String, Long> wordCount) {
		this.wordCount = wordCount;
	}

}
