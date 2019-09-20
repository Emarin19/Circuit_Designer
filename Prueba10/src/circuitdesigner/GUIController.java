/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

//Same project package
import linkedlist.LinkedList;

//Java package
import java.net.URL;
import java.util.ResourceBundle;

//Jfoenix package
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

//JavaFX package
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


/**
 *
 * @author Emanuel Marín
 */
public class GUIController implements Initializable {
    
    @FXML
    private AnchorPane base;
    
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
    private JFXCheckBox checkboxGrid;
    
    @FXML
    private JFXTextField message;

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
    private Circle True;

    @FXML
    private Circle False;
    
    @FXML
    private Text Text1;

    @FXML
    private Text Text2;
    
    @FXML
    private GridPane gridpane;
    
    @FXML
    private CheckMenuItem gridOption;
    
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
        message.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                message.setPrefColumnCount(message.getText().length());
            }
        });
        message.setText("Welcome to Circuit Designer");
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
        for (int i = 0; i < 500; i++) {
            RowConstraints row = new RowConstraints(20);
            gridpane.getRowConstraints().add(row);
        }
        
        for(int j=0; j<500; j++){
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
            message.setText("AND Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("ANDTWO.png");
        }
        else if(event.getSource().equals(OrTwo)){
            message.setText("OR Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("ORTWO.png");
        }
        else if(event.getSource().equals(Not)){
            message.setText("NOT Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("NOT.png");
        }
        else if(event.getSource().equals(NandTwo)){
            message.setText("NAND Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("NANDTWO.png");
        }
        else if(event.getSource().equals(NorTwo)){
            message.setText("NOR Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("NORTWO.png");
        }
        else if(event.getSource().equals(XorTwo)){
            message.setText("XOR Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
            Facade facade = new Facade("XORTWO.png");
        }
        else if(event.getSource().equals(XnorTwo)){
            message.setText("XNOR Gate");
            message.setUnFocusColor(Color.web("#1aef86"));
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
            message.setText("Circuit has not been created");
            message.setUnFocusColor(Color.RED);
        }
        else{
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("True Table");
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                message.setText("");
                message.setUnFocusColor(Color.WHITE);
                stage.show();
            
            }catch(Exception e){
                message.setText("Could not load the table, check the connections between the gates");
                message.setUnFocusColor(Color.RED);
            } 
        }
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
        System.out.println("Saving file");
    }

    @FXML
    void saveasFile(ActionEvent event) {
        System.out.println("Save as file");
    }

    @FXML
    void settings(ActionEvent event) {
        System.out.println("Opening settings option");
    }
    
    @FXML
    void about(ActionEvent event) {
        System.out.println(Facade.getCircuit().getSize());
        for(int i=0; i<Facade.getCircuit().getSize(); i++){
            System.out.println(Facade.getCircuit().getValue(i));
        }
        System.out.println("Information"); 
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
        System.out.println("Pasting");
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
    
    public JFXTextField getMessage(){
        return message;
    }
    
    public void check(){
        checkboxGrid.setSelected(false);
    }

}
