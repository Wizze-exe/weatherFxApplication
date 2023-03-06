package com.example.superdemo;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser {
	private Document yaDoc;
	private Document acDoc;
	private String yandexWeatherURL = "https://yandex.ru/pogoda/?lat=55.75581741&lon=37.61764526";
	private String accuWeatherURL = "https://www.accuweather.com/ru/ru/moscow/294021/current-weather/294021";
	
	public void updateWeatherInfo() throws IOException {
		yaDoc = Jsoup.connect(yandexWeatherURL).get();
		acDoc = Jsoup.connect(accuWeatherURL).get();
	}
	public String[] getYandexWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(1).toString();
		weatherInfo[1] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(2).toString();
		weatherInfo[2] = yaDoc.getElementsByClass("wind-speed").toString();
		weatherInfo[3] = yaDoc.getElementsByClass("term__value").get(3).toString();
		weatherInfo[4] = yaDoc.getElementsByClass("term__value").get(4).toString();
		for (int i = 0; i < 3; i++) {
			weatherInfo[i] = weatherInfo[i].substring(weatherInfo[i].indexOf('>') + 1, weatherInfo[i].indexOf('<', 1));
		}
		for (int i = 3; i < 5; i++) {
			weatherInfo[i] = weatherInfo[i].substring(weatherInfo[i].indexOf('/') + 3, weatherInfo[i].indexOf('/') + 6);
		}
		return weatherInfo;
	}
	
	public String[] getAccuWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = acDoc.getElementsByClass("display-temp").get(0).toString();
		weatherInfo[1] = acDoc.getElementsByClass("real-feel").toString();
		weatherInfo[0] = weatherInfo[0].substring(weatherInfo[0].indexOf('>') + 1, weatherInfo[0].indexOf('<', 1));
		//weatherInfo[2] = doc.getElementsByClass("")
		return weatherInfo;
	}
}
