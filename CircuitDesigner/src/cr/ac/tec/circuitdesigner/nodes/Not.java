
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
    
    private final LinkedList <LogicGate> inputsReferences = new LinkedList<>();
    private final LinkedList <LogicGate> outputsReferences = new LinkedList<>();

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
        LogicGate gate = inputsReferences.getValue(0);
        String value = null;
        switch(gate.getName()){
            case "AND":
                value = gate.operate(inputs);
                if(value.equals("1,")){
                    value = "0,";
                }
                else{
                    value = "1,";
                }
                break;
            case "OR":
                value = gate.operate(inputs);
                if(value.equals("1,")){
                    value = "1,";
                }
                else{
                    value = "0,";
                }
                break;
                
        }
        return value;

    }
    
    @Override
    public void operate(String type) {
        Boolean first = getFirstInput();
        Boolean second = getSecondInput();
        
        if(first == null){
            this.output = null;
        }
        if (first){
            this.output = false;
        }
        else{
            this.output = true;
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
            case "FirstInput":
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
            case "FirstInput":
                this.input = value;
                break;
            case "Output":
                this.output = value;
                break;
        }
        
    }

    @Override
    public Boolean getInput_Output(String type) {
        switch(type){
            case "FirstInput":
                return input;
            case "Output":
                return output;
            default:
                return null;
        }
    }

    
}
