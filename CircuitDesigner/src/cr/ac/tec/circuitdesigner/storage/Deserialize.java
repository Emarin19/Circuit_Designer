
package cr.ac.tec.circuitdesigner.storage;

import cr.ac.tec.circuitdesigner.draw.Builder;
import cr.ac.tec.circuitdesigner.linkedlist.LinkedList;
import java.io.Serializable;

/**
 *
 * @author Emanuel Marín
 */
public class Deserialize implements Serializable {
    private final LinkedList<Serialize> circuit;
    private static Serialize node;

    public Deserialize(LinkedList<Serialize> circuit){
        this.circuit = circuit;
        deserializeCircuit();
    }

    private void deserializeCircuit() {
        for(int i=0; i<circuit.getSize(); i++){
            node = circuit.getValue(i);
            Builder build = new Builder(Integer.parseInt(node.getType()));
        }
    }
    
    public static Serialize serial(){
        return node;
    }
    
}
