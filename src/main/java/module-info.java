module com.example.superdemo {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires org.controlsfx.controls;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires org.jsoup;
	
	opens com.example.superdemo to javafx.fxml;
	exports com.example.superdemo;
}