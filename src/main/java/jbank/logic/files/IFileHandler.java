package jbank.logic.files;

import java.io.IOException;

import jbank.data.Accounts;

public interface IFileHandler {

    Accounts readAccounts(String file, Accounts accounts) throws IOException;

    void writeAccounts(String file, Accounts accounts) throws IOException;

}
