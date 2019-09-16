/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import drawgate.DrawGate;
import linkedlist.LinkedList;
import linkedlist.MyObserver;
import nodes.LogicGate;

/**
 *
 * @author Emanuel
 */
public class Facade {
    private static LinkedList<LogicGate> circuit = new LinkedList<>();
    private MyObserver circuitObserver = new MyObserver();
    
    public Facade(String image){
        if(circuit.countObservers() == 0){
            circuit.addObserver(circuitObserver);
        }
        if(image.equals("NOT.png")){
            DrawGate draw = new DrawGate(image);
            draw.setNot();
            circuit.add(draw.gate());
        }
        else{
            DrawGate draw = new DrawGate(image);
            draw.setGate();
            circuit.add(draw.gate());
        }
    }
    
    public Facade(Boolean value){
        DrawGate draw = new DrawGate();
        draw.setBooleanValue(value);
    }

    /**
     * @return the circuit
     */
    public static LinkedList<LogicGate> getCircuit() {
        return circuit;
    }
      
}
