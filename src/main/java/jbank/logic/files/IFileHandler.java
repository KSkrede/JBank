package jbank.logic.files;

import java.io.FileNotFoundException;

import jbank.data.Accounts;

public interface IFileHandler {

    // Object readFile(String filename, Object object) throws FileNotFoundException;

    // void writeFile(String filename, Object  object) throws FileNotFoundException;

    Object readFile(String filename, Accounts accounts) throws FileNotFoundException;

    void writeFile(String filename, Accounts  accounts) throws FileNotFoundException;

}
