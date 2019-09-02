/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Emanuel
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new AnchorPane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        stage.setScene(scene);
        stage.setTitle("I'm Tab x"); 
        stage.show();
        //Parent root = FXMLLoader.load(getClass().getResource("MainController.fxml"));
        
        //Scene scene = new Scene(root);
        
        //stage.setScene(scene);
        //stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
