package jbank.controllers.jbank;
import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import jbank.Jbank;
import jbank.data.Person;
import jbank.logic.JBankHelp;

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
    private StockController stockController;


    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        setLoggedInUser();
        setDateLabel();

    }

    @FXML
    public void setLoggedInUser(){
        loggedInUser.setText(loggedInPerson.getFullName());
    }

    @FXML
    public void setDateLabel(){
        currentDate.setText(JBankHelp.todayToString());
    }

    @FXML
    public void settings() throws IOException{
        jbank.getApp().changeScene("jbank/settings.fxml");
    }

    @FXML
    public void nextDay(){
        jbank.stockMarket.nextDay();
        stockController.updateStockInfo();
        System.out.println("her trykkes cet");
    }


    @FXML
    public void logOut() throws IOException{
        jbank.getAccountObject().setLoggedInPerson(null);
        jbank.getApp().changeScene("login.fxml");

    }



    }