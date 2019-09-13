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
import nodes.AndTwo;
import nodes.LogicGate;
import nodes.OrTwo;

/**
 *
 * @author Emanuel
 */
public class Gate extends ImageView {
  
    private LogicGate gate;
    
    public Gate(String image) {
        
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
