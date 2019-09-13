/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba6;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Emanuel
 */
public class MyObserver implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Una nueva compuerta ha sido agregada " + ((LogicGate)arg));
    }
    
}
