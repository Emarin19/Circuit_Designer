/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Emanuel
 */
public class Prueba2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new AnchorPane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabPaneRootView.fxml"));
        scene.setRoot(loader.load());
        TabPaneRootController controller = loader.getController();
        controller.myInit(); //my init-Methode 
        primaryStage.setScene(scene);
        primaryStage.setTitle("I'm Tab x"); 
        primaryStage.show();
    }

    public static void main(String[] args) {            
        launch(args);
    }  
    
}
