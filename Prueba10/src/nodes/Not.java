/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import drawgate.MyCircle;
import java.util.ArrayList;
import linkedlist.LinkedList;
import linkedlist.MyObserver;

/**
 *
 * @author Emanuel
 */
public class Not extends LogicGate{
   private LinkedList <LogicGate> inputs = new LinkedList<>();
    private LinkedList <LogicGate> outputs = new LinkedList<>();
    private MyObserver inputsObserver = new MyObserver();
    private MyObserver outputsObserver = new MyObserver();
    
    private MyCircle first;
    private MyCircle second;
    private MyCircle out;

    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean output;
    
    public Not(){
        this.firstInput = null;
        this.secondInput = null;
        this.output = null;
        if(inputs.countObservers() == 0){
            inputs.addObserver(inputsObserver);
        }
        if(outputs.countObservers() == 0){
            outputs.addObserver(outputsObserver);
        }
    }

    @Override
    public String foo() {
        return "NOT";
    }
    
    @Override
    public String getType() {
        return "1";
    }

    @Override
    public void setFirstInput(Boolean first) {
        this.firstInput = first;
    }

    @Override
    public Boolean getFirstInput() {
        return firstInput;
    }

    @Override
    public void setSecondInput(Boolean second) {
        this.secondInput = null;
    }

    @Override
    public Boolean getSecondInput() {
       return null;
    }

    @Override
    public void setOutput(Boolean out) {
        this.output = out;
    }

    @Override
    public Boolean getOutput() {
        if(firstInput == null){
            return null;
        }
        if(firstInput){
            return false;
        }
        else{
            return true;
        }
    }
    
    @Override
    public void setValue(String type, Boolean value) {
        if(type.equals("Salida")){
            this.output = value;
        }
        if(type.equals("FirstInput")){
            this.firstInput = value;
        }
    }

    @Override
    public Boolean getValue(String type) {
        Boolean result = null;
        if(type.equals("Salida")){
            result = getOutput();
        }
        if(type.equalsIgnoreCase("FirstInput")){
            result = getFirstInput();
        }
        return result;
    }

    /**
     * @return the inputs
     */
    public LinkedList <LogicGate> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(LinkedList <LogicGate> inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the outputs
     */
    public LinkedList <LogicGate> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(LinkedList <LogicGate> outputs) {
        this.outputs = outputs;
    }
    
    
    
    @Override
    public String operate(ArrayList inputs) {
        
        Boolean finalValue = false;
        int first = (int) inputs.get(0);
        if(first == 1){
            finalValue = true;
        }
        else{
            finalValue = false;
        }
        
        for(int i=1; i<inputs.size(); i++){
            int value = (int) inputs.get(i);
            if(value == 1){
                finalValue = finalValue&&true;
            }
            else{
                finalValue = finalValue&&false;
            }
        }
        
        if(finalValue){
            return "1,";
        }
        else{
            return "0,";
        }
        
    }
    
}
