
package cr.ac.tec.circuitdesigner.factory;

import java.io.Serializable;
import javafx.scene.image.ImageView;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel Marín
 */
public class GateImage extends ImageView implements Serializable{
  
    private LogicGate gate;
    
    public GateImage(String image) {
        
        if(image.equals("AND.png") || image.equals("ANDTHREE.png") || image.equals("ANDFOUR.png")){
            this.gate = Factory.setGate(GateType.AND);
        }
        if(image.equals("OR.png")){
            this.gate = Factory.setGate(GateType.OR);
        }
        if(image.equals("NOT.png")){
            this.gate = Factory.setGate(GateType.NOT);
        }
        if(image.equals("NAND.png")){
            this.gate = Factory.setGate(GateType.NAND);
        }
        if(image.equals("NOR.png")){
            this.gate = Factory.setGate(GateType.NOR);
        }
        if(image.equals("XOR.png")){
            this.gate = Factory.setGate(GateType.XOR);
        }
        if(image.equals("XNOR.png")){
            this.gate = Factory.setGate(GateType.XNOR);
        }
    }

    /**
     * @return the gate
     */
    public LogicGate getCurrentGate() {
        return gate;
    }
    
}
