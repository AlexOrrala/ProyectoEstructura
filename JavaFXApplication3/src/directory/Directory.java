/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directory;

import TDA.BinaryTree;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.annotation.processing.FilerException;

/**
 *
 * @author alex_
 */
public class Directory {
    private String name;
    private String directory;
    private String color;
    private long peso;
    private boolean isdirectory;
    private HashMap<String, Float> colorespeso ;
    
    
    public Directory(){
        this(null,null, 0, false,null);
    }
    
    public Directory(boolean isdirectory,String name){
        this(name,null, 0, isdirectory,null);
    }
    
    public Directory(String name,String color,long peso,boolean isdirectory,String direccion){
        this.colorespeso = new HashMap<>();
        this.name = name;
        this.color = color;
        this.peso = peso;
        this.isdirectory = isdirectory;
        if(isdirectory == true){
            colorespeso = new HashMap<String,Float>();
        }
        directory = direccion;
    
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
    
    public void anadirpesocolor(String key,float valor){
        colorespeso.put(key, valor);
    }
    
    public void sumarpesocolor(String key,float valor){
        if(colorespeso.get(key) != null){
        colorespeso.put(key, colorespeso.get(key)+valor);
        }else{
            anadirpesocolor(key, valor);
            //System.out.println(key+":"+valor);
        }
        
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
                return "-fx-background-color: #A2A2A2"; // gris
            case ".pdf":
                return "-fx-background-color: #FF3333"; //rojo
            case ".jpg":
                return "-fx-background-color: #F3FF33"; // amarillo
            case ".docx":
                return "-fx-background-color: #478DEC"; // azul
            case ".xlsx":
                return "-fx-background-color: #008000"; // verde
            case "":
                return "-fx-background-color: #FFF700"; //armarillo juerte
            default:
                return "-fx-background-color: #CB3FBE"; //fucsia
        } 
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public HashMap<String, Float> getColorespeso() {
        return colorespeso;
    }

    public void setColorespeso(HashMap<String, Float> colorespeso) {
        this.colorespeso = colorespeso;
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
            probardirectorio(arreglado,ficheros);
            probarfichero(arreglado,ficheros);
            for (File fichero : arreglado) {
                if(!fichero.isDirectory()){
                    
                    //System.out.println(fichero.getName());
                    String arreglado2 = fichero.getName().replace(".", ",");
                    String[] extension = arreglado2.split(",");
                    long peso = fichero.length();
                    arbol.addChildren(new BinaryTree(new Directory(extension[0],colors("."+extension[1]), peso, false,fichero.getPath())));
                    
                    long pesopadre = arbol.getRoot().getContent().getPeso() + peso;
                    arbol.getRoot().getContent().sumarpesocolor("."+extension[1], pesopadre);
                    System.out.println("."+extension[0]);
                    arbol.getRoot().getContent().setPeso(pesopadre);
                }else{
                    //BinaryTree<Directory> padre = new BinaryTree<Directory>((cargarubicacion(directory+"\\"+fichero.getName())).getRoot().getContent());
                    arbol.addChildren(cargarubicacion(directory+"\\"+fichero.getName()));
                    //arbol.getRoot().getContent().setPeso(arbol.getRoot().getContent().getPeso()+padre.getRoot().getContent().getPeso());
                    
                }
                
            }
        }
        arbol.getRoot().getContent().setPeso(0);
        for(BinaryTree<Directory> arbolito :arbol.getChildrens()){
            arbol.getRoot().getContent().setPeso(arbol.getRoot().getContent().getPeso()+arbolito.getRoot().getContent().getPeso()); ;
        }
        return arbol;
    }

    public void actualizaroot(BinaryTree<Directory> arbol){
        arbol.getRoot().getContent().setPeso(0);
        for(BinaryTree<Directory> arbolito :arbol.getChildrens()){
            arbol.getRoot().getContent().setPeso(arbol.getRoot().getContent().getPeso()+arbolito.getRoot().getContent().getPeso()); ;
        }
    }
    
    
    private void probarfichero(LinkedList<File> arreglado,File[] ficheros) {
        for (File fichero : ficheros) {
                if(fichero.getName().contains(".") && true){
                arreglado.add(fichero);
                }
            }
    }
    private void probardirectorio(LinkedList<File> arreglado,File[] ficheros) {
        for (File fichero : ficheros) {
                if(!fichero.getName().contains(".") && true){
                arreglado.add(fichero);
                }
            }
    }
}
