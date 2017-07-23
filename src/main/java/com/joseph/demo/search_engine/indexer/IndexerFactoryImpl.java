package com.joseph.demo.search_engine.indexer;

public class IndexerFactoryImpl implements IndexerFactory<Long, String> {
	private static IndexerFactoryImpl instance = new IndexerFactoryImpl();

	private IndexerFactoryImpl() {
	}

	@Override
	public Indexer<Long, String> getIndexer() {
		return new IndexerImpl1();
	}

	public static IndexerFactory<Long, String> getFactory() {
		return instance;
	}
}
