package jbank.logic.files;

import java.io.IOException;
import java.nio.file.Path;

import jbank.data.Accounts;

public interface IFileHandler {

    Accounts readAccounts(String file, Accounts accounts) throws IOException;

    void writeAccounts(String file, Accounts  accounts) throws IOException;

    Path getFilePath(String filename);

}
