package jbank.controllers.jbank;

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

    }