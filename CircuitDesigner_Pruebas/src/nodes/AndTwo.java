/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import drawgate.MyCircle;
import linkedlist.LinkedList;
import linkedlist.MyObserver;

/**
 *
 * @author Emanuel
 */
public class AndTwo extends LogicGate {
    private static LinkedList <LogicGate> inputs = new LinkedList<>();
    private static LinkedList <LogicGate> outputs = new LinkedList<>();
    private MyObserver inputsObserver = new MyObserver();
    private MyObserver outputsObserver = new MyObserver();
    private MyCircle firstInput;
    private MyCircle secondInput;
    private MyCircle output;
    String something = "And Forever";
    
    public AndTwo(){
        this.firstInput = null;
        this.secondInput = null;
        this.output = null;
    }

    @Override
    public String foo() {
        return "Soy And";
    }

    @Override
    public void setFirstInput(MyCircle firstInput) {
        this.firstInput = firstInput;
    }

    @Override
    public MyCircle getFirstInput() {
        return firstInput;
    }

    @Override
    public void setSecondInput(MyCircle secondInput) {
        this.secondInput = secondInput;
    }

    @Override
    public MyCircle getSecondInput() {
        return secondInput;
    }

    @Override
    public void setOutput(MyCircle output) {
        this.output = output;
    }

    @Override
    public MyCircle getOutput() {
        output.saySomething(something);
        return output;
    }

    @Override
    public void operate() {
 
    }
     
}
