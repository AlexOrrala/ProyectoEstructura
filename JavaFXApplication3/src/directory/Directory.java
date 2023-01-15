/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directory;

import java.io.*;
import javax.annotation.processing.FilerException;

/**
 *
 * @author alex_
 */
public class Directory {
    private String directory;

    public Directory(String directory) {
        this.directory = directory;
    }
    
    
    
    
    public void SetDirectory(String directory){
        this.directory = directory;
    }
    
    public void cargarubicacion(){
        File directoryfile = new File(directory);
        Exception e = new FilerException(directory + " DIRECTORY NO VALID");
        if(!directoryfile.isDirectory()){
            e.printStackTrace();
            return;
        }
        if(!directoryfile.exists()){
            e.printStackTrace();
            return;
        } else {
            File[] ficheros = directoryfile.listFiles();
            for (File fichero : ficheros) {
                System.out.println(fichero.getName());
            }
        }
    
    }
}
