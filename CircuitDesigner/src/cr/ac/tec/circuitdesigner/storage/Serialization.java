/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.storage;

import cr.ac.tec.circuitdesigner.factory.GateImage;
import cr.ac.tec.circuitdesigner.draw.LogicCircle;
import java.io.Serializable;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel
 */
public class Serialization implements Serializable {
    private String image;
    private GateImage gateImageView;
    private LogicGate gate;
    private LogicCircle first;
    private LogicCircle second;
    private LogicCircle out;
    
    private double ImageX;
    private double ImageY;
    
    public Serialization(){
        
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the first
     */
    public LogicCircle getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(LogicCircle first) {
        this.first = first;
    }

    /**
     * @return the second
     */
    public LogicCircle getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(LogicCircle second) {
        this.second = second;
    }

    /**
     * @return the out
     */
    public LogicCircle getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(LogicCircle out) {
        this.out = out;
    }

    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }

    /**
     * @param gate the gate to set
     */
    public void setGate(LogicGate gate) {
        this.gate = gate;
    }

    /**
     * @return the gateImageView
     */
    public GateImage getGateImageView() {
        return gateImageView;
    }

    /**
     * @param gateImageView the gateImageView to set
     */
    public void setGateImageView(GateImage gateImageView) {
        this.gateImageView = gateImageView;
    }

    /**
     * @return the ImageX
     */
    public double getImageX() {
        return ImageX;
    }

    /**
     * @param ImageX the ImageX to set
     */
    public void setImageX(double ImageX) {
        this.ImageX = ImageX;
    }

    /**
     * @return the ImageY
     */
    public double getImageY() {
        return ImageY;
    }

    /**
     * @param ImageY the ImageY to set
     */
    public void setImageY(double ImageY) {
        this.ImageY = ImageY;
    }
    
    
}
