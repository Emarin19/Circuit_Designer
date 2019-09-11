/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import linkedlist.LinkedList;
import linkedlist.MyObserver;
import nodes.AndTwo;
import nodes.LogicGate;
import nodes.OrTwo;

/**
 *
 * @author Emanuel
 */
public class Gate extends ImageView {
    
    private static LinkedList<LogicGate> inputs = new LinkedList<>();
    private static LinkedList<LogicGate> outputs = new LinkedList<>();
    private MyObserver inputsObserver = new MyObserver();
    private MyObserver outputsObserver = new MyObserver();
    private LogicGate gate;
    

    public Gate(String image) {
        
        //inputs.addObserver(inputsObserver);
        //outputs.addObserver(outputsObserver);        
        
        if(image.equals("AND.png")){
            this.gate = new AndTwo();
        }
        
        if(image.equals("OR.png")){
            this.gate = new OrTwo();
        }
    }

    /**
     * @return the inputs
     */
    public static LinkedList<LogicGate> getInputs() {
        return inputs;
    }

    /**
     * @param aInputs the inputs to set
     */
    public static void setInputs(LinkedList<LogicGate> aInputs) {
        inputs = aInputs;
    }

    /**
     * @return the outputs
     */
    public static LinkedList<LogicGate> getOutputs() {
        return outputs;
    }

    /**
     * @param aOutputs the outputs to set
     */
    public static void setOutputs(LinkedList<LogicGate> aOutputs) {
        outputs = aOutputs;
    }

    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }
    
}
