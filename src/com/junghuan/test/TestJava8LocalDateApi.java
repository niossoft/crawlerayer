package com.junghuan.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestJava8LocalDateApi {
	
	public static void main(String[] args) {
		testLocalDate();
	 }
	
	public static void testLocalDate() {
	
	LocalDate d = LocalDate.now(); // 取得今日
	System.out.println("today: " + d.toString());      // today
	System.out.println("year: "  + d.getYear());       // year
	System.out.println("month: " + d.getMonthValue()); // month
	System.out.println("date: "  + d.getDayOfMonth()); // date

	System.out.println("day-of-year: "  + d.getDayOfYear());           
	System.out.println("day-of-month: " + d.getDayOfMonth());           
	System.out.println("day-of-week: "  + d.getDayOfWeek().name());    
	System.out.println("day-of-week: "  + d.getDayOfWeek().getValue()); 

	System.out.println("day-of-1day-ago: "  + d.minusDays(1L));   
	System.out.println("day-of-1week-ago: "  + d.minusWeeks(1L));  
	System.out.println("day-of-1month-ago: "  + d.minusMonths(1L)); 
	System.out.println("day-of-1year-ago: "  + d.minusYears(1L));  

	System.out.println("day-of-1day-after: " + d.plusDays(1L));    
	System.out.println("day-of-1week-after: " + d.plusWeeks(1L));   
	System.out.println("day-of-1month-after: " + d.plusMonths(1L));  
	System.out.println("day-of-1year-after: " + d.plusYears(1L));   

	// parse 字串為LocalDate
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
	    System.out.println(LocalDate.parse("20150303", format)); // 2015-03-03
	}
}
