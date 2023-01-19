/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

import TDA.BinaryTree;
import directory.Directory;
import java.awt.Desktop;
import java.io.File;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button btnpro = new Button("Treemap");
        btn.setText("Buscar");
        btn.setAlignment(Pos.CENTER);
        //btn.setLayoutY(150);
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
            System.out.println(padres.size());
            Colorear(padres, arbol,hijos);
            
        });
        
        StackPane root = new StackPane();
        
        direccionField.setAlignment(Pos.CENTER);
        btn.setAlignment(Pos.CENTER);
        
        h1.getChildren().add(direccionField);
        h1.getChildren().add(btn);
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

    
    
    private void Colorear(LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, LinkedList<BinaryTree<Directory>> hijos) {
        Stage stagecolor = new Stage();
        
        HBox horizontalpadres = new HBox();
        VBox verticalfuera = new VBox();
        TextField nombre = nombredir(arbol.getRoot().getContent().getName(),arbol.getRoot().getContent().getPeso(),1000);
        verticalfuera.setSpacing(1);
        verticalfuera.getChildren().add(nombre);
        verticalfuera.getChildren().add(horizontalpadres);
        
        crearcuadrados(horizontalpadres,padres,arbol,10);
        for (BinaryTree<Directory> hijo : hijos) {
            
            
        }
        
        StackPane colores = new StackPane(verticalfuera);
        Scene scenecolors = new Scene(colores, 1000, 1000);
        stagecolor.setScene(scenecolors);
        stagecolor.show();
        
    }

    

    private void crearcuadrados(HBox horizontalpadres,LinkedList<BinaryTree<Directory>> padres, BinaryTree<Directory> arbol, int espacio) {
        for (BinaryTree<Directory> padre : padres) {
            int ancho = (int) ((padre.getRoot().getContent().getPeso()/arbol.getRoot().getContent().getPeso())/padres.size());
            TextField nombre = nombredir(padre.getRoot().getContent().getName(),padre.getRoot().getContent().getPeso(),ancho);
            LinkedList<BinaryTree<Directory>> hijos = padre.getChildrens();
            VBox verticalhijos = new VBox();
            verticalhijos.getChildren().add(nombre);
            verticalhijos.setSpacing(espacio);
            for (BinaryTree<Directory> hijo : hijos) {
                
                if(hijo.isLeaf()){
                    
                    Button b1 = new Button();
                    long dimension = (long) factorpeso(hijo.getRoot().getContent().getPeso(),arbol.getRoot().getContent().getPeso());
                    b1.setMaxSize(dimension,dimension);
                    b1.setStyle(hijo.getRoot().getContent().getColor());
                    b1.setOnAction((event) -> {
                        try{
                            
                            File file = new File(hijo.getRoot().getContent().getDirectory());
                            if(!Desktop.isDesktopSupported()){
                            System.out.println("not supported");
                            }
                            Desktop desktop = Desktop.getDesktop();
                            if(file.exists()){
                                System.out.println("xd");
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
                    horizontalpadre.setSpacing(5);
                    nuevopadre.add(hijo);
                    System.out.println("x:"+padre.getRoot().getContent().getPeso());
                    crearcuadrados(horizontalpadre,nuevopadre, padre, 2);
                    verticalhijos.getChildren().add(horizontalpadre);
                }
            }
            
            horizontalpadres.getChildren().add(verticalhijos);
        }
    }
    
    private TextField nombredir(String name, Long peso, float ancho) {
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
        System.out.println(ancho);
        nombre.setMaxSize(150, 100);
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
