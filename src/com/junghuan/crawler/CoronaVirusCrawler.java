package com.junghuan.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.junghuan.dto.DailyInfectedPopulationRecord;
import com.junghuan.util.CreateNewFileUtil;
import com.junghuan.util.HtmlUtil;

public class CoronaVirusCrawler {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String url = "https://www.worldometers.info/coronavirus/#countries";
		Element table = null;
		Document doc = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			doc = Jsoup.connect(url).get();
			// 
			table = doc.select("table[id=main_table_countries_today]").first();
			Elements rows = table.select("tr");
			Elements ths = rows.select("th");

			DailyInfectedPopulationRecord title = new DailyInfectedPopulationRecord();
			Map<String, DailyInfectedPopulationRecord> MapByCountryName = new HashMap<String, DailyInfectedPopulationRecord>();
			// Table th value
			for (Element th : ths) {
				System.out.println(" ColumnName: " + HtmlUtil.html2text(th.text()));
			}

			// 
			for (Element row : rows) {
				Elements tds = row.select("td");
				if (tds.isEmpty()) {
					continue;
				}

				DailyInfectedPopulationRecord node = new DailyInfectedPopulationRecord();

				String countryOther = tds.get(0).text();
				String totalCases = tds.get(1).text();
				String newCases = tds.get(2).text();
				String totalDeaths = tds.get(3).text();
				String newDeaths = tds.get(4).text();
				String totalRecovered = tds.get(5).text();
				String activeCases = tds.get(6).text();
				String seriousCritical = tds.get(7).text();
				String totalCases_1Mpop = tds.get(8).text();
				String deaths_1Mpop = tds.get(9).text();
				String totalTests = tds.get(10).text();
				String tests_1Mpop = tds.get(11).text();

				node.setCountryOther(countryOther);
				node.setTotalCases(totalCases);
				node.setNewCases(newCases);
				node.setTotalDeaths(totalDeaths);
				node.setNewDeaths(newDeaths);
				node.setTotalRecovered(totalRecovered);
				node.setActiveCases(activeCases);
				node.setSeriousCritical(seriousCritical);
				node.setTotalCases_1Mpop(totalCases_1Mpop);
				node.setDeaths_1Mpop(deaths_1Mpop);
				node.setTotalTests(totalTests);
				node.setTests_1Mpop(tests_1Mpop);

				MapByCountryName.put(countryOther, node);
			}
			System.out.println("number of country: " + MapByCountryName.size());

			Gson gsone = new Gson();
			// 1. sorting by Record.TotalCase
			// 2. turn into JsonObject 
			JsonObject result = gsone.toJsonTree(sortRecordMapByValue(MapByCountryName)).getAsJsonObject();

			// 3. 
			ObjectMapper mapper = new ObjectMapper();
			Object jsonObject = mapper.readValue(result.toString(), Object.class);
			String prettyFormatted = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

			// 日期字串
			String dateStr = new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime());
			/**
			String configPath = "config/ECouponImportFileToSN.properties";
			Properties ps = PropertiesLoaderUtils.loadProperties(resource);
			
			headerFormat = ps.getProperty("data.header.format");
			*/
			
			String filePath = "C:\\temp";
			String fileName = "DailyRecord_" + dateStr +".json";
			
			CreateNewFileUtil.createFileUsingFileClass(filePath, fileName, prettyFormatted);
			
			/**
			try (FileWriter file = new FileWriter("c:\\projects\\test.json")) {
				file.write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// 排序
	public static Map<String, DailyInfectedPopulationRecord> sortRecordMapByValue(final Map<String, DailyInfectedPopulationRecord> records) {
        return records.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().getTotalCases().compareTo(e1.getValue().getTotalCases()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
