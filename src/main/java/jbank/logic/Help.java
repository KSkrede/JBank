package jbank.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import jbank.data.BankAccount;

public class Help {

    public static int dateToInt(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("ddMMyy"));
        int birthdayInt = Integer.parseInt(formattedDate);
        return birthdayInt;
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("ddMMyy"));
    }

    public static String todayToString() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public static LocalDate stringToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        return LocalDate.parse(date, formatter);
    }



    public static boolean isAllDigit(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllLetters(String str) {
        for (char c : str.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

//Kopiert fra Øvingsforelesning 18.03.22
        public static void showErrorMessage(String errorMessage) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Feilmelding");
            alert.setHeaderText("Det har skjedd en feil:");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }

        public static void showInformation(String header, String content) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informasjon");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        }

        public static BankAccount choseBankAccount(BankAccount selectedBankAccount, ArrayList<BankAccount> loggedInPersonBankAccounts, String title, String header, String content ){
            ChoiceDialog<BankAccount> dialog = new ChoiceDialog<BankAccount>(selectedBankAccount, loggedInPersonBankAccounts);
            dialog.setTitle(title);
            dialog.setHeaderText(header);
            dialog.setContentText(content);
            BankAccount chosenBankAccount = dialog.showAndWait().get();
            return chosenBankAccount;

        }

    
    }


