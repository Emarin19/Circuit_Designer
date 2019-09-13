/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba6;

/**
 *
 * @author Emanuel
 */
public class Not {
    private Boolean input;
    private Boolean output;
    
    public Not(){
        this.input = null;
        this.output = null;
    }

    /**
     * @return the input
     */
    public Boolean getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(Boolean input) {
        this.input = input;
    }

    /**
     * @return the output
     */
    public Boolean getOutput() {
        if (getInput() == true){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @param output the output to set
     */
    public void setOutput(Boolean output) {
        this.output = output;
    }
    
}
