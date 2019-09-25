/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner;

import cr.ac.tec.circuitdesigner.draw.DrawGate;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import cr.ac.tec.circuitdesigner.linkedlist.ListObserver;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel Marín
 */
public class Facade {
    private static LinkedList<LogicGate> circuit12 = new LinkedList<>();
    private ListObserver circuitObserver12 = new ListObserver();
    
    public Facade(String image){
        if(circuit12.countObservers() == 0){
            circuit12.addObserver(circuitObserver12);
        }
        /*if(image.equals("NOT.png")){
            DrawGate draw = new DrawGate(image);
            draw.setNot();
            circuit12.add(draw.getGate());
        }
        else{
            DrawGate draw = new DrawGate(image);
            draw.setGate();
            circuit12.add(draw.getGate());
        }*/
    }
   

    /**
     * @return the circuit
     */
    public static LinkedList<LogicGate> getCircuit() {
        return circuit12;
    }
      
}
