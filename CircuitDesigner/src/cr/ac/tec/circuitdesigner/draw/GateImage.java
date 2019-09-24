/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.draw;

import java.io.Serializable;
import javafx.scene.image.ImageView;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.nodes.AndTwo;
import cr.ac.tec.circuitdesigner.nodes.OrTwo;
import cr.ac.tec.circuitdesigner.nodes.NandTwo;
import cr.ac.tec.circuitdesigner.nodes.NorTwo;
import cr.ac.tec.circuitdesigner.nodes.Not;
import cr.ac.tec.circuitdesigner.nodes.XnorTwo;
import cr.ac.tec.circuitdesigner.nodes.XorTwo;

/**
 *
 * @author Emanuel
 */
public class GateImage extends ImageView implements Serializable{
  
    private LogicGate gate;
    
    public GateImage(String image) {
        
        if(image.equals("ANDTWO.png")){
            this.gate = new AndTwo();
        }
        if(image.equals("ORTWO.png")){
            this.gate = new OrTwo();
        }
        if(image.equals("NOT.png")){
            this.gate = new Not();
        }
        if(image.equals("NANDTWO.png")){
            this.gate = new NandTwo();
        }
        if(image.equals("NORTWO.png")){
            this.gate = new NorTwo();
        }
        if(image.equals("XORTWO.png")){
            this.gate = new XorTwo();
        }
        if(image.equals("XNORTWO.png")){
            this.gate = new XnorTwo();
        }
    }

    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }
    
}
