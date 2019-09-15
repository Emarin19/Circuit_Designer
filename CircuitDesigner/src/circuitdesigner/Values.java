/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author Emanuel
 */
public class Values {
    private SimpleIntegerProperty input1;
    private SimpleIntegerProperty input2;
    private SimpleIntegerProperty output;
 
    Values(int input1, int input2, int output) {
        
        this.input1 = new SimpleIntegerProperty(input1);
        this.input2 = new SimpleIntegerProperty(input2);
        this.output = new SimpleIntegerProperty(output);
    }
 
    /**
     * @return the fisrtInput
     */
    public int getInput1() {
        return input1.get();
    }

    /**
     * @param fisrtInput the fisrtInput to set
     */
    public void setInput1(int input1) {
        this.input1.set(input1);
    }

    /**
     * @return the secondInput
     */
    public int getInput2() {
        return input2.get();
    }

    /**
     * @param secondInput the secondInput to set
     */
    public void setInput2(int input2) {
        this.input2.set(input2);
    }

    /**
     * @return the output
     */
    public int getOutput() {
        return output.get();
    }

    /**
     * @param output the output to set
     */
    public void setOutput(int output) {
        this.output.set(output);
    }
    
    
}
