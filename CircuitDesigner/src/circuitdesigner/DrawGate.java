/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Emanuel
 */
public class DrawGate {
    
    private String image;
    static double X = 100;
    static double Y = 50;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    Image gateImage;
    ImageView logicGateImage;
    
    public DrawGate(String image){
        this.image = image;
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
    
    public ImageView setImage(){
        try {
            gateImage = new Image(new FileInputStream(getImage()));
            logicGateImage = new ImageView(gateImage);
            logicGateImage.setFitWidth(80);
            logicGateImage.setFitHeight(50);
            logicGateImage.setOnMouseClicked(cl);
            logicGateImage.setOnMousePressed(gateOnMousePressed);
            logicGateImage.setOnMouseDragged(gateOnMouseDragged);
            logicGateImage.setX(X);
            logicGateImage.setY(Y);
            Y = Y + 100;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrawGate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return logicGateImage;
        
    }
    
    EventHandler<MouseEvent> cl = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            System.out.println("Clicked now");
        }
    };
    
    EventHandler<MouseEvent> gateOnMousePressed = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
        }
    };
    
    EventHandler<MouseEvent> gateOnMouseDragged = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
        }
    };
}
