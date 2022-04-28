package jbank.logic.files;

import java.io.IOException;

import jbank.Jbank;

public interface IFileHandler {

    void readObject(String fileName, Jbank jbank) throws IOException;

    void writeObject(String fileName, Jbank jbank) throws IOException;

}
