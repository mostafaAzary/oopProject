package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //stage.setResizable(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Scene1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 423, 370);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
