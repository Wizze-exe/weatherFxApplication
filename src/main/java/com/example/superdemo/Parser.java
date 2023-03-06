package com.example.superdemo;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser {
	private Document yaDoc;
	private Document acDoc;
	
	public void updateWeatherInfo() throws IOException {
		try {
			yaDoc = Jsoup.connect("https://yandex.ru/pogoda/?lat=55.75581741&lon=37.61764527").get();
		}
		catch (HttpStatusException e) {
			System.out.println("http status yandex");
		}
		acDoc = Jsoup.connect("https://www.accuweather.com/ru/ru/moscow/294021/current-weather/294021").get();
	}
	
	public String[] getYandexWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(1).text().replace('−', '-');
		weatherInfo[1] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(2).text().replace('−', '-');
		weatherInfo[2] = yaDoc.getElementsByClass("wind-speed").text();
		weatherInfo[3] = yaDoc.getElementsByClass("term__value").get(3).text();
		weatherInfo[4] = yaDoc.getElementsByClass("term__value").get(4).text();
		return weatherInfo;
	}
	
	public String[] getAccuWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = acDoc.getElementsByClass("display-temp").get(0).text();
		weatherInfo[1] = acDoc.getElementsByClass("current-weather-extra no-realfeel-phrase").text().split(" ")[1];
		
		return weatherInfo;
	}
}
