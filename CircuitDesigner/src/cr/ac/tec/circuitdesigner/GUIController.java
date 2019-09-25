
package cr.ac.tec.circuitdesigner;

//Same project package
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;

//Java package
import java.net.URL;
import java.util.ResourceBundle;

//Jfoenix package
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.tec.circuitdesigner.draw.Builder;
import cr.ac.tec.circuitdesigner.draw.DrawGate;
//import cr.ac.tec.circuitdesigner.storage.Deserialization;
//import cr.ac.tec.circuitdesigner.storage.Serialization;
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
import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.storage.Deserialize;
import cr.ac.tec.circuitdesigner.storage.Serialize;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    private Desktop desktop;
    
       
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
            choices.add("TWO");
            choices.add("THREE");
            choices.add("FOUR");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("TWO", choices);
            dialog.setTitle("Choice Dialog");
            dialog.setHeaderText("Select the number of inputs");
            dialog.setContentText("INPUTS:");

            //Obtener la respuesta
            Optional<String> result = dialog.showAndWait();
            int num = 0;
            if (result.isPresent()){
                switch(result.get()){
                    case "TWO":
                        num = 2;
                        break;
                    case "THREE":
                        num = 3;
                        break;
                    case "FOUR":
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
            System.out.println("here");
            if(gateOutputs.getSize() == 1){
                gate = (LogicGate) gateOutputs.getValue(0);
                Boolean value = gate.getOutput();
                System.out.println(value);
                if(value == null){
                    message.setText("No has asignado valores de verdad");
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
            
            }catch(Exception e){
                message.setText("Could not load the table, check the connections between the gates");
                message.setUnFocusColor(Color.RED);
            } 
        }
    }
    
    @FXML
    void newFile(ActionEvent event) {
        //LogicGate gate = Facade.getCircuit().getValue(0);
        System.out.println(gate.getGateImage().getX());
        System.out.println(gate.getGateImage().getY());
        System.out.println(gate);
        System.out.println(gate.getGateImage());
        //System.out.println(gate.getImage());
       //System.out.println(gate.getFirst());
        //System.out.println("Hola " + gate.getFirst().getCenterX());
        //System.out.println("Hola2 " + gate.getFirst().getCenterY());
        //System.out.println(gate.getSecond());
        //System.out.println(gate.getOut());
        
        System.out.println("Craeting a new file...");
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
    void saveasFile(ActionEvent event) {
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
        DrawGate draw = new DrawGate("Prueba.png",2);
        //draw.setPrueba("Prueba.png");
        System.out.println("Coping...");
    }

    @FXML
    void cut(ActionEvent event) {
        System.out.println("Cutting");
    }
    
    @FXML
    void paste(ActionEvent event) {
       //deserializeNode();
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

    private void deserializeNode() {
        LinkedList<Serialize> readCircuit;
        //Serialize serial;
        try{
            FileInputStream file = new FileInputStream("Save.txt");
            ObjectInputStream object = new ObjectInputStream(file);
            readCircuit = (LinkedList<Serialize> ) object.readObject();
            Deserialize des = new Deserialize(readCircuit);
            /*serial = readCircuit.getValue(0);
            System.out.println(serial.getName());
            System.out.println(serial.getType());
            System.out.println("Node");
            System.out.println(serial.getNodeX());
            System.out.println(serial.getNodeY());
            System.out.println("FirstCircle");
            System.out.println(serial.getFirstCircleX());
            System.out.println(serial.getFirstCircleY());
            //System.out.println("Exito");*/
            System.out.println("Deserialization completed");
            
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error");          
        }
    }

}
