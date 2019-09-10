/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.Observable;
import java.util.Observer;
import nodes.AndTwo;
import nodes.LogicGate;
import nodes.OrTwo;


/**
 *
 * @author Emanuel
 */
public class MyObserver implements Observer {
    AndTwo and;
    OrTwo or;

    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("Una nueva compuerta ha sido agregado a la lista");
        
    }
    
}
