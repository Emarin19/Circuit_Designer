/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.nodes;

import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import cr.ac.tec.circuitdesigner.factory.GateImage;
import java.util.ArrayList;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;


/**
 *
 * @author Emanuel Marín
 */
public class Nand extends LogicGate {
    
    private GateImage gateImageView;
    private String type;
    
    private final LinkedList <LogicGate> inputsReferences = new LinkedList<>();
    private final LinkedList <LogicGate> outputsReferences = new LinkedList<>();

    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean thirdInput;
    private Boolean fourthInput;
    private Boolean output;
   
    private LogicCircle firstCircle;
    private LogicCircle secondCircle;
    private LogicCircle thirdCircle;
    private LogicCircle fourthCircle;
    private LogicCircle outputCircle;
    
    public Nand(){
        
        this.firstInput = null;
        this.secondInput = null;
        this.thirdInput = null;
        this.fourthInput = null;
        this.output = null;
        this.type = "2";
        
    }

    @Override
    public String getName() {
        return "NAND";
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public void setType(String type) {
        this.type = type;
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
            return "0,";
        }
        else{
            return "1,";
        }
        
    }
    
    @Override
    public void operate(String type) {
        Boolean first = getFirstInput();
        Boolean second = getSecondInput();
        
        if(first == null || second == null){
            this.output = null;
        }
        else{
            if(first&&second){
                this.output = false;
            }
            else{
                this.output = true;
            }
            
        }
    }

    @Override
    public GateImage getGateImage() {
        return gateImageView;
    }

    @Override
    public void setGateImage(GateImage gateImage) {
        this.gateImageView = gateImage;
    }

    @Override
    public void setCircle(String circleType, LogicCircle circle) {
        switch(circleType){
            case "FirstInput":
                this.firstCircle = circle;
            case "SecondInput":
                this.secondCircle = circle;
            case "ThirdInput":
                this.thirdCircle = circle;
            case "FourthInput":
                this.fourthCircle = circle;
            case "Output":
                this.outputCircle = circle;
        }
    }

    @Override
    public LogicCircle getCircle(String circleType) {
        switch(circleType){
            case "FirstInput":
                return firstCircle;
            case "SecondInput":
                return secondCircle;
            case "ThirdCircle":
                return thirdCircle;
            case "fourthInput":
                return fourthCircle;
            case "Output":
                return outputCircle;
            default:
                return null;
        }
    }

    @Override
    public LinkedList getInputsReferences() {
        return inputsReferences;
    }

    @Override
    public LinkedList getOutputsReferences() {
        return outputsReferences;
    }

    @Override
    public void setInput_Output(String type, Boolean value) {
        switch(type){
            case "FirstInput":
                this.firstInput = value;
            case "SecondInput":
                this.secondInput = value;
            case "ThirdInput":
                this.thirdInput = value;
            case "FourthInput":
                this.fourthInput = value;
            case "Output":
                this.output = value;
        }
        
    }

    @Override
    public Boolean getInput_Output(String type) {
        switch(type){
            case "FirstInput":
                return firstInput;
            case "SecondInput":
                return secondInput;
            case "ThirdInput":
                return thirdInput;
            case "FourthInput":
                return fourthInput;
            case "Output":
                return output;
            default:
                return null;
        }
    }
}
