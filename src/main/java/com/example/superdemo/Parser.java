package com.example.superdemo;

import javafx.scene.image.Image;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {
	private Document yaDoc;
	private Document acDoc;
	private Document panoramaDoc;
	
	public boolean updateSiteInfo() throws IOException {
		boolean yandex = false;
		try {
			yaDoc = Jsoup.connect("https://yandex.ru/pogoda/?lat=55.74945068&lon=37.54282380").get();
			yandex = true;
		}
		catch (HttpStatusException e) {
			
			System.out.println("http status yandex: " + e.getMessage());
		}
		acDoc = Jsoup.connect("https://www.accuweather.com/ru/ru/moscow/294021/current-weather/294021").get();
		panoramaDoc = Jsoup.connect("https://panorama.pub").get();
		return yandex;
	}
	
	public String[] getYandexWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(1).text().replace('−', '-');
		weatherInfo[1] = yaDoc.getElementsByClass("temp__value temp__value_with-unit").get(2).text().replace('−', '-');
		weatherInfo[2] = yaDoc.getElementsByClass("wind-speed").text().replace(',', '.');
		weatherInfo[3] = yaDoc.getElementsByClass("term__value").get(3).text().split(" ")[0];
		weatherInfo[4] = yaDoc.getElementsByClass("term__value").get(4).text();
		return weatherInfo;
	}
	
	public String[] getAccuWeather() {
		String[] weatherInfo = new String[5];
		weatherInfo[0] = acDoc.getElementsByClass("display-temp").get(0).text();
		weatherInfo[1] = acDoc.getElementsByClass("current-weather-extra no-realfeel-phrase").text().split(" ")[1];
		weatherInfo[2] = Double.toString(Math.round(Double.parseDouble(acDoc.getElementsByClass("detail-item spaced-content").select("div:contains(Ветер)").text().split(" ")[2]) / 3.6));
		weatherInfo[3] = acDoc.getElementsByClass("detail-item spaced-content").select("div:contains(Влажность)").text().split(" ")[1];
		weatherInfo[4] = Long.toString(Math.round(Integer.parseInt(acDoc.getElementsByClass("detail-item spaced-content").select("div:contains(Давление)").text().split(" ")[2]) / 1.333));
		return weatherInfo;
	}
	
	public String[] getPanoramaNews(){
		String[] newsInfo = new String[12];
		Elements elementsNews = panoramaDoc.select("div.shrink-0.w-full").select("div.pl-2.pr-1.text-sm.basis-auto.self-center");
		for (int i = 0; i < newsInfo.length; i++) {
			newsInfo[i] = elementsNews.get(i).child(0).text();
		}
		return newsInfo;
	}
	
	public Image[] getPanoramaImages() throws IOException {
		String[] imgInfo = new String[12];
		Image[] images = new Image[12];
		Elements elementsImg = panoramaDoc.select("div.shrink-0.w-full").select("img.rounded-md");
		for (int i = 0; i < imgInfo.length; i++) {
			imgInfo[i] = elementsImg.get(i).absUrl("src");
			URL imgUrl = new URL(imgInfo[i]);
			images[i] = new Image(imgUrl.openStream());
		}
		return images;
	}
}
