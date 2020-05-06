package com.junghuan.crawler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.junghuan.dto.Lotto649NumberRecord;

public class LotteryCrawler {

	public static void main(String[] args) {
		String tableClass = ".td_hm";
		String url = "https://www.taiwanlottery.com.tw/lotto/lotto649/history.aspx";
		Document doc = null;
		Elements tables = null;

		try {
			doc = Jsoup.connect(url).get();
			// find all table from html
			tables = doc.select("table.td_hm");

			for (Element table : tables) {
				Elements rows = table.select("tr");
				for (Element row : rows) {
					Elements tds = row.select("td > span");
					for (Element td : tds) {
						Attributes attributes = td.attributes();
						String tdId = td.id();
						String text = td.text();
						System.out.println(tdId + ", " + text);
					}
				}
			}
			
			Lotto649NumberRecord record = new Lotto649NumberRecord(null, null, url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
