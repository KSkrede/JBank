package jbank.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import jbank.data.BankAccount;

public class JBankHelp {

    public static int dateToInt(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("ddMMyy"));
        int birthdayInt = Integer.parseInt(formattedDate);
        return birthdayInt;
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("ddMMyy"));
    }

    public static String todayToString(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public static LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        return LocalDate.parse(date, formatter);
    }

    public static boolean isAllDigit(String string) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
            count++;
        }
        return count > 0;
    }

    public static boolean isAllLetters(String string) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
            count++;
        }
        return count > 0;
    }

    // so that you can have a middlename
    public static boolean isAllLettersOrBlank(String string) {
        int letters = 0;
        for (char c : string.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
            if (Character.isLetter(c)) {
                letters++;
            }
        }
        if (letters > 0) {
            return true;
        }
        return false;
    }

    // inspirert fra Øvingsforelesning 18.03.22
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

    public static BankAccount choseBankAccount(BankAccount selectedBankAccount,
            ArrayList<BankAccount> loggedInPersonBankManager, String title, String header, String content)
            throws NoSuchElementException {
        ChoiceDialog<BankAccount> dialog = new ChoiceDialog<BankAccount>(selectedBankAccount,
                loggedInPersonBankManager);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        BankAccount chosenBankAccount = dialog.showAndWait().get();
        return chosenBankAccount;
    }

    public static String choseStock(String selectedStock,
            ArrayList<String> allStocks) {
        ChoiceDialog<String> dialog = new ChoiceDialog<String>(selectedStock, allStocks);
        dialog.setTitle("Velg aksje");
        dialog.setHeaderText("Velg aksje under:");
        String chosenStock = dialog.showAndWait().get();
        return chosenStock;
    }

    public static String choseSort() {
        ChoiceDialog<String> dialog = new ChoiceDialog<String>("Navn økende", "Navn økende", "Navn synkende",
                "Verdi økende", "Verdi synkende");
        dialog.setTitle("Velg sortering");
        dialog.setHeaderText("Velg sortering under:");
        String sort = dialog.showAndWait().get();
        return sort;
    }

    public static int amount() throws NumberFormatException, IllegalArgumentException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Beløp");
        dialog.setHeaderText("Velg et beløp: ");
        dialog.setContentText("Antall kr: ");
        String tester = dialog.showAndWait().get();
        if (tester == null || tester == "" || !isAllDigit(tester)) {
            throw new IllegalArgumentException("Ugyldig valg");
        }
        int amount = Integer.parseInt(tester);
        return amount;
    }

    public static int number() throws NumberFormatException, IllegalArgumentException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Antall");
        dialog.setHeaderText("Velg antall aksjer under: ");
        String tester = dialog.showAndWait().get();
        if (tester == null || tester == "" || !isAllDigit(tester)) {
            throw new IllegalArgumentException("Ugyldig valg");
        }
        int number = Integer.parseInt(tester);
        return number;
    }

    public static String name() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Navn");
        dialog.setHeaderText("Velg et navn: ");
        return dialog.showAndWait().get().strip();
    }

    public static String pin(String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Bytte av pin");
        dialog.setHeaderText("Skriv inn pin under: ");
        dialog.setContentText(content);
        return dialog.showAndWait().get();
    }

    public static boolean confirm(String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Bekreft");
        alert.setHeaderText("Bekrefter du at: ");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

}
