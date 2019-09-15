/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import drawgate.MyCircle;
import linkedlist.LinkedList;
import linkedlist.MyObserver;

/**
 *
 * @author Emanuel
 */
public class OrTwo extends LogicGate{
    
    private static LinkedList <LogicGate> inputs = new LinkedList<>();
    private static LinkedList <LogicGate> outputs = new LinkedList<>();
    private MyObserver inputsObserver = new MyObserver();
    private MyObserver outputsObserver = new MyObserver();
    
    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean output;
    
    public OrTwo(){
        
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
        return "OR";
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
        this.secondInput = second;
    }

    @Override
    public Boolean getSecondInput() {
       return secondInput;
    }

    @Override
    public void setOutput(Boolean out) {
        this.output = out;
    }

    @Override
    public Boolean getOutput() {
        if(firstInput == null || secondInput == null){
            return null;
        }
        else{
            return (firstInput || secondInput);
        }
        
        //return (getFirstInput()||getSecondInput());
    }
    
    @Override
    public void setValue(String type, Boolean value) {
        if(type.equals("Salida")){
            this.output = value;
        }
        if(type.equals("FirstInput")){
            this.firstInput = value;
        }
        if(type.equals("SecondInput")){
            this.secondInput = value;
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
        if(type.equals("SecondInput")){
            result = getSecondInput();
        }
        return result;
    }

    @Override
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
    @Override
    public LinkedList <LogicGate> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(LinkedList <LogicGate> outputs) {
        this.outputs = outputs;
    }
    
}
