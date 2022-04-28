package jbank.controllers.beforeLogin;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.logic.JBankHelp;

public class LogInController {

    @FXML
    private Button nextButton;
    @FXML
    private Button newUserButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField birthDate;

    Jbank jbank;

    public void initialize() {
        jbank = Jbank.getInstance();
        try {
            jbank.jBankLogin();
        } catch (IllegalArgumentException | IOException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }
        catch (IllegalStateException e){
            //its fine that you dont have any accounts the first time you login
        }
    }

    public void newUser() throws IOException {
        jbank.getApp().changeScene("newUser.fxml");
    }

    public void userLogIn() throws IOException {
        if (phoneNumber.getText().isEmpty() && birthDate.getText().isEmpty()) {
            wrongLogIn.setText("Venligst fyll inn mobil og fødselsdato");
        }
        String userID = phoneNumber.getText().toString() + birthDate.getText().toString();
        if (jbank.userLogin(userID)) {
            jbank.getApp().changeScene("bankID.fxml");
        } else {
            wrongLogIn.setText("Det finnes ingen bruker med dette mobilnummeret og fødselsdato ");
        }
    }

}