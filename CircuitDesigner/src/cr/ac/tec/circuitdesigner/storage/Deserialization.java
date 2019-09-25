/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.storage;

import cr.ac.tec.circuitdesigner.Facade;
import cr.ac.tec.circuitdesigner.Main;
import cr.ac.tec.circuitdesigner.factory.GateImage;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel Marín
 */
public class Deserialization {
    
    private String image;
    private Image gateImage;
    private GateImage gate;
    private Serialization serial;
    
    public Deserialization(Serialization serial){
        this.serial = serial;
        
    }
    
    public void doWork(){
        
        gateImage = new Image(serial.getImage());
        gate = new GateImage(serial.getImage());
        gate.setImage(gateImage);
        gate.setFitWidth(95);
        gate.setFitHeight(50);

        gate.setX(serial.getImageX());
        gate.setY(serial.getImageY());
        
        //Main.getController().getRoot().getChildren().addAll(gate);

    }
    
    
}
