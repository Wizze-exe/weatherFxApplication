package com.example.superdemo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
	@FXML
	private List<HBox> hboxList;
	Parser parser = new Parser();
	
	@FXML
	protected void onReloadButtonClick() throws IOException {
		//- −
		if (parser.updateSiteInfo()){
			String[] infoYa = parser.getYandexWeather();
			tempLabelYa.setText(infoYa[0] + "°C");
			yaFeelsLike.setText(infoYa[1] + "°");
			yaWindSpeed.setText(infoYa[2]);
			yaHumidity.setText(infoYa[3]);
			yaPressure.setText(infoYa[4]);
		}
		
		String[] panoramaNews = parser.getPanoramaNews();
		Image[] panoramaImages = parser.getPanoramaImages();
		int i = 0;
		int j = 0;
		for (HBox hbox : hboxList) {
			// Получаем список всех дочерних элементов HBox
			ObservableList<Node> children = hbox.getChildren();
			// Фильтруем список, чтобы получить только Label
			List<Label> labels = children.stream()
					.filter(node -> node instanceof Label)
					.map(node -> (Label) node)
					.collect(Collectors.toList());
			List<ImageView> images = children.stream()
					.filter(node -> node instanceof ImageView)
					.map(node -> (ImageView) node)
					.collect(Collectors.toList());
			// Устанавливаем текст для каждого Label
			for (Label l : labels) {
				l.setText(panoramaNews[i]);
				i++;
			}
			for (ImageView iv : images){
				iv.setImage(panoramaImages[j]);
				j++;
			}
		}
		
		String[] infoAc = parser.getAccuWeather();
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