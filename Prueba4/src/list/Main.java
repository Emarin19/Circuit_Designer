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
public class Main {
    public static void main(String[] args){
        LogicGate logicGate = new AndTwo();
        logicGate.setFirstInput(false);
        logicGate.setSecondInput(true);
        System.out.println("AND " + logicGate.getOutput());
        
        LogicGate logicOr = new OrTwo();
        logicOr.setFirstInput(false);
        logicOr.setSecondInput(false);
        System.out.println("Or " + logicOr.getOutput());
        
        System.out.println("");
        
        LogicGate And = new AndTwo();
        LogicGate Or = new OrTwo();
        
        And.setFirstInput(true);
        And.setSecondInput(false);
        Or.setFirstInput(And.getOutput());
        Or.setSecondInput(false);
        System.out.println("Resultado: " + Or.getOutput());
        
    }
    
}
