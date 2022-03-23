package skredebank.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skredebank.SkredebankApp;
import skredebank.data.Person;
import skredebank.logic.BankID;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;

public class BankIDController {
    public BankIDController() {}

    SkredebankApp m = new SkredebankApp();

    Person loggedInUser;

    public Person getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Person loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @FXML
    private Button backButton;
    @FXML
    private Label BankIDLabel;

    @FXML
    public void initialize() {
        setBankIDLabel(); 
    }

    private void setBankIDLabel(){
        BankID test = new BankID(new Person("Kristian", "Skrede", "40612594", LocalDate.of(2000, 1, 23), "1234"));
        BankIDLabel.setText(test.getBankIDText());
    }

    public void back(ActionEvent event) throws IOException {
        m.changeScene("login.fxml");
    }

}


