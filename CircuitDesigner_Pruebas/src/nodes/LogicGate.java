/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import drawgate.MyCircle;

/**
 *
 * @author Emanuel
 */
public abstract class LogicGate {
    public abstract String foo();
    public abstract void setFirstInput(MyCircle firstInput);
    public abstract MyCircle getFirstInput();
    public abstract void setSecondInput(MyCircle secondInput);
    public abstract MyCircle getSecondInput();
    public abstract void setOutput(MyCircle output);
    public abstract MyCircle getOutput();
    public abstract void operate();

}
