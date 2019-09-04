/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



/**
 *
 * @author Emanuel
 */
public class GUIController implements Initializable {
    
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
    private ImageView AndTwoImage;
    
    @FXML
    private ImageView OrTwoImage;
    
    @FXML
    private ImageView NotImage;
    
    @FXML
    private ImageView runIcon;
    
    @FXML
    private GridPane gridpane;
    
    @FXML
    private CheckMenuItem gridOption;
    
    @FXML
    private JFXCheckBox checkboxGrid;
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        buttons();
        gridDimensions();
        checkboxGrid.setSelected(true);
        //AndTwoImage.setOnMouseClicked(createAndTwo);
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
    
    @FXML
    void OnGateClicked(MouseEvent event) {
        if(event.getSource().equals(AndTwoImage)){
            System.out.println("AND");
            DrawGate draw = new DrawGate("C:\\Users\\Emanuel\\Desktop\\Circuit_Designer\\CircuitDesigner\\src\\resources\\images\\AND.png");
            leftpane.getChildren().add(draw.setImage());
        }
        else if(event.getSource().equals(OrTwoImage)){
            System.out.println("OR");
        }
        else{
            System.out.println("Ninguna");
        }
    }
    
    
    @FXML
    void newFile(ActionEvent event) {
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Emanuel\\Desktop\\Circuit_Designer\\CircuitDesigner\\src\\resources\\images\\AND.png"));
            ImageView imageGate = new ImageView(image);
            imageGate.setFitWidth(80);
            imageGate.setFitHeight(50);
            leftpane.getChildren().add(imageGate);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public AnchorPane getRoot(){
        return leftpane;
    }
    
    public AnchorPane getMain(){
        return mainAnchorPane;
    }
    
    public void sayHi(){
        System.out.println("Hi");
    }
    
    public void check(){
        checkboxGrid.setSelected(false);
    }
    
}
