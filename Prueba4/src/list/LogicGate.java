/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

/**
 *
 * @author Emanuel
 */
public abstract class LogicGate {
    public abstract void setType(String type);
    public abstract String getType();
    public abstract void setFirstInput(boolean firstInput);
    public abstract boolean getFirstInput();
    public abstract void setSecondInput(boolean secondInput);
    public abstract boolean getSecondInput();
    public abstract boolean getOutput();
    //public abstract void operate();
}
