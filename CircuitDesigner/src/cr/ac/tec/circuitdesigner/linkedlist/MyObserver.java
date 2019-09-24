/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.linkedlist;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import cr.ac.tec.circuitdesigner.nodes.AndTwo;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.nodes.OrTwo;


/**
 *
 * @author Emanuel
 */
public class MyObserver implements Observer, Serializable {
    AndTwo and;
    OrTwo or;

    @Override
    public void update(Observable o, Object arg) {
       
       System.out.println("Una nueva compuerta ha sido agregado a la lista " + arg.getClass());
        
    }
    
}
