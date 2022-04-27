package jbank.controllers.jbank;
import java.io.IOException;

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
        currentDate.setText("Dato: " + JBankHelp.todayToString(0));
    }

    @FXML
    public void settings() throws IOException{
        jbank.getApp().changeScene("jbank/settings.fxml");
    }

    @FXML
    public void nextDay(){
        jbank.getStockTracker().log(jbank.getStockMarket().getStocks(), jbank.getDays(), jbank.getStockIndex().getAvg());
        jbank.getStockMarket().nextDay();
        stockController.nextDay();
        jbank.daysIncrease();
        currentDate.setText("Dato: " + JBankHelp.todayToString(jbank.getDays()));
    }


    @FXML
    public void logOut() throws IOException{
        jbank.getAccountObject().setLoggedInPerson(null);
        jbank.getApp().changeScene("login.fxml");

    }

    }