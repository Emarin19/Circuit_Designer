/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import drawgate.MyCircle;
import linkedlist.LinkedList;

/**
 *
 * @author Emanuel
 */
public class OrTwo extends LogicGate{
    private static LinkedList <LogicGate> inputs = new LinkedList<>();
    private static LinkedList <LogicGate> outputs = new LinkedList<>();
    private MyCircle firstInput;
    private MyCircle secondInput;
    private MyCircle output;
    String some = "Aleluya";
    
    public OrTwo(){
        this.firstInput = null;
        this.secondInput = null;
        this.output = null;
    }

    @Override
    public String foo() {
        return "Soy Or";
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
        output.saySomething(some);
        return output;
    }

    @Override
    public void operate() {
        
    }
    
}
