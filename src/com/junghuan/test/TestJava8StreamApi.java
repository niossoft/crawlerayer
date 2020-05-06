package com.junghuan.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJava8StreamApi {

	 public static List<String> langNamelist = Arrays.asList("Java","JavaScript","Python","Ruby");
	
	 public static void main(String[] args) {
		 testStreamNonMatchMethod();
	 }
	 
	 public static void testStreamNonMatchMethod() {
		 List<String> matchList = new ArrayList<>();
		 matchList.add("a");
		 matchList.add("");
		 matchList.add("a");
		 matchList.add("c");
		 matchList.add("d");
		 boolean isNotEmpty = matchList.stream().noneMatch(s -> s.isEmpty());
		 
		 System.out.println(isNotEmpty);
	 }
}
