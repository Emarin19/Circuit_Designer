/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.T;
import javafx.stage.Stage;
import linkedlist.LinkedList;
import linkedlist.MyObserver;
import nodes.AndTwo;
import nodes.LogicGate;
import nodes.Not;
import nodes.OrTwo;

/**
 *
 * @author Emanuel
 */
public class Main extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        AndTwo and = null;
        OrTwo or = null;
        stage.setTitle("Circuit Designer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        loader.setController(controller);
        Scene scene = (new Scene(loader.load()));
        stage.setScene(scene);
        stage.show();
        
    }
    
    static GUIController controller = new GUIController();
    
    public static GUIController getController(){
        return controller;
    }
 
}
