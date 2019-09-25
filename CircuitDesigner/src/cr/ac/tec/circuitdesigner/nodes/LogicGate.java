/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.nodes;

import cr.ac.tec.circuitdesigner.factory.GateImage;
import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import java.io.Serializable;
import java.util.ArrayList;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;

/**
 *
 * @author Emanuel
 */
public abstract class LogicGate implements Serializable {

    public abstract String getName();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    public abstract String getType();
    public abstract void setType(String type);
    
    public abstract void setGateImage(GateImage gateImage);
    public abstract GateImage getGateImage();
    
    public abstract void setCircle(String circleType, LogicCircle circle);
    public abstract LogicCircle getCircle(String circleType);
    
    //Delete
    public abstract String operate(ArrayList inputs);
    public abstract void operate();
    
    public abstract LinkedList getInputsReferences();
    public abstract LinkedList getOutputsReferences();
    
    public abstract void setInput_Output(String type, Boolean value);
    public abstract Boolean getInput_Output(String type);
    
    //Delete
    public abstract void setFirstInput(Boolean firstInput);
    public abstract Boolean getFirstInput();
    
    public abstract void setSecondInput(Boolean secondInput);
    public abstract Boolean getSecondInput();
    
    public abstract void setOutput(Boolean output);
    public abstract Boolean getOutput();
    
    public abstract void setValue(String type, Boolean value);
    public abstract Boolean getValue(String type);

}
