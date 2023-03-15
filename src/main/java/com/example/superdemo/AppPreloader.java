package com.example.superdemo;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppPreloader extends Preloader {
	private Stage preloaderStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.preloaderStage = primaryStage;
		
		VBox loading = new VBox(20);
		loading.setMaxWidth(Region.USE_PREF_SIZE);
		loading.setMaxHeight(Region.USE_PREF_SIZE);
		loading.getChildren().add(new ProgressBar());
		loading.getChildren().add(new Label("Please wait..."));
		
		BorderPane root = new BorderPane(loading);
		Scene scene = new Scene(root, 600, 410);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Parser parser = new Parser();
		parser.updateSiteInfo();
	}
	
	@Override
	public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
		if (stateChangeNotification.getType() == Type.BEFORE_START) {
			preloaderStage.hide();
		}
	}
}
