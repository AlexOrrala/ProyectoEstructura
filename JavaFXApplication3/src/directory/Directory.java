/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directory;

import TDA.BinaryTree;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.FilerException;

/**
 *
 * @author alex_
 */
public class Directory {
    private String name;
    private String color;
    private long peso;
    private boolean isdirectory;
    
    
    public Directory(){
        this(null,null, 0, false);
    }
    
    public Directory(boolean isdirectory,String name){
        this(name,null, 0, isdirectory);
    }
    
    public Directory(String name,String color,long peso,boolean isdirectory){
        this.name = name;
        this.color = color;
        this.peso = peso;
        this.isdirectory = isdirectory;
    
    }
    public void setPeso(long peso){
       this.peso = peso;
    }
    public long getPeso(){
        return peso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isIsdirectory() {
        return isdirectory;
    }

    public void setIsdirectory(boolean isdirectory) {
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
    
    public BinaryTree<Directory> cargarubicacion(String directory){
        Stack<Directory> retornar = new Stack<Directory>();
        BinaryTree<Directory> arbol = null;
        File directoryfile = new File(directory);
        Exception e = new FilerException(directory + " DIRECTORY NO VALID");
        if(!directoryfile.isDirectory()){
            e.printStackTrace();
        }
        if(!directoryfile.exists()){
            e.printStackTrace();
        } else {
            arbol = new BinaryTree(new Directory(true, directoryfile.getName()));
            File[] ficheros = directoryfile.listFiles();
            LinkedList<File> arreglado=  new LinkedList<>();
            for (File fichero : ficheros) {
                if(!fichero.getName().contains(".")){
                arreglado.add(fichero);
                }
            }
            for (File fichero : ficheros) {
                if(fichero.getName().contains(".")){
                arreglado.add(fichero);
                }
            }
            for (File fichero : arreglado) {
                if(!fichero.isDirectory()){
                    
                    System.out.println(fichero.getName());
                    String arreglado2 = fichero.getName().replace(".", ",");
                    String[] extension = arreglado2.split(",");
                    long peso = fichero.length();
                    arbol.addChildren(new BinaryTree(new Directory(extension[0],colors(extension[1]), peso, false)));
                    
                    long pesopadre = arbol.getRoot().getContent().getPeso() + peso;
                    
                    arbol.getRoot().getContent().setPeso(pesopadre);
                }else{
                    arbol.addChildren(cargarubicacion(directory+"\\"+fichero.getName()));
                    
                }
                
            }
        }
        return arbol;
    }
}
