package skredebank.controllers.skredebank;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import skredebank.Skredebank;
import skredebank.data.Person;

public class bankAccountsController {
    public bankAccountsController() {

    }

    @FXML
    private Button backButton;
    @FXML
    private Button createUserButton;
    @FXML
    private Label error;
    @FXML
    private TextField givenName;
    @FXML
    private TextField surName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField bankIDPin;
    @FXML
    private TextField confirmBankIDPin;

    Skredebank skredebank;

    public void initialize() {
        skredebank = Skredebank.getInstance();
    }

    public void createUser(ActionEvent event) throws IOException {
        try{
            Person p1 = new Person(phoneNumber.getText(), birthDate.getValue(), givenName.getText(), surName.getText(), bankIDPin.getText());
            //ID = new Person(givenName.getText(), surName.getText(), phoneNumber.getText(), birthDate.getValue(), bankIDPin.getText());
            skredebank.getAccountObject().addAccounts(p1.getUserId(), p1);
            skredebank.getAccountSaver().writeFile(skredebank.getAccountObject());
        }
        
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }



    }

    public void newUser(ActionEvent event) throws IOException {
        skredebank.getApp().changeScene("newUser.fxml");
    }

    
    public void back(ActionEvent event) throws IOException {
        skredebank.getApp().changeScene("login.fxml");
    }

}
