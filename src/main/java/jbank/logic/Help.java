package jbank.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


}
