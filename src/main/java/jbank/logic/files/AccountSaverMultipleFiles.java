package jbank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import jbank.data.Accounts;

public class AccountSaverMultipleFiles implements IFileHandler {

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



            // accounts.getAccounts().get(filename).forEach(entry -> {
            //     writer.println(entry.getKey() + " " + entry.getValue().toString());
            // });
        }
        

    }

     File getFile(String filename) {
        return new File("src\\main\\java\\jbank\\accounts\\" + filename + ".txt");
    }
}
