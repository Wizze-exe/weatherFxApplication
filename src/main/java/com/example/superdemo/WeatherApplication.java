package com.example.superdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherApplication extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		AppPreloader appPreloader = new AppPreloader();
		appPreloader.start(stage);
		
		FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("appLayout.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 410);
		stage.setTitle("Weather and news");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}