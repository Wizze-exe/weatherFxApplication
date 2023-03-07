package com.example.superdemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeatherController implements Initializable {
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
		
		String[] infoYa = parser.getYandexWeather();
		String[] infoAc = parser.getAccuWeather();
		
		//- −
		tempLabelYa.setText(infoYa[0] + "°C");
		yaFeelsLike.setText(infoYa[1] + "°");
		yaWindSpeed.setText(infoYa[2]);
		yaHumidity.setText(infoYa[3]);
		yaPressure.setText(infoYa[4]);
		
		tempLabelAc.setText(infoAc[0]);
		acFeelsLike.setText(infoAc[1]);
		acWindSpeed.setText(infoAc[2]);
		acHumidity.setText(infoAc[3] + "%");
		acPressure.setText(infoAc[4] + " мм рт. ст.");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			onReloadButtonClick();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}