/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import linkedlist.LinkedList;
import linkedlist.MyObserver;
import nodes.LogicGate;
import nodes.AndTwo;
import nodes.OrTwo;
import nodes.NandTwo;
import nodes.NorTwo;
import nodes.XnorTwo;
import nodes.XorTwo;

/**
 *
 * @author Emanuel
 */
public class Gate extends ImageView {
  
    private LogicGate gate;
    
    public Gate(String image) {
        
        if(image.equals("ANDTWO.png")){
            this.gate = new AndTwo();
        }
        if(image.equals("ORTWO.png")){
            this.gate = new OrTwo();
        }
        if(image.equals("Not.png")){
            System.out.println("No todavia");
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
        if(image.equals("AND.png")){
            this.gate = new AndTwo();
        }
        
        if(image.equals("OR.png")){
            this.gate = new OrTwo();
        }
    }

    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }
    
}
