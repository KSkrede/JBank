package skredebank.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import skredebank.Skredebank;
import skredebank.data.Person;
import skredebank.logic.BankID;

public class BankIDController {
    public BankIDController() {}

    @FXML
    private Button backButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label BankIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private PasswordField pinField;

    Skredebank skredebank;
    Person loggedInPerson;
    

    @FXML
    public void initialize() {
        skredebank = Skredebank.getInstance();
        loggedInPerson = skredebank.getAccountObject().getLoggedInPerson();
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
    public void Back(ActionEvent event) throws IOException {
        skredebank.getApp().changeScene("login.fxml");
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {

        if(loggedInPerson.getBankIDPin().equals(pinField.getText().toString())) {
            skredebank.getApp().changeScene("skredebank/skredeBank.fxml");
        }
        else System.out.println("nay" + pinField.getText().toString());
    

}

}


