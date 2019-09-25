
package cr.ac.tec.circuitdesigner;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Emanuel Marín
 */
public class Main extends Application {
    
    private static Stage window;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Main.window = stage;
        stage.setTitle("Circuit Designer");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        loader.setController(controller);
        Scene scene = (new Scene(loader.load(),992,643));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }
    
    static GUIController controller = new GUIController();
    
    public static GUIController getController(){
        return controller;
    }
    
    public static Stage getStage(){
        return window;
    }
 
}
