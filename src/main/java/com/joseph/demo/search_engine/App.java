package com.joseph.demo.search_engine;

import java.util.Map;

import com.joseph.demo.search_engine.indexer.Indexer;
import com.joseph.demo.search_engine.indexer.IndexerImpl1;
import com.joseph.demo.search_engine.scorer.TfScorer;
import com.joseph.demo.search_engine.scorer.TfidfSotaScorer;
import com.joseph.demo.search_engine.utils.Utils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		testIndexer();

	}

	private static void testIndexer() {
		Indexer<Long, String> indexer = new IndexerImpl1();
		TfidfSotaScorer scorer = new TfidfSotaScorer();
		scorer.setIndexer(indexer);
		scorer.setSquashingConstant(2);

		String doc1 = "titi toto tata titi";
		String doc2 = "toto tata ";
		String doc3 = "titi toto toto titi";
		String doc4 = "toto toto ";
		String doc5 = "titi tata toto toto titi";
		String doc6 = "titi tutu titi";
		Long[] ids = indexer.index(doc1, doc2, doc3, doc4, doc5, doc6);
		System.out.println("coucou");

		String query = "toto tata";

		for (Long id : ids) {
			System.out.println(scorer.apply(Utils.wordCount(query), id));
		}
	}

	private static void testScore() {
		TfScorer scorer = new TfScorer();
		String query = "toto   tata";
		String doc1 = "titi toto tata titi";
		String doc2 = "toto tata ";
		String doc3 = "titi toto toto titi";
		String doc4 = "toto toto ";
		String doc5 = "titi tata toto toto titi";
		String doc6 = "titi tutu titi";

		Map<String, Long> queryMap = Utils.wordCount2(query);
		Map<String, Long> doc1Map = Utils.wordCount2(doc1);
		Map<String, Long> doc2Map = Utils.wordCount2(doc2);
		Map<String, Long> doc3Map = Utils.wordCount2(doc3);
		Map<String, Long> doc4Map = Utils.wordCount2(doc4);
		Map<String, Long> doc5Map = Utils.wordCount2(doc5);
		Map<String, Long> doc6Map = Utils.wordCount2(doc6);

		System.out.println(doc1Map);
		System.out.println(doc2Map);
		System.out.println(doc3Map);
		System.out.println(doc4Map);
		System.out.println(doc5Map);
		System.out.println(doc6Map);

		System.out.println(scorer.apply(queryMap, doc1Map));
		System.out.println(scorer.apply(queryMap, doc2Map));
		System.out.println(scorer.apply(queryMap, doc3Map));
		System.out.println(scorer.apply(queryMap, doc4Map));
		System.out.println(scorer.apply(queryMap, doc5Map));
		System.out.println(scorer.apply(queryMap, doc6Map));
	}
}
