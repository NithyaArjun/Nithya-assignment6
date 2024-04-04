package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class SalesReport {

	public static void main(String[] args) throws IOException {
		List<TeslaService> model3 = readReport("model3.csv");
		List<TeslaService> models = readReport("modelS.csv");
		List<TeslaService> modelx = readReport("modelX.csv");
		generateReport("Model 3", model3);
		maxMonthReport("Model 3", model3);
		minMonthReport("Model 3", model3);
		generateReport("Model S", models);
		maxMonthReport("Model S", models);
		minMonthReport("Model S", models);
		generateReport("Model x", modelx);
		maxMonthReport("Model X", modelx);
		minMonthReport("Model X", modelx);
	}

	public static List<TeslaService> readReport(String filename) throws IOException {
		List<TeslaService> report = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(",");
			String date = data[0];
			int year = formatMonth(date).getYear();
			int month = formatMonth(date).getMonthValue();
			int sales = Integer.parseInt(data[1]);
			report.add(new TeslaService(year, sales, month, date));
		}
		reader.close();
		return report;
	}

	public static void generateReport(String modelname, List<TeslaService> modelList) {
		System.out.println(modelname + " Yearly Sales Report");
		System.out.println("---------------------");
		Map<Integer, Integer> yearlySales = modelList.stream()
				.collect(Collectors.groupingBy(TeslaService::getYear, Collectors.summingInt(TeslaService::getSales)));
		yearlySales.forEach((year, totalsales) -> System.out.println(year + " -> " + totalsales));
	}

	public static void maxMonthReport(String modelname, List<TeslaService> modelList) {
		OptionalInt maxSales = modelList.stream().mapToInt(TeslaService::getSales).max();
		List<TeslaService> bestMonth = modelList.stream().filter(sales -> sales.getSales() == maxSales.getAsInt())
				.collect(Collectors.toList());
		System.out.print("The best month for " + modelname + " was: ");
		String bestMonthDates = bestMonth.stream().map(TeslaService::getDate).collect(Collectors.joining(", "));
		System.out.println(bestMonthDates);

	}

	public static void minMonthReport(String modelname, List<TeslaService> modelList) {
		OptionalInt minSales = modelList.stream().mapToInt(TeslaService::getSales).min();
		List<TeslaService> worstMonth = modelList.stream().filter(sales -> sales.getSales() == minSales.getAsInt())
				.collect(Collectors.toList());
		System.out.print("The worst month for " + modelname + " was: ");
		String worstMonthDates = worstMonth.stream().map(TeslaService::getDate).collect(Collectors.joining(", "));
		System.out.println(worstMonthDates);

	}

	public static YearMonth formatMonth(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
		YearMonth localDate = YearMonth.parse(date, formatter);
		return localDate;
	}

}