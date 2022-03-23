package skredebank.logic;

import java.io.File;
import java.io.FileWriter;

public class Files {
    
private static File getFilePath(String filename){
    return new File("src\\main\\java\\skredebank\\savedDiplomas\\" + filename + ".txt");
}  
}
