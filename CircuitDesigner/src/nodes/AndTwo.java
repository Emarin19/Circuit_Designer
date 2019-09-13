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
    private LinkedList <LogicGate> inputs = new LinkedList<>();
    private LinkedList <LogicGate> outputs = new LinkedList<>();
    private MyObserver inputsObserver = new MyObserver();
    private MyObserver outputsObserver = new MyObserver();
    
    private MyCircle first;
    private MyCircle second;
    private MyCircle out;

    private Boolean firstInput;
    private Boolean secondInput;
    private Boolean output;
    
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
    public void setFirstInput(Boolean first) {
        this.firstInput = first;
    }

    @Override
    public Boolean getFirstInput() {
        return firstInput;
    }

    @Override
    public void setSecondInput(Boolean second) {
        this.secondInput = second;
    }

    @Override
    public Boolean getSecondInput() {
       return secondInput;
    }

    @Override
    public void setOutput(Boolean out) {
        this.output = out;
    }

    @Override
    public Boolean getOutput() {
        return output;
    }
    
    @Override
    public void setValue(String type, Boolean value) {
        if(type.equals("Salida")){
            this.output = value;
        }
        if(type.equals("FirstInput")){
            this.firstInput = value;
        }
        if(type.equals("SecondInput")){
            this.secondInput = value;
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
        if(type.equals("SecondInput")){
            result = getSecondInput();
        }
        return result;
    }

    @Override
    public void setFirst(MyCircle circle) {
        this.first = circle;
    }
    
    @Override
    public void setSecond(MyCircle circle) {
        this.second = circle;
    }
    
    @Override
    public void setOut(MyCircle circle) {
        this.out = circle;
    }

    @Override
    public MyCircle getCircle(String type) {
        if(type.equals("Salida")){
            return this.out;
        }
        else if(type.equals("FirstInput")){
            return this.first;
        }
        else if(type.equals("SecondInput")){
            return this.second;
        }
        else{
            return null;
        }
    }  

}
