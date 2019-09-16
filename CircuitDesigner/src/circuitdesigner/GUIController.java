/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import drawgate.DrawGate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import linkedlist.LinkedList;


/**
 *
 * @author Emanuel
 */
public class GUIController implements Initializable {
    
    @FXML
    AnchorPane base;
    static int i = 0;
    @FXML
    private AnchorPane leftpane;
 
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
    private Circle True;

    @FXML
    private Circle False;
    
    @FXML
    private Text Text1;

    @FXML
    private Text Text2;
    
    @FXML
    private ImageView AndTwo;

    @FXML
    private ImageView OrTwo;

    @FXML
    private ImageView Not;

    @FXML
    private ImageView NandTwo;

    @FXML
    private ImageView NorTwo;

    @FXML
    private ImageView XorTwo;

    @FXML
    private ImageView XnorTwo;
    
    @FXML
    private ImageView runIcon;
    
    @FXML
    private GridPane gridpane;
    
    @FXML
    private CheckMenuItem gridOption;
    
    @FXML
    private JFXCheckBox checkboxGrid;
    
    private LinkedList circuit;    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        buttons();
        gridDimensions();
        Text1.setMouseTransparent(true);
        Text2.setMouseTransparent(true);
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
    
    @FXML
    void OnGateClicked(MouseEvent event) {
        if(event.getSource().equals(True)){
            Facade facade = new Facade(true);
        }
        else if(event.getSource().equals(False)){
            Facade facade = new Facade(false);
        }
        else if(event.getSource().equals(AndTwo)){
            Facade facade = new Facade("ANDTWO.png");
        }
        else if(event.getSource().equals(OrTwo)){
            Facade facade = new Facade("ORTWO.png");
        }
        else if(event.getSource().equals(Not)){
            Facade facade = new Facade("NOT.png");
        }
        else if(event.getSource().equals(NandTwo)){
            Facade facade = new Facade("NANDTWO.png");
        }
        else if(event.getSource().equals(NorTwo)){
            Facade facade = new Facade("NORTWO.png");
        }
        else if(event.getSource().equals(XorTwo)){
            Facade facade = new Facade("XORTWO.png");
        }
        else if(event.getSource().equals(XnorTwo)){
            Facade facade = new Facade("XNORTWO.png");
        }
        else{
            return;
        }
    }
    
    @FXML
    void generateTable(ActionEvent event) {
        circuit = Facade.getCircuit();
        if(circuit.getSize() == 0){
            System.out.println("Lista vacia");
        }
        else{
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("True Table");
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.show();
            
            }catch(Exception e){
                System.out.println("Can´t load FXML");
            } 
        }
    }
    
    
    @FXML
    void newFile(ActionEvent event) {   
        System.out.println("Craeting a new file...");
    }
    
    public ArrayList<Integer> genereteRow(int i){
        ArrayList<Integer> list = new ArrayList<>();
        if(i == 1){
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0); 
            return list;
        }
        if(i == 2){
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(1);
            return list;
        }
        else{
            return list;
        }
    }
    
    @FXML
    void openFile(ActionEvent event) {
        AnchorPane prueba = new AnchorPane();
        prueba.setPrefSize(80, 50);
        prueba.setStyle("-fx-background-color: #9c9c9c;");
        leftpane.getChildren().add(prueba);
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Emanuel\\Desktop\\Circuit_Designer\\CircuitDesigner\\src\\resources\\images\\AND.png"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Open file...");

    }

    @FXML
    void saveFile(ActionEvent event) {
        TableView<String> table = new TableView<String>();

        TableColumn columnOne = new TableColumn("One");
        TableColumn columnTwo = new TableColumn("Two");

        table.getColumns().addAll(columnOne, columnTwo);

        columnOne.setCellValueFactory(c ->new SimpleStringProperty(new String("1")));
        columnTwo.setCellValueFactory(c ->new SimpleStringProperty(new String("2")));
        table.getItems().addAll("");
        
        

        table.getItems().addAll("Column one's data");
        leftpane.getChildren().add(table);
    }

    @FXML
    void saveasFile(ActionEvent event) {
        System.out.println("Saving as file...");
        Circle circle = new Circle(10);
        circle.setLayoutX(50);
        circle.setLayoutY(50);
        circle.setFill(Color.BLUE);
        leftpane.getChildren().add(circle);
    }

    @FXML
    void settings(ActionEvent event) {
        System.out.println("Opening settings option");
    }
    
    @FXML
    void about(ActionEvent event) {
        
        System.out.println(Facade.getCircuit().getValue(0));
        System.out.println(Facade.getCircuit().getValue(0).getFirstInput());
        System.out.println(Facade.getCircuit().getValue(0).getSecondInput());
        System.out.println(Facade.getCircuit().getValue(0).getOutput());
        
        System.out.println("Aditional Information");
        
    }
    
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    void copy(ActionEvent event) {
        System.out.println(Facade.getCircuit().getSize());
        System.out.println("Coping...");
    }

    @FXML
    void cut(ActionEvent event) {
        Text text = new Text();
        text.setText("Hola");
        text.setFill(Color.BLACK);
        text.setFont(Font.font(20));
        text.setFont(Font.font("Swis721 BT"));
        text.setX(50);
        text.setY(50);
        leftpane.getChildren().add(text);
        
        //Facade.getCircuit().getValue(i).setOutput(output);
        System.out.println("Cutting");
    }
    
    @FXML
    void paste(ActionEvent event) {
        Circle circle = new Circle(5);
        circle.setCenterX(50);
        circle.setCenterY(50);
        circle.setOnMouseClicked(e->{
            if(e.getButton() == MouseButton.SECONDARY){
                leftpane.getChildren().remove(circle);
            }
        });
        leftpane.getChildren().add(circle);
        
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

    public void sayHi(){
        System.out.println("Hi");
    }
    
    public void check(){
        checkboxGrid.setSelected(false);
    }

    
}
