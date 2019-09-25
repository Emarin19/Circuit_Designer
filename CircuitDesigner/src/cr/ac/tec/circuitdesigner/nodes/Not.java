
package cr.ac.tec.circuitdesigner.nodes;

import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import cr.ac.tec.circuitdesigner.factory.GateImage;
import java.util.ArrayList;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;


/**
 *
 * @author Emanuel Marín
 */
public class Not extends LogicGate{
    
    private GateImage gateImageView;
    private String type;
    
    private LinkedList <LogicGate> inputsReferences = new LinkedList<>();
    private LinkedList <LogicGate> outputsReferences = new LinkedList<>();

    private Boolean input;
    private Boolean second;
    private Boolean output;
   
    private LogicCircle firstCircle;
    private LogicCircle outputCircle;
    
    public Not(){
        
        this.input = null;
        this.output = null;
        this.type = "1";

    }

    @Override
    public String getName() {
        return "NOT";
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
        this.input = first;
    }

    @Override
    public Boolean getFirstInput() {
        return input;
    }

    @Override
    public void setSecondInput(Boolean second) {
        this.second = null;
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
        if(input == null){
            return null;
        }
        if(input){
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
            this.input = value;
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
            case "Output":
                this.outputCircle = circle;
        }
    }

    @Override
    public LogicCircle getCircle(String circleType) {
        switch(circleType){
            case "Input":
                return firstCircle;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
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
            case "Input":
                this.input = value;
            case "Output":
                this.output = value;
        }
        
    }

    @Override
    public Boolean getInput_Output(String type) {
        switch(type){
            case "Input":
                return input;
            case "Output":
                return output;
            default:
                return null;
        }
    }

    
}
