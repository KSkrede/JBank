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
import skredebank.Skredebank;
import skredebank.SkredebankApp;
import skredebank.data.Person;
import skredebank.data.Accounts;
import skredebank.logic.BankID;

import java.io.IOException;

import javafx.event.ActionEvent;

public class BankIDController {
    public BankIDController() {}

    @FXML
    private Button backButton;
    @FXML
    private Button updateButton;
    @FXML
    private Label BankIDLabel;
    @FXML
    private Label nameLabel;

    Skredebank skredebank;
    

    @FXML
    public void initialize() {
        skredebank = Skredebank.getInstance();
        setBankIDLabel();
        nameLabel.setText(skredebank.getAccountObject().getLoggedInPerson().getGivenName()); 
    }

    private void setBankIDLabel(){
        BankIDLabel.setText(BankID.getBankIDText());
    }

    @FXML
    private void refreshBankIDLabel(ActionEvent event) throws IOException {
        BankIDLabel.setText(BankID.getBankIDText());
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        skredebank.getApp().changeScene("login.fxml");
    }

}


