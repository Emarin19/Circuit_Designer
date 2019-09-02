/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 *
 * @author Emanuel
 */
public class GUIController extends AnchorPane implements Initializable {
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    @FXML
    private AnchorPane leftpane;
    
    @FXML
    private VBox rightpane;
 
    @FXML
    private JFXButton newFileButton;

    @FXML
    private JFXButton openFileButton;

    @FXML
    private JFXButton saveFileButton;

    @FXML
    private JFXButton saveasFileButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton helpButton;

    @FXML
    private JFXButton exitButton;
    
    @FXML
    private JFXButton runButton;

    @FXML
    private ImageView newFileIcon;

    @FXML
    private ImageView openFileIcon;

    @FXML
    private ImageView saveFileIcon;

    @FXML
    private ImageView saveasFileIcon;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private ImageView helpIcon;

    @FXML
    private ImageView exitIcon;
    
    @FXML
    private ImageView runIcon;
    
    @FXML
    private GridPane gridpane;
    
    @FXML
    private CheckMenuItem gridOption;
    
    @FXML
    private JFXCheckBox checkboxGrid;
    
    private AnchorPane mDragOverGate = null;
    private EventHandler gateDragOverRoot = null;
    private EventHandler gateDragDropped = null;
    private EventHandler gateDragOverLeftPane = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons();
        gridDimensions();
        checkboxGrid.setSelected(true);
    }
    
    private void buttons() {
        newFileButton.setGraphic(newFileIcon);
        openFileButton.setGraphic(openFileIcon);
        saveFileButton.setGraphic(saveFileIcon);
        saveasFileButton.setGraphic(saveasFileIcon);
        settingsButton.setGraphic(settingsIcon);
        runButton.setGraphic(runIcon);
        helpButton.setGraphic(helpIcon);
        exitButton.setGraphic(exitIcon);
    }
    
    private void gridDimensions() {
        for (int i = 0; i < 60; i++) {
            RowConstraints row = new RowConstraints(20);
            gridpane.getRowConstraints().add(row);
        }
        
        for(int j=0; j<60; j++){
            ColumnConstraints colum = new ColumnConstraints(20);
            gridpane.getColumnConstraints().add(colum);   
        }
    }
    
    private void addDragDetection(AnchorPane anchorpane){
        anchorpane.setOnDragDetected(new EventHandler <MouseEvent> (){
            @Override
            public void handle(MouseEvent event){
                mainAnchorPane.setOnDragOver(gateDragOverRoot);
                leftpane.setOnDragOver(gateDragOverLeftPane);
                leftpane.setOnDragDropped(gateDragDropped);
                
                AnchorPane anchor = (AnchorPane)event.getSource();
                mDragOverGate = anchor;
                mDragOverGate.relocateToPoint(new Point2D(event.getSceneX(),event.getSceneY()));
            }
        });
    }
    
    public void relocateToPoint (Point2D p){
        Point2D localCoords = new Point2D(getParent().sceneToLocal(p));
            
        
                
        relocate(
          (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
          (int) (localCoords.getY()) -(getBoundsInLocal().getHeight() / 2)
        );
    }

    @FXML
    void newFile(ActionEvent event) {
        System.out.println("Craeting a new file...");
    }
    
    @FXML
    void openFile(ActionEvent event) {
        System.out.println("Open file...");

    }

    @FXML
    void saveFile(ActionEvent event) {
        System.out.println("Saving file...");
    }

    @FXML
    void saveasFile(ActionEvent event) {
        System.out.println("Saving as file...");
    }

    @FXML
    void settings(ActionEvent event) {
        System.out.println("Opening settings option");
    }
    
    @FXML
    void about(ActionEvent event) {
        System.out.println("Aditional Information");
    }
    
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    void copy(ActionEvent event) {
        System.out.println("Coping...");
    }

    @FXML
    void cut(ActionEvent event) {
        System.out.println("Cutting");
    }
    
    @FXML
    void paste(ActionEvent event) {
        System.out.println("Pasting...");
    }
    
    @FXML
    void gridView(ActionEvent event) {
        if(gridOption.isSelected()){
            checkboxGrid.setSelected(true);
            gridpane.setVisible(true);
            System.out.println("Grid");
        }
        else{
            checkboxGrid.setSelected(false);
            gridpane.setVisible(false);
            System.out.println("No grid");
        }
    }
    
    @FXML
    void checkboxGridAction(ActionEvent event) {
        if(checkboxGrid.isSelected()){
            gridOption.setSelected(true);
            gridpane.setVisible(true);
        }
        else{
            gridOption.setSelected(false);
            gridpane.setVisible(false);
        }
    }
    
}
