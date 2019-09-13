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
public abstract class LogicGate {
    public abstract String foo();
    public abstract void setFirstInput(Boolean firstInput);
    public abstract Boolean getFirstInput();
    public abstract void setSecondInput(Boolean secondInput);
    public abstract Boolean getSecondInput();
    public abstract void setOutput(Boolean output);
    public abstract Boolean getOutput();
    public abstract void operate();

}
