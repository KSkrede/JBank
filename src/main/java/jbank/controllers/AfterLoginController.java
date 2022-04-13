package jbank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import jbank.JbankApp;

import java.io.IOException;

public class AfterLoginController {

    @FXML
    private Button logout;


    public void userLogOut(ActionEvent event) throws IOException {
        JbankApp m = new JbankApp();
        m.changeScene("login.fxml");

    }
}
