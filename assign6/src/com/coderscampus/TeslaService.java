package com.coderscampus;

public class TeslaService {
	private int year;
	private int month;
	private int sales;
	private String date;

	TeslaService(int year, int sales, int month, String date) {
		this.year = year;
		this.sales = sales;
		this.month = month;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {

		return date;

	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int compareTo(TeslaService other) {
		return Integer.compare(this.sales, other.sales);
	}

}
