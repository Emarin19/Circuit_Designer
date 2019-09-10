/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import linkedlist.LinkedList;

/**
 *
 * @author Emanuel
 */
public class AndTwo extends LogicGate {
    private static LinkedList <LogicGate> inputs = new LinkedList<>();
    private static LinkedList <LogicGate> outputs = new LinkedList<>();
    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean output;
    
    public AndTwo(){
        this.firstInput = null;
        this.secondInput = null;
        this.output = output;
    }

    @Override
    public String foo() {
        return "Soy And";
    }

    @Override
    public void setFirstInput(Boolean firstInput) {
        this.firstInput = firstInput;
    }

    @Override
    public Boolean getFirstInput() {
        return firstInput;
    }

    @Override
    public void setSecondInput(Boolean secondInput) {
        this.secondInput = secondInput;
    }

    @Override
    public Boolean getSecondInput() {
        return secondInput;
    }

    @Override
    public void setOutput(Boolean output) {
        this.output = output;
    }

    @Override
    public Boolean getOutput() {
        return (getFirstInput()&&getSecondInput());
    }

    @Override
    public void operate() {
        Boolean first = getFirstInput();
        Boolean second = getSecondInput();
        
        if(first == null){
            
        }
    }
    
    
}
