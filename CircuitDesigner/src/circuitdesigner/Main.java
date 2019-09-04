/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.setTitle("Circuit Designer");
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        maincontroller = new GUIController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        loader.setController(maincontroller);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static GUIController maincontroller;
    
    public static GUIController getController(){
        return maincontroller;
    }
    
}
