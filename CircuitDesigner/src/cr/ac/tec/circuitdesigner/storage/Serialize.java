
package cr.ac.tec.circuitdesigner.storage;

import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import java.io.Serializable;
import javafx.geometry.Bounds;

/**
 *
 * @author Emanuel Marín
 */
public class Serialize implements Serializable {
    private final LogicGate node;
    private String type;
    private String name;
    private double nodeX;
    private double nodeY;
    private double firstCircleX;
    private double firstCircleY;
    private double secondCircleX;
    private double secondCircleY;
    private double thirdCircleX;
    private double thirdCircleY;
    private double fourthCircleX;
    private double fourthCircleY;
    private double outputCircleX;
    private double outputCircleY;
    
    public Serialize(LogicGate node){
        this.node = node;
        serializeNode();
        
    }

    private void serializeNode() {
        type = node.getType();
        name = node.getName();
        Bounds boundsInScene = node.getGateImage().localToScene(node.getGateImage().getBoundsInLocal());
        nodeX = boundsInScene.getMinX()-3;
        nodeY = boundsInScene.getMinY()-61;
        
        
        switch(type){
            case "1":
                Bounds In1 = node.getCircle("FirstInput").getBoundsInParent();
                firstCircleX = In1.getMinX()+2.5;
                firstCircleY = In1.getMinY()+5;
                Bounds Out1 = node.getCircle("Output").getBoundsInParent();
                outputCircleX= Out1.getMinX()+7.5;
                outputCircleY = Out1.getMinY()+5;
                break;
            case "2":
                Bounds In2 = node.getCircle("FirstInput").getBoundsInParent();
                firstCircleX = In2.getMinX()+2.5;
                firstCircleY = In2.getMinY()+5;
                Bounds In22 = node.getCircle("SecondInput").getBoundsInParent();
                secondCircleX = In22.getMinX()+2.5;
                secondCircleY = In22.getMinY()+5;
                Bounds Out2 = node.getCircle("Output").getBoundsInParent();
                outputCircleX= Out2.getMinX()+7.5;
                outputCircleY = Out2.getMinY()+5;
                break;
            case "3":
                Bounds In3 = node.getCircle("FirstInput").getBoundsInParent();
                firstCircleX = In3.getMinX()+2.5;
                firstCircleY = In3.getMinY()+5;
                Bounds In33 = node.getCircle("SecondInput").getBoundsInParent();
                secondCircleX = In33.getMinX()+2.5;
                secondCircleY = In33.getMinY()+5;
                Bounds In333 = node.getCircle("ThirdInput").getBoundsInParent();
                thirdCircleX = In333.getMinX()+2.5;
                thirdCircleY = In333.getMinY()+5;
                Bounds Out3 = node.getCircle("Output").getBoundsInParent();
                outputCircleX= Out3.getMinX()+7.5;
                outputCircleY = Out3.getMinY()+5;
                break;
            case "4":
                Bounds In4 = node.getCircle("FirstInput").getBoundsInParent();
                firstCircleX = In4.getMinX()+2.5;
                firstCircleY = In4.getMinY()+5;
                Bounds In44 = node.getCircle("SecondInput").getBoundsInParent();
                secondCircleX = In44.getMinX()+2.5;
                secondCircleY = In44.getMinY()+5;
                Bounds In444 = node.getCircle("ThirdInput").getBoundsInParent();
                thirdCircleX = In444.getMinX()+2.5;
                thirdCircleY = In444.getMinY()+5;
                Bounds In4444 = node.getCircle("FourthInput").getBoundsInParent();
                fourthCircleX = In4444.getMinX()+2.5;
                fourthCircleY = In4444.getMinY()+5;
                Bounds Out4 = node.getCircle("Output").getBoundsInParent();
                outputCircleX= Out4.getMinX()+7.5;
                outputCircleY = Out4.getMinY()+5;
                break;  
        }
    }

    /**
     * @return the node
     */
    public LogicGate getNode() {
        return node;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nodeX
     */
    public double getNodeX() {
        return nodeX;
    }

    /**
     * @param nodeX the nodeX to set
     */
    public void setNodeX(double nodeX) {
        this.nodeX = nodeX;
    }

    /**
     * @return the nodeY
     */
    public double getNodeY() {
        return nodeY;
    }

    /**
     * @param nodeY the nodeY to set
     */
    public void setNodeY(double nodeY) {
        this.nodeY = nodeY;
    }

    /**
     * @return the firstCircleX
     */
    public double getFirstCircleX() {
        return firstCircleX;
    }

    /**
     * @param firstCircleX the firstCircleX to set
     */
    public void setFirstCircleX(double firstCircleX) {
        this.firstCircleX = firstCircleX;
    }

    /**
     * @return the firstCircleY
     */
    public double getFirstCircleY() {
        return firstCircleY;
    }

    /**
     * @param firstCircleY the firstCircleY to set
     */
    public void setFirstCircleY(double firstCircleY) {
        this.firstCircleY = firstCircleY;
    }

    /**
     * @return the secondCircleX
     */
    public double getSecondCircleX() {
        return secondCircleX;
    }

    /**
     * @param secondCircleX the secondCircleX to set
     */
    public void setSecondCircleX(double secondCircleX) {
        this.secondCircleX = secondCircleX;
    }

    /**
     * @return the secondCircleY
     */
    public double getSecondCircleY() {
        return secondCircleY;
    }

    /**
     * @param secondCircleY the secondCircleY to set
     */
    public void setSecondCircleY(double secondCircleY) {
        this.secondCircleY = secondCircleY;
    }

    /**
     * @return the thirdCircleX
     */
    public double getThirdCircleX() {
        return thirdCircleX;
    }

    /**
     * @param thirdCircleX the thirdCircleX to set
     */
    public void setThirdCircleX(double thirdCircleX) {
        this.thirdCircleX = thirdCircleX;
    }

    /**
     * @return the thirdCircleY
     */
    public double getThirdCircleY() {
        return thirdCircleY;
    }

    /**
     * @param thirdCircleY the thirdCircleY to set
     */
    public void setThirdCircleY(double thirdCircleY) {
        this.thirdCircleY = thirdCircleY;
    }

    /**
     * @return the fourthCircleX
     */
    public double getFourthCircleX() {
        return fourthCircleX;
    }

    /**
     * @param fourthCircleX the fourthCircleX to set
     */
    public void setFourthCircleX(double fourthCircleX) {
        this.fourthCircleX = fourthCircleX;
    }

    /**
     * @return the fourthCircleY
     */
    public double getFourthCircleY() {
        return fourthCircleY;
    }

    /**
     * @param fourthCircleY the fourthCircleY to set
     */
    public void setFourthCircleY(double fourthCircleY) {
        this.fourthCircleY = fourthCircleY;
    }

    /**
     * @return the outputCircleX
     */
    public double getOutputCircleX() {
        return outputCircleX;
    }

    /**
     * @param outputCircleX the outputCircleX to set
     */
    public void setOutputCircleX(double outputCircleX) {
        this.outputCircleX = outputCircleX;
    }

    /**
     * @return the outputCircleY
     */
    public double getOutputCircleY() {
        return outputCircleY;
    }

    /**
     * @param outputCircleY the outputCircleY to set
     */
    public void setOutputCircleY(double outputCircleY) {
        this.outputCircleY = outputCircleY;
    }
    
    
}
