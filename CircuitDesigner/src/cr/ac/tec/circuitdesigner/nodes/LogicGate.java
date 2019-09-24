/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.nodes;

import cr.ac.tec.circuitdesigner.draw.GateImage;
import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import java.io.Serializable;
import java.util.ArrayList;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;

/**
 *
 * @author Emanuel
 */
public abstract class LogicGate implements Serializable {
    
    private String name;
    public abstract String foo();
    public abstract String getType();
    public abstract String operate(ArrayList inputs);
    public abstract void operate();
    
    public abstract void setImage(String image);
    public abstract String getImage();
    
    public abstract void Gate(GateImage gateImage);
    public abstract GateImage getGateImage();
    
    public abstract void setFirstCircle(LogicCircle first);
    public abstract void setSecondCircle(LogicCircle first);
    public abstract void setOutCircle(LogicCircle first);
    
    public abstract LogicCircle getFirst();
    public abstract LogicCircle getSecond();
    public abstract LogicCircle getOut();
    
    
    public abstract LinkedList getInputs();
    public abstract LinkedList getOutputs();
    
    public abstract void setFirstInput(Boolean firstInput);
    public abstract Boolean getFirstInput();
    
    public abstract void setSecondInput(Boolean secondInput);
    public abstract Boolean getSecondInput();
    
    public abstract void setOutput(Boolean output);
    public abstract Boolean getOutput();
    
    public abstract void setValue(String type, Boolean value);
    public abstract Boolean getValue(String type);

}
