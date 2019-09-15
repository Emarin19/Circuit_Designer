/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import circuitdesigner.Facade;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import linkedlist.LinkedList;
import nodes.LogicGate;
import drawgate.DecimalToBinary;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class TableViewController implements Initializable {
    
    @FXML
    private AnchorPane base;
    
    private LinkedList circuit;
    
    private LogicGate gate;
   
    private TableView<Values> table = new TableView();
    
    private ObservableList<Values> values = FXCollections.observableArrayList();
    
    private static int h = 1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTable();
    }

    private void createTable() {
        
        circuit = Facade.getCircuit();
       
        for(int i=0; i<circuit.getSize(); i++){
            
            gate = (LogicGate) circuit.getValue(i);
            TableColumn column = new TableColumn(gate.foo());
            
            if(gate.foo().equals("NOT")){
                TableColumn input = new TableColumn("In");
                TableColumn output = new TableColumn("Out");
                input.setMaxWidth(40);
                output.setMaxWidth(40);
                column.getColumns().addAll(input, output);
                table.getColumns().add(column);
            }
            else{
                if(gate.foo().equals("AND")){
                int ctd = 2; //Crear metodo en la clase abstracta para obtener la cantidad real de entreadas
                for(int j=0; j<ctd; j++){
                    TableColumn andInput = new TableColumn("In" + (j+1));
                    andInput.setCellValueFactory(new PropertyValueFactory<>("input" + (j+1)));
                    andInput.setMaxWidth(40);
                    column.getColumns().add(andInput);
                }
                TableColumn andOutput = new TableColumn("Out");
                andOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
                andOutput.setMaxWidth(40);
                column.getColumns().add(andOutput);
                table.getColumns().add(column);
                table.setItems(getValues(ctd));
                
                }
                if(gate.foo().equals("OR")){
                int ctd = 2; //Crear metodo en la clase abstracta para obtener la cantidad real de entreadas
                for(int j=0; j<ctd; j++){
                    TableColumn orInput = new TableColumn("In" + (j+1));
                    orInput.setCellValueFactory(new PropertyValueFactory<>("input" + (j+1)));
                    orInput.setMaxWidth(40);
                    column.getColumns().add(orInput);
                }
                TableColumn orOutput = new TableColumn("Out");
                orOutput.setCellValueFactory(new PropertyValueFactory<>("output"));
                orOutput.setMaxWidth(40);
                column.getColumns().add(orOutput);
                table.getColumns().add(column);
                table.setItems(getValues(ctd));
                
                }
            }
            //
        }
        base.getChildren().add(table);           
    }
    
    public ObservableList<Values> getValues(int entradas){
        
        int ctd = (int) Math.pow(2, entradas);
       
        int intA,intB,intC;
        for(int i=0; i<ctd; i++){
            ArrayList<Integer> inputs = DecimalToBinary.inputs(i, entradas);
            intA = inputs.get(1);
            intB = inputs.get(0);
            
            switch(intA){
                case 0:
                    gate.setFirstInput(false);
                    break;
                case 1:
                    gate.setFirstInput(true);
                    break;
            }
            
            switch(intB){
                case 0:
                    gate.setSecondInput(false);
                    break;
                case 1:
                    gate.setSecondInput(true);
                    break;
            }
            
            if(gate.getOutput()){
                intC = 1;
            }
            else{
                intC = 0;
            }
            
            values.add(new Values(intA,intB,intC));

        }
        return values;
    }
    
}
