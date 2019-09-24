/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner;

import cr.ac.tec.circuitdesigner.Facade;
import cr.ac.tec.circuitdesigner.pruebas.DecimalToBinary;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class TableViewController implements Initializable {
    
    @FXML
    private AnchorPane base;
    
    private LogicGate gate;
    
    private LinkedList<LogicGate> circuit;
    
    private LogicGate gateOutput;
    
    private ArrayList<String>cellDatas = new ArrayList<>();
    
    private TableView<ObservableList<String>> tableView = new TableView<>();
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableValues();
        createTable();
    }
    
    private void tableValues() {
        
        circuit = Facade.getCircuit();
        
        
        String values = "";
        int num_inputs = 0;
        int num_outputs = 0;
        int total_columns = 0;
        
        if(circuit.getSize()==1){
            gateOutput = circuit.getValue(0);
        }

        for(int i=0; i<circuit.getSize(); i++){
            gate = circuit.getValue(i);
            if(gate.getType().equals("2")){
                if(gate.getInputs().getSize() == 0){
                    num_inputs = num_inputs+2;
                }
                if(gate.getOutputs().getSize() == 0){
                    num_outputs = num_outputs+1;
                }
                if(gate.getInputs().getSize()!=0 && gate.getOutputs().getSize()==0){
                    gateOutput = gate;
                }
            }
        }
        
        int combinations = (int) Math.pow(2, num_inputs);
      
        for(int i=0; i<num_inputs; i++){
            values += "In" + (i+1) + ",";
        }
        
        for(int i=0; i<num_outputs; i++){
            values += "Out" + (i+1) + ",";
        }
        
        for(int i=0; i<combinations; i++){
            ArrayList<Integer> num_bin = DecimalToBinary.inputs(i, num_inputs);
            
            for(int j=num_bin.size()-1; j>=0; j--){
                values += String.valueOf(num_bin.get(j) + ",");
            }
            values += gateOutput.operate(num_bin);
            
        }
        
        total_columns = (num_inputs+num_outputs);
        
        System.out.println(values);
        cellDatas.add(values);
        cellDatas.add(String.valueOf(combinations));
        System.out.println(cellDatas.get(1));
        cellDatas.add(String.valueOf(total_columns));
        System.out.println(cellDatas.get(2));
    }

    private void createTable() {
        
        TableCreation Table  = new TableCreation(cellDatas.get(0));
        tableView = new TableView<>();
        
        List<String> columnNames = Table.getNext(Integer.parseInt(cellDatas.get(2)));
        
        for (int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                columnNames.get(i)
            );
            column.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
            );
            tableView.getColumns().add(column);
        }
        
        for (int i = 0; i < Integer.parseInt(cellDatas.get(1)); i++) {
            tableView.getItems().add(
                FXCollections.observableArrayList(
                        Table.getNext(Integer.parseInt(cellDatas.get(2)))
                )
            );
        }
        
        base.getChildren().add(tableView);
 
    }    

       
}