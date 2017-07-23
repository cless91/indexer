package com.joseph.demo.search_engine.indexer;

public interface IndexerFactory<TId, TDocument> {
	public Indexer<TId, TDocument> getIndexer();
}
