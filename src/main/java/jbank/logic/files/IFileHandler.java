package jbank.logic.files;

import java.io.IOException;

import jbank.Jbank;

public interface IFileHandler {

    void readObject(String file, Jbank jbank) throws IOException;

    void writeObject(String file, Jbank jbank) throws IOException;

}
