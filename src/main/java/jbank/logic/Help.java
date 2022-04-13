package jbank.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
        return LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
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
            if(!Character.isLetter(c)) {
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
    }


