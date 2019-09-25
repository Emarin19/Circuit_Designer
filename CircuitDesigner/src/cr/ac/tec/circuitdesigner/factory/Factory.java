
package cr.ac.tec.circuitdesigner.factory;

import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.nodes.And;
import cr.ac.tec.circuitdesigner.nodes.Nand;
import cr.ac.tec.circuitdesigner.nodes.Nor;
import cr.ac.tec.circuitdesigner.nodes.Not;
import cr.ac.tec.circuitdesigner.nodes.Or;
import cr.ac.tec.circuitdesigner.nodes.Xnor;
import cr.ac.tec.circuitdesigner.nodes.Xor;

/**
 *
 * @author Emanuel Marín
 */
public class Factory {
    public static LogicGate setGate(GateType g){
        switch (g) {
            case AND:
                return new And();
            case OR:
                return new Or();
            case NOT:
                return new Not();
            case NAND:
                return new Nand();
            case NOR:
                return new Nor();
            case XOR:
                return new Xor();
            case XNOR:
                return new Xnor();
            default:
                return null;
        }
    }
}
