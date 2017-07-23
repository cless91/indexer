package com.joseph.demo.search_engine.indexer;

public interface Indexer<TId, TDocument> {
	public Long getCorpusCount();

	public Double getDocumentAverageLength();

	public Double getDocumentFrequency(String word);

	/**
	 * Create de CRUD
	 * 
	 * @param document
	 */
	public TId index(String document);

	public TId[] index(String... documents);

	/**
	 * R (par id) de CRUD
	 * 
	 * @param id
	 */
	public TDocument get(TId id);

	/**
	 * U (par id) de CRUD
	 * 
	 * @param id
	 * @param documentUpdate
	 */
	public void update(TId id, TDocument documentUpdate);

	/**
	 * D (par id) de CRUD
	 * 
	 * @param id
	 */
	public void delete(TId id);
}
