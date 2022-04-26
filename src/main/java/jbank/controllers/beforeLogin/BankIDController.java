package jbank.controllers.beforeLogin;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import jbank.Jbank;
import jbank.data.Person;
import jbank.logic.BankID;
import jbank.logic.JBankHelp;

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

    Jbank jbank;
    Person loggedInPerson;
    

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        setBankIDLabel();
        nameLabel.setText("Velkommen tilbake " + loggedInPerson.getGivenName() + ". Din BankID Referanse:"); 
    }

    private void setBankIDLabel(){
        BankIDLabel.setText(BankID.getBankIDText());
    }

    @FXML
    private void refreshBankIDLabel() throws IOException {
        BankIDLabel.setText(BankID.getBankIDText());
    }

    @FXML
    public void Back() throws IOException {
        jbank.getApp().changeScene("login.fxml");
    }

    @FXML
    private void Login() throws IOException {

        if(loggedInPerson.getBankIDPin().equals(pinField.getText().toString())) {
            jbank.getApp().changeScene("jbank/jBank.fxml");
        }
        else JBankHelp.showErrorMessage(pinField.getText().toString() + " er feil pin");

}

}


