package skredebank.logic;

import java.io.FileNotFoundException;

import skredebank.Skredebank;

public interface IFileHandler {

    Skredebank readFile(String filename, Object skredebank) throws FileNotFoundException;

    void writeFile(String filename, Object  skredebank) throws FileNotFoundException;

}
