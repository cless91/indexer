package com.joseph.demo.search_engine.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {
	public static Map<String, Long> wordCount(String input) {
		return Arrays.stream(input.trim().split("\\s+")).sorted().collect(Collectors.toMap(w -> w, w -> 1l, Long::sum));

	}

	public static Map<String, Long> wordCount2(String input) {
		return Arrays.stream(input.trim().split("\\s+")).sorted()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	}
}
