package com.junghuan.dto;

import lombok.Getter;
import lombok.Setter;

public class DailyInfectedPopulationRecord {
	private String countryOther;
	private String totalCases;
	private String newCases;
	private String totalDeaths;
	private String newDeaths;
	private String totalRecovered;
	private String activeCases;
	private String seriousCritical;
	private String totalCases_1Mpop;
	private String deaths_1Mpop;
	private String totalTests;
	private String tests_1Mpop;
	
	public String getCountryOther() {
		return countryOther;
	}
	public void setCountryOther(String countryOther) {
		this.countryOther = countryOther;
	}
	public String getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(String totalCases) {
		this.totalCases = totalCases;
	}
	public String getNewCases() {
		return newCases;
	}
	public void setNewCases(String newCases) {
		this.newCases = newCases;
	}
	public String getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(String totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public String getNewDeaths() {
		return newDeaths;
	}
	public void setNewDeaths(String newDeaths) {
		this.newDeaths = newDeaths;
	}
	public String getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(String totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public String getActiveCases() {
		return activeCases;
	}
	public void setActiveCases(String activeCases) {
		this.activeCases = activeCases;
	}
	public String getSeriousCritical() {
		return seriousCritical;
	}
	public void setSeriousCritical(String seriousCritical) {
		this.seriousCritical = seriousCritical;
	}
	public String getTotalCases_1Mpop() {
		return totalCases_1Mpop;
	}
	public void setTotalCases_1Mpop(String totalCases_1Mpop) {
		this.totalCases_1Mpop = totalCases_1Mpop;
	}
	public String getDeaths_1Mpop() {
		return deaths_1Mpop;
	}
	public void setDeaths_1Mpop(String deaths_1Mpop) {
		this.deaths_1Mpop = deaths_1Mpop;
	}
	public String getTotalTests() {
		return totalTests;
	}
	public void setTotalTests(String totalTests) {
		this.totalTests = totalTests;
	}
	public String getTests_1Mpop() {
		return tests_1Mpop;
	}
	public void setTests_1Mpop(String tests_1Mpop) {
		this.tests_1Mpop = tests_1Mpop;
	}
}
