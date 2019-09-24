/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.nodes;

import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import java.util.ArrayList;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import cr.ac.tec.circuitdesigner.linkedlist.MyObserver;

/**
 *
 * @author Emanuel
 */
public class NandTwo extends LogicGate {
    
    private LinkedList <LogicGate> inputs = new LinkedList<>();
    private LinkedList <LogicGate> outputs = new LinkedList<>();
    
    private LogicCircle first;
    private LogicCircle second;
    private LogicCircle out;

    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean output;
    
    public NandTwo(){
        
        this.firstInput = null;
        this.secondInput = null;
        this.output = null;
        
    }

    @Override
    public String foo() {
        return "NAND";
    }
    
    @Override
    public String getType() {
        return "2";
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
            Boolean result = (firstInput && secondInput);
            if(result){
                return false;
            }
            else{
                return true;
            }
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
    
    @Override
    public void operate() {
        Boolean first = getFirstInput();
        Boolean second = getSecondInput();
        
        if(first == null || second == null){
            this.output = null;
        }
        else{
            this.output = (first&&second);
        }
    }

    @Override
    public void setImage(String image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFirstCircle(LogicCircle first) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSecondCircle(LogicCircle first) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutCircle(LogicCircle first) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogicCircle getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogicCircle getSecond() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LogicCircle getOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Gate(cr.ac.tec.circuitdesigner.draw.GateImage gateImage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cr.ac.tec.circuitdesigner.draw.GateImage getGateImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
