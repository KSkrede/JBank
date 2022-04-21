package jbank.controllers.jbank;
import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import jbank.Jbank;
import jbank.data.Person;
import jbank.logic.Help;

public class JBankController {

    Jbank jbank;
    Person loggedInPerson;

    @FXML
    private Button loggedInUser;
    @FXML
    private Button nextDay;
    @FXML
    private Label currentDate;
    @FXML
    private Button logOuButton;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        setLoggedInUser();
        setDateLabel();

    }

    @FXML
    private void setLoggedInUser(){
        loggedInUser.setText(loggedInPerson.getFullName());
    }

    @FXML
    private void setDateLabel(){
        currentDate.setText(Help.todayToString());
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException{
        jbank.getAccountObject().setLoggedInPerson(null);
        jbank.getApp().changeScene("login.fxml");

    }



    }