/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import drawgate.DecimalToBinary;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import static javafx.application.Application.launch;
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
        
        /*ArrayList<Integer> inputs = DecimalToBinary.inputs(3, 2);
        for(int i=inputs.size()-1; i>=0; i--){
            System.out.print(inputs.get(i));
        }*/

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
