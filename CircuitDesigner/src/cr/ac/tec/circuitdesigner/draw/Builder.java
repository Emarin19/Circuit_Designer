
package cr.ac.tec.circuitdesigner.draw;

import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import cr.ac.tec.circuitdesigner.linkedlist.ListObserver;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel Marín
 */
public class Builder {
    private static LinkedList<LogicGate> circuit = new LinkedList<>();
    private ListObserver circuitObserver = new ListObserver();
    
    public Builder(String image, int num){
        if(circuit.countObservers() == 0){
            circuit.addObserver(circuitObserver);
        }
        
        DrawGate draw = new DrawGate(image,num);
        draw.setGate();
        circuit.add(draw.getGate());
        
    }
    
    public Builder(boolean value){
        DrawValue draw = new DrawValue();
        draw.setValue(value);
    }
    
    public static void newCircuit(){
        circuit = new LinkedList<>();
    }
    
        
    public Builder(int num){
        DrawGate draw = new DrawGate(num);
        draw.setAgainGate();
        circuit.add(draw.getGate());
    }

    /**
     * @return the circuit
     */
    public static LinkedList<LogicGate> getCircuit() {
        return circuit;
    }
    
}
