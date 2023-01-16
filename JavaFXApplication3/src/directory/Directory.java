/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directory;

import java.io.*;
import java.util.Stack;
import javax.annotation.processing.FilerException;

/**
 *
 * @author alex_
 */
public class Directory {
    private String color;
    private String peso;
    private boolean isdirectory;
    
    public String colors(String ext){
        
        switch (ext) {
            case ".txt":
                return "#A2A2A2"; // gris
            case ".pdf":
                return "#FF3333"; //rojo
            case ".jpg":
                return "#F3FF33"; // amarillo
            case ".word":
                return "#478DEC"; // azul
            case "":
                return "#FFF700"; //armarillo juerte
            default:
                return "#CB3FBE"; //fucsia
        } 
    }
    
    public Stack cargarubicacion(String directory){
        Stack<Directory> retornar = new Stack<Directory>();
        File directoryfile = new File(directory);
        Exception e = new FilerException(directory + " DIRECTORY NO VALID");
        if(!directoryfile.isDirectory()){
            e.printStackTrace();
        }
        if(!directoryfile.exists()){
            e.printStackTrace();
        } else {
            File[] ficheros = directoryfile.listFiles();
            for (File fichero : ficheros) {
                if(!fichero.isDirectory()){
                float tamano = fichero.length();
                System.out.println(tamano);
                }
                
            }
        }
        return retornar;
    }
}
