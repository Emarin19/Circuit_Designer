
package cr.ac.tec.circuitdesigner;

//Same project package
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import cr.ac.tec.circuitdesigner.draw.Builder;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.storage.Deserialize;
import cr.ac.tec.circuitdesigner.storage.Serialize;

//Java package
import java.net.URL;
import java.util.ResourceBundle;

//Jfoenix package
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;

/**
 *
 * @author Emanuel Marín
 */
public class GUIController implements Initializable {
    
    @FXML
    private JFXButton newFileButton,openFileButton,saveFileButton,saveasFileButton,settingsButton,helpButton,exitButton,runButton;
    
    @FXML
    private ImageView newFileIcon,openFileIcon,saveFileIcon,saveasFileIcon,settingsIcon,helpIcon,exitIcon,runIcon;
    
    @FXML
    private ImageView AND,OR,NOT,NAND,NOR,XOR,XNOR;
    
    @FXML
    private JFXCheckBox checkboxGrid;
    
    @FXML
    private CheckMenuItem gridOption;
    
    @FXML
    private JFXTextField message;
    
    @FXML
    private GridPane gridpane;
    
    @FXML
    private AnchorPane pane;
 
    @FXML
    private Circle True,False;
    
    @FXML
    private Text Text1,Text2;
    
    private LinkedList circuit;
    
    private LogicGate gate;
    
       
    /**
     * Initializes the controller class.
     * @param rb
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        buttons(); 
        gridDimensions();
        Text1.setMouseTransparent(true);
        Text2.setMouseTransparent(true);
        checkboxGrid.setSelected(true);
        message.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            message.setPrefColumnCount(message.getText().length());
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
        for (int i = 0; i<500; i++) {
            RowConstraints row = new RowConstraints(15);
            gridpane.getRowConstraints().add(row);
        }
        
        for(int j=0; j<500; j++){
            ColumnConstraints colum = new ColumnConstraints(15);
            gridpane.getColumnConstraints().add(colum);   
        }
    }
    
    @FXML
    void OnValueClicked(MouseEvent event) {
        if(event.getSource().equals(True)){
            Builder builder = new Builder(true);
        }
        else if(event.getSource().equals(False)){
            Builder builder = new Builder(false);
        }
    }
    
    @FXML
    void OnNotClicked(MouseEvent event) {
        if(event.getSource().equals(NOT)){
            message.setText("NOT Gate");
            message.setUnFocusColor(Color.web("#1AEF86"));
            Builder build = new Builder("NOT.png",1);
        }
    }
    
    @FXML
    void OnGateClicked(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY){
            List<String> choices = new ArrayList<>();
            choices.add("Two");
            choices.add("Three");
            choices.add("Four");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Two", choices);
            dialog.setTitle("Choice Dialog");
            dialog.setHeaderText("Select the number of inputs");
            dialog.setContentText("Inputs:");

            //Obtener la respuesta
            Optional<String> result = dialog.showAndWait();
            int num = 0;
            if (result.isPresent()){
                switch(result.get()){
                    case "Two":
                        num = 2;
                        break;
                    case "Three":
                        num = 3;
                        break;
                    case "Four":
                        num = 4;
                        break;
                }
            }
            loadGate(event,num);
        }
        
        else{
            if(event.getSource().equals(AND)){
                message.setText("AND Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("AND.png",2);
            }
            else if(event.getSource().equals(OR)){
                message.setText("OR Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("OR.png",2);
            }
            else if(event.getSource().equals(NAND)){
                message.setText("NAND Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("NAND.png",2);
            }
            else if(event.getSource().equals(NOR)){
                message.setText("NOR Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("NOR.png",2);
            }
            else if(event.getSource().equals(XOR)){
                message.setText("XOR Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("XOR.png",2);
            }
            else if(event.getSource().equals(XNOR)){
                message.setText("XNOR Gate");
                message.setUnFocusColor(Color.web("#1AEF86"));
                Builder build = new Builder("XNOR.png",2);
            }
            else{
            }
        }
        
    }
    
    @FXML
    void runCircuit(ActionEvent event) {
        circuit = Facade.getCircuit();
        if(circuit.isEmpty()){
            message.setText("Circuit has not been created");
            message.setUnFocusColor(Color.RED);
        }
        else{
            LinkedList gateOutputs = new LinkedList();
            gateOutputs = verification();
            if(gateOutputs.getSize() == 1){
                gate = (LogicGate) gateOutputs.getValue(0);
                Boolean value = gate.getOutput();
                System.out.println(value);
                if(value == null){
                    message.setText("");
                    message.setUnFocusColor(Color.RED);
                }
            
                else if(value == false){
                    message.setText("The value is False");
                    message.setUnFocusColor(Color.web("#1aef86"));
                }
            
                else if(value){
                    message.setText("The value is True");
                    message.setUnFocusColor(Color.web("#1aef86"));
                }
                else{
                    
                }
            }
        
            else{
                System.out.println("Es aqui");
                for(int i=0; i<gateOutputs.getSize(); i++){
                    String values;
                    try{
                    
                    }catch (Exception e){
                        message.setText("Circuit has not been created");
                        message.setUnFocusColor(Color.RED);
                    }
                }
            }
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
            
            }catch(IOException e){
                message.setText("Could not load the table, check the connections between the gates");
                message.setUnFocusColor(Color.RED);
            } 
        }
    }
    
    @FXML
    void newFile(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("New File");
        alert.setContentText("Are you sure to create a new File?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            pane.getChildren().clear();
            Builder.newCircuit();
            pane.getChildren().add(gridpane);  
        } 
    }
    
    @FXML
    void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("TXT", "*.txt"),
            new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        File file = fileChooser.showOpenDialog(Main.getStage());
        if(file!=null){
            LinkedList<Serialize> readCircuit;
            try{
                FileInputStream readFile = new FileInputStream(file);
                ObjectInputStream object = new ObjectInputStream(readFile);
                readCircuit = (LinkedList<Serialize> ) object.readObject();
                Deserialize des = new Deserialize(readCircuit);
                message.setText("");  
            }catch(IOException | ClassNotFoundException e){
                message.setText("The circuit cannot be loaded");
                message.setUnFocusColor(Color.RED);         
            }
        }
    }

    @FXML
    void saveFile(ActionEvent event) {
        circuit = Builder.getCircuit();
        if(circuit.isEmpty()){
            message.setText("Circuit has not been created");
            message.setUnFocusColor(Color.RED);
        }
        else{
            LinkedList<Serialize> toSave = new LinkedList<>();
            for(int i=0; i<circuit.getSize(); i++){
                Serialize serial = new Serialize((LogicGate) circuit.getValue(i));
                toSave.add(serial);
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save circuit");
            File saveFile = fileChooser.showSaveDialog(Main.getStage());
            if(saveFile != null){
                try{
                    FileOutputStream file = new FileOutputStream(saveFile + ".txt");
                    ObjectOutputStream object = new ObjectOutputStream(file);
                    object.writeObject(toSave);
                    object.close();
                    file.close();
                }catch(IOException e){}
            }
        }
    }

    @FXML
    void settings(ActionEvent event) {
       
    }
    
    @FXML
    void about(ActionEvent event) {
        System.out.println(Builder.getCircuit().getSize());
        for(int i=0; i<Builder.getCircuit().getSize(); i++){
            System.out.println(Builder.getCircuit().getValue(i));
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
    
    public AnchorPane getPane(){
        return pane;
    }
    
    public JFXTextField getMessage(){
        return message;
    }
    
    public void check(){
        checkboxGrid.setSelected(false);
    }

    private LinkedList verification() {
        LinkedList gateOutputs = new LinkedList();
        if(circuit.getSize()==1){
            gate = (LogicGate) circuit.getValue(0);
            gateOutputs.add(gate);
        }
        else{
            for(int i=0; i<circuit.getSize(); i++){
                gate = (LogicGate) circuit.getValue(i);
                if(gate.getInputsReferences().getSize()!=0 && gate.getOutputsReferences().getSize()==0){
                    gateOutputs.add(gate);
                }
            }
            
        }
        return gateOutputs;
    }

    private void loadGate(MouseEvent event, int num) {
        System.out.println(num);
        switch(num){
            case 2:
                if(event.getSource().equals(AND)){
                    System.out.println("Here");
                    message.setText("AND Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("AND.png",2);
                    break;
                }
                else if(event.getSource().equals(OR)){
                    message.setText("OR Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("OR.png",2);
                    break;
                }
                else if(event.getSource().equals(NAND)){
                    message.setText("NAND Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("NAND.png",2);
                }
                else if(event.getSource().equals(NOR)){
                    message.setText("NOR Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("NOR.png",2);
                    break;
                }
                else if(event.getSource().equals(XOR)){
                    message.setText("XOR Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("XOR.png",2);
                    break;
                }
                else if(event.getSource().equals(XNOR)){
                    message.setText("XNOR Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("XNOR.png",2);
                    break;
                }
                else{
                    break;
                }
            
            case 3:
                if(event.getSource().equals(AND)){
                    message.setText("AND Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("ANDTHREE.png",3);
                    break;
                }
            
            case 4:
                if(event.getSource().equals(AND)){
                    message.setText("AND Gate");
                    message.setUnFocusColor(Color.web("#1AEF86"));
                    Builder build = new Builder("ANDFOUR.png",4);
                    break;
                }
        }
    }
}
