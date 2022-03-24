package skredebank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SkredebankApp extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("SkredeBank");
        //I stedet for 600 600 så hent prefheight widt
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        //finne måte å hente ut height og width fra fxml.
        int sceneWidth = (int)stage.getScene().getWidth();
        int sceneHeight = (int)stage.getScene().getHeight();
        System.out.println(sceneWidth);
        System.out.println(sceneHeight);
        stage.setScene(new Scene(root, sceneWidth, sceneHeight));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
