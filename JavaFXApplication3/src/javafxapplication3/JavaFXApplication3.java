/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication3;

import TDA.BinaryTree;
import directory.Directory;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alex_
 */
public class JavaFXApplication3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox h1 = new VBox();
        Insets padd = new Insets(35, 35, 35, 35);
        h1.setPadding(padd);
        TextField direccionField = new TextField("Dirección");
        Button btn = new Button();
        
        btn.setText("Buscar");
        btn.setOnAction((ActionEvent event) -> {
            Directory d1 = new Directory();
            BinaryTree<Directory> arbol = d1.cargarubicacion(direccionField.getText());
            LinkedList<BinaryTree<Directory>>lista = arbol.getChildrens();
            System.out.println("---------------------------");
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
    
}
