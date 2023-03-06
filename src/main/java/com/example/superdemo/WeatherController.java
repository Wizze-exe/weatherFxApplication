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
	
	@FXML
	protected void onReloadButtonClick() throws IOException {
		Parser parser = new Parser();
		
		parser.updateWeatherInfo();
		
		tempLabelYa.setText(parser.getYandexWeather()[0] + "°C");
		tempLabelYa.setLayoutX(100);
		yaFeelsLike.setText(yaFeelsLike.getText() + parser.getYandexWeather()[1]);
		yaWindSpeed.setText(yaWindSpeed.getText() + parser.getYandexWeather()[2]);
		yaHumidity.setText(yaHumidity.getText() + parser.getYandexWeather()[3]);
		yaPressure.setText(yaPressure.getText() + parser.getYandexWeather()[4]);
		
		tempLabelAc.setText(parser.getAccuWeather()[0] + "C");

		
	}
}