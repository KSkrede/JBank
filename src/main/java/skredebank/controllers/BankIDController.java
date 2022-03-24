package skredebank.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import skredebank.Skredebank;
import skredebank.logic.BankID;

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
        nameLabel.setText("Velkommen tilbake " + skredebank.getAccountObject().getLoggedInPerson().getGivenName() + ". Din BankID Referanse:"); 
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


