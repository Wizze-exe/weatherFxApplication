package com.example.superdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class WeatherController {
	public Label tempLabelYa;
	public Label tempLabelAc;
	public Label yaFeelsLike;
	public Label yaWindSpeed;
	public Label yaHumidity;
	public Label yaPressure;
	public Label acFeelsLike;
	public Label acWindSpeed;
	public Label acHumidity;
	public Label acPressure;
	Parser parser = new Parser();
	
	@FXML
	protected void onReloadButtonClick() throws IOException {
		parser.updateWeatherInfo();
		
		//- −
		tempLabelYa.setText(parser.getYandexWeather()[0] + "°C");
		yaFeelsLike.setText(parser.getYandexWeather()[1] + "°");
		yaWindSpeed.setText(parser.getYandexWeather()[2]);
		yaHumidity.setText(parser.getYandexWeather()[3]);
		yaPressure.setText(parser.getYandexWeather()[4]);
		
		tempLabelAc.setText(parser.getAccuWeather()[0]);
		acFeelsLike.setText(parser.getAccuWeather()[1]);
		
	}
}