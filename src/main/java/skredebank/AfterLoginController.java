package skredebank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterLoginController {

    @FXML
    private Button logout;


    public void userLogOut(ActionEvent event) throws IOException {
        SkredebankApp m = new SkredebankApp();
        m.changeScene("login.fxml");

    }
}