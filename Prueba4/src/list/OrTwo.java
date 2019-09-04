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
public class OrTwo extends LogicGate{
    
    private String type;
    private boolean firstInput;
    private boolean secondInput;
    private boolean output;
    
    public OrTwo(){
        
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setFirstInput(boolean firstInput) {
         this.firstInput = firstInput;
    }

    @Override
    public boolean getFirstInput() {
        return firstInput;
    }

    @Override
    public void setSecondInput(boolean secondInput) {
        this.secondInput = secondInput;
    }

    @Override
    public boolean getSecondInput() {
        return secondInput;
    }

    @Override
    public boolean getOutput() {
        boolean first = getFirstInput();
        boolean second = getSecondInput(); 
        
        output = (first||second); 
        return output;
    }
    
}
