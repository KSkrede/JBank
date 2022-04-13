package jbank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import jbank.data.Accounts;

public class AccountSaverToDate implements IFileHandler {

    @Override
    public Object readFile(String filename, Accounts accounts) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(getFile(filename))){
           // String 
        }
        return null;
    }

    @Override
    public void writeFile(String filename, Accounts accounts) throws FileNotFoundException {
        //Casting test
        //Accounts a = (Accounts) object; 
        // try (PrintWriter writer = new PrintWriter(getFile(filename))) {

        //     writer.println(a.getAccounts());

        //     a.getAccounts().entrySet().forEach(entry -> {
        //         writer.println(entry.getKey() + " " + entry.getValue().toString());
        //     });
        // }
        
        try (PrintWriter writer = new PrintWriter(getFile(filename))) {

            writer.println(accounts.toString()); };

        }
        

     File getFile(String filename) {
        return new File("src\\main\\java\\skredebank\\accounts\\" + filename + ".txt");
    }
}
