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
    
    
    public Directory(){
        this(null, 0, false);
    }
    
    public Directory(boolean isdirectory){
        this(null, 0, isdirectory);
    }
    
    public Directory(String color,float peso,boolean isdirectory){
        this.color = color;
        this.peso = peso;
        this.isdirectory = isdirectory;
    
    }
    
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
            BinaryTree<Directory> arbol = new BinaryTree(directoryfile.getName());
            File[] ficheros = directoryfile.listFiles();
            for (File fichero : ficheros) {
                if(!fichero.isDirectory()){
                    System.out.println(fichero.getName());
                    String arreglado = fichero.getName().replace(".", ",");
                    String[] extension = arreglado.split(",");
                    float peso = fichero.length();
                    arbol.addChildren(new BinaryTree(new Directory(colors(extension[1]), peso, false)));
                }else{
                    cargarubicacion(directory+"\\"+fichero.getName());
                }
                
            }
        }
        return retornar;
    }
}
