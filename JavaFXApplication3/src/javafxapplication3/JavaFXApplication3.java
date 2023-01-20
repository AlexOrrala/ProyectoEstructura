/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

import TDA.BinaryTree;
import directory.Directory;
import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 *
 * @author alex_
 */
public class JavaFXApplication3 extends Application {
    private TextField direccionField = new TextField("Dirección");
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox h1 = new VBox();
        Insets padd = new Insets(35, 35, 35, 35);
        h1.setPadding(padd);
        
        Button btn = new Button();
        Button btnpro = new Button();
        btnpro.setText("Treemap");
        btnpro.setAlignment(Pos.CENTER);

        btn.setText("Buscar");
        btn.setAlignment(Pos.CENTER);
        //btn.setLayoutY(150);
        
        btnpro.setOnAction((event) -> {
           Directory d1 = new Directory();
            BinaryTree<Directory> arbol = d1.cargarubicacion(direccionField.getText());
            LinkedList<BinaryTree<Directory>>lista = arbol.getChildrens();
            //System.out.println("---------------------------");
            //System.out.println(arbol.contadorHijos());
            LinkedList<BinaryTree<Directory>> padres = new LinkedList<BinaryTree<Directory>>();
            LinkedList<BinaryTree<Directory>> hijos = new LinkedList<BinaryTree<Directory>>();
            recorrerarbolpadre(lista,padres);
            recorrerarbolhijos(lista,hijos);
            Colorear2(padres, arbol,hijos);
            
            
        });
        
        btn.setOnAction((ActionEvent event) -> {
            Directory d1 = new Directory();
            BinaryTree<Directory> arbol = d1.cargarubicacion(direccionField.getText());
            LinkedList<BinaryTree<Directory>>lista = arbol.getChildrens();
            //System.out.println("---------------------------");
            //System.out.println(arbol.contadorHijos());
            LinkedList<BinaryTree<Directory>> padres = new LinkedList<BinaryTree<Directory>>();
            LinkedList<BinaryTree<Directory>> hijos = new LinkedList<BinaryTree<Directory>>();
            recorrerarbolpadre(lista,padres);
            recorrerarbolhijos(lista,hijos);
            Colorear(padres, arbol,hijos);
            
        });
        
        StackPane root = new StackPane();
        
        direccionField.setAlignment(Pos.CENTER);
        btn.setAlignment(Pos.CENTER);
        
        h1.getChildren().add(direccionField);
        h1.getChildren().add(btn);
        h1.getChildren().add(btnpro);
        root.getChildren().add(h1);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Treemap");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private LinkedList<BinaryTree<Directory>> recorrerarbolhijos(LinkedList<BinaryTree<Directory>> lista,LinkedList<BinaryTree<Directory>> ordenada) {
        LinkedList<BinaryTree<Directory>> hijos = new LinkedList<>();
        
        
        for (BinaryTree<Directory> binaryTree : lista) {
            if (binaryTree.isLeaf()) {
                ordenada.add(binaryTree);
            } 
        }
        return hijos;
    }
    
    private void recorrerarbolpadre(LinkedList<BinaryTree<Directory>> lista,LinkedList<BinaryTree<Directory>> ordenada){
        
        
        for (BinaryTree<Directory> binaryTree : lista) {
            if (!binaryTree.isLeaf()) {
                ordenada.add(binaryTree);
                //recorrerarbolpadre(binaryTree.getChildrens(),ordenada);
            } 
        }
    }
    private void Colorear2(LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, LinkedList<BinaryTree<Directory>> hijos) {
        Stage stagecolor = new Stage();
        
        HBox horizontalpadres = new HBox();
        VBox verticalfuera = new VBox();
        TextField nombre = nombredir(arbol.getRoot().getContent().getName(),arbol.getRoot().getContent().getPeso(),1000);
        verticalfuera.setSpacing(1);
        verticalfuera.getChildren().add(nombre);
        verticalfuera.getChildren().add(horizontalpadres);
        
        
        crearcuadrados2(horizontalpadres, padres, arbol, 2);
        
        SplitPane colores = new SplitPane(verticalfuera);
        
        Scene scenecolors = new Scene(colores, 1000, 1000);
        stagecolor.setScene(scenecolors);
        stagecolor.show();
    }
    
    
    private void Colorear(LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, LinkedList<BinaryTree<Directory>> hijos) {
        Stage stagecolor = new Stage();
        
        HBox horizontalpadres = new HBox();
        VBox verticalfuera = new VBox();
        TextField nombre = nombredir(arbol.getRoot().getContent().getName(),arbol.getRoot().getContent().getPeso(),1000);
        verticalfuera.setSpacing(1);
        verticalfuera.getChildren().add(nombre);
        verticalfuera.getChildren().add(horizontalpadres);
        
        crearcuadrados(horizontalpadres,padres,arbol,20);
        VBox verticalhijos = new VBox();

        for (BinaryTree<Directory> hijo : hijos) {
                if(hijo.isLeaf()){
                    Button b1 = new Button();
                    long dimension = (long) factorpeso(hijo.getRoot().getContent().getPeso(),arbol.getRoot().getContent().getPeso());
                    b1.setMaxSize(dimension,dimension);
                    double hije1 = hijo.getRoot().getContent().getPeso();
                    double padredouble1 = arbol.getRoot().getContent().getPeso();
                    double ancho1 = (hije1/padredouble1)*1000;
                    b1.setStyle(hijo.getRoot().getContent().getColor());
                    b1.setMaxSize(ancho1-10, ancho1-10);
                    b1.setOnAction((event) -> {
                        try{
                            
                            File file = new File(hijo.getRoot().getContent().getDirectory());
                            if(!Desktop.isDesktopSupported()){
                            System.out.println("not supported");
                            }
                            Desktop desktop = Desktop.getDesktop();
                            if(file.exists()){
                                desktop.open(file);
                            }
                        }catch(Exception e){
                        e.printStackTrace();
                    }
                });
                    verticalhijos.getChildren().add(b1);
                } else{
                    LinkedList<BinaryTree<Directory>> nuevopadre = new LinkedList<BinaryTree<Directory>>();
                    HBox horizontalpadre = new HBox();
                    horizontalpadre.setSpacing(2);
                    nuevopadre.add(hijo);
                    //System.out.println("x:"+arbol.getRoot().getContent().getPeso());
                    crearcuadrados(horizontalpadre,nuevopadre, arbol, 2);
                    verticalhijos.getChildren().add(horizontalpadre);
                }
            
            
        }
        
        StackPane colores = new StackPane(verticalfuera);
        Scene scenecolors = new Scene(colores, 1000, 1000);
        stagecolor.setScene(scenecolors);
        stagecolor.show();
        
    }
    private void crearcuadrados2(HBox horizontalpadres,LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, int espacio) {
        for (BinaryTree<Directory> padre : padres) {
            double hije = padre.getRoot().getContent().getPeso();
            double padredouble = arbol.getRoot().getContent().getPeso();
            double ancho = (hije/padredouble)*1000;
            TextField nombre = nombredir(padre.getRoot().getContent().getName(),padre.getRoot().getContent().getPeso(),ancho);
            
            LinkedList<BinaryTree<Directory>> hijos = padre.getChildrens();
            VBox verticalhijos = new VBox();
            verticalhijos.getChildren().add(nombre);
            verticalhijos.setSpacing(espacio);
              
                    HashMap<String, Float> mapa = padre.getRoot().getContent().getColorespeso();
                    //System.out.println(mapa.size());
                    for (Map.Entry<String, Float> entry : mapa.entrySet()) {
                        Button b1 = new Button();
                        double hije1 = entry.getValue();
                        double padredouble1 = padre.getRoot().getContent().getPeso();
                        double ancho1 = (hije1/padredouble1)*1000;
                        //System.out.println(entry.getKey());
                        if(ancho1>0){
                        b1.setMaxSize(ancho1,ancho1);
                        }else{
                            b1.setMaxSize(100,100);
                        }
                        b1.setPrefHeight(ancho1);
                        b1.setStyle(padre.getRoot().getContent().colors(entry.getKey()));
                        verticalhijos.getChildren().add(b1);    
                    }
            horizontalpadres.getChildren().add(verticalhijos);
        }
    }
        

    private void crearcuadrados(HBox horizontalpadres,LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, int espacio) {
        for (BinaryTree<Directory> padre : padres) {
            double hije = padre.getRoot().getContent().getPeso();
            double padredouble = arbol.getRoot().getContent().getPeso();
            double ancho = (hije/padredouble)*1000;
            TextField nombre = nombredir(padre.getRoot().getContent().getName(),padre.getRoot().getContent().getPeso(),ancho);
            LinkedList<BinaryTree<Directory>> hijos = padre.getChildrens();
            VBox verticalhijos = new VBox();
            verticalhijos.getChildren().add(nombre);
            verticalhijos.setSpacing(espacio);
            HBox horizontal = new HBox();
            horizontal.setSpacing(10);
            horizontal.autosize();
            verticalhijos.autosize();
            for (BinaryTree<Directory> hijo : hijos) {
                
                if(hijo.isLeaf()){
                    
                    
                    Button b1 = new Button();
                    
                    double hije1 = hijo.getRoot().getContent().getPeso();
                    double padredouble1 = padre.getRoot().getContent().getPeso();
                    double ancho1 = (hije1/padredouble1)*1000;
                    b1.setStyle(hijo.getRoot().getContent().getColor());
                    b1.setMaxHeight(ancho1);
                    b1.setOnAction((event) -> {
                        try{
                            
                            File file = new File(hijo.getRoot().getContent().getDirectory());
                            if(!Desktop.isDesktopSupported()){
                            System.out.println("not supported");
                            }
                            Desktop desktop = Desktop.getDesktop();
                            if(file.exists()){
                                desktop.open(file);
                            }
                        }catch(Exception e){
                        e.printStackTrace();
                    }
                });
                    horizontal.getChildren().add(b1);
                } else{
                    LinkedList<BinaryTree<Directory>> nuevopadre = new LinkedList<BinaryTree<Directory>>();
                    HBox horizontalpadre = new HBox();
                    horizontalpadre.setSpacing(2);
                    nuevopadre.add(hijo);
                    crearcuadrados(horizontalpadre,nuevopadre, padre, 2);
                    verticalhijos.getChildren().add(horizontalpadre);
                }
            }
            verticalhijos.getChildren().add(horizontal);
            horizontalpadres.getChildren().add(verticalhijos);
        }
    }
    
    private TextField nombredir(String name, Long peso, double ancho) {
        TextField nombre = null;
        if(peso >= (1048576)){
        nombre = new TextField("."+name+"("+peso/(1024*1024)+"MB)");
        } else if(peso >= 1024){
        nombre = new TextField("."+name+"("+peso/(1024)+"KB)");
        } else{
        nombre = new TextField("."+name+"("+peso+"Byes)");    
        }
        
        nombre.setEditable(false);
        nombre.setAlignment(Pos.CENTER);
        nombre.setPrefWidth(peso);
        nombre.setStyle("-fx-background-color: #CB3FBE");
        return nombre;
    }

    private double factorpeso(long pesohijo,long pesopadre) {
        return (pesohijo/pesopadre);        
    }
    public String text(){
        String text = direccionField.getText();
        return text;
    }
    
}
