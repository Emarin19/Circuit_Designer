
package cr.ac.tec.circuitdesigner.linkedlist;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;



/**
 *
 * @author Emanuel Marin
 */
public class ListObserver implements Observer, Serializable {
    @Override
    public void update(Observable o, Object arg) {
       System.out.println("Una nueva compuerta ha sido agregado a la lista " + arg.getClass());
    }
    
}
