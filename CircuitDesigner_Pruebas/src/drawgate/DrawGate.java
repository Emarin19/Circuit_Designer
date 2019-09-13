/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import circuitdesigner.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import nodes.LogicGate;


/**
 *
 * @author Emanuel
 */
public class DrawGate {
    
    private String image;
    private Image gateImage;
    private Gate gate;
    
    private static double X = 100;
    private static double Y = 50;
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    private MyCircle startFirstInput;
    private MyCircle endFirstInput;
    private Line lineFirstInput;
    
    private MyCircle startSecondInput;
    private MyCircle endSecondInput;
    private Line lineSecondInput;
    
    private MyCircle start;
    private MyCircle end;
    private Line line;
    
    public DrawGate(String image){
        this.image = image;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    public void setGate(){
        
        gateImage = new Image(getImage());
        gate = new Gate(getImage());
        gate.setImage(gateImage);
        gate.setFitWidth(80);
        gate.setFitHeight(50);

        gate.setX(X);
        gate.setY(Y);
        
        //gate.setOnMouseClicked(clicked);
        gate.setOnMousePressed(gateOnMousePressed);
        gate.setOnMouseDragged(gateOnMouseDragged);

        Main.getController().getRoot().getChildren().addAll(gate);
        setOutput();
        setFirstInput();
        setSecondInput();
        
        Y = Y + 100;
    }
    
    EventHandler<MouseEvent> clicked = 
        new EventHandler<MouseEvent>(){
        
        @Override
        public void handle(MouseEvent t){
            
            //t.getSource().toString();
            //System.out.println(gate.getGate().foo());
            //System.out.println(gate.getGate().getOutput().toString());
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
            
            startFirstInput.setTranslateX(newTranslateX);
            startFirstInput.setTranslateY(newTranslateY);
            endFirstInput.setTranslateX(newTranslateX);
            endFirstInput.setTranslateY(newTranslateY);
            lineFirstInput.setTranslateX(newTranslateX);
            lineFirstInput.setTranslateY(newTranslateY);
            
            startSecondInput.setTranslateX(newTranslateX);
            startSecondInput.setTranslateY(newTranslateY);
            endSecondInput.setTranslateX(newTranslateX);
            endSecondInput.setTranslateY(newTranslateY);
            lineSecondInput.setTranslateX(newTranslateX);
            lineSecondInput.setTranslateY(newTranslateY);
            
            start.setTranslateX(newTranslateX);
            start.setTranslateY(newTranslateY);
            end.setTranslateX(newTranslateX);
            end.setTranslateY(newTranslateY);
            line.setTranslateX(newTranslateX);
            line.setTranslateY(newTranslateY);
        }
    };

    private void setFirstInput() {
        
        DoubleProperty startFirstInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty startFirstInputY = new SimpleDoubleProperty(Y+13);
        DoubleProperty endFirstInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty endFirstInputY = new SimpleDoubleProperty(Y+13);
        
        startFirstInput = new MyCircle(Color.CADETBLUE,startFirstInputX,startFirstInputY);
        endFirstInput = new MyCircle(Color.CADETBLUE,endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        
        lineFirstInput = new MyLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        
        Main.getController().getRoot().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
    }
    
    private void setSecondInput() {
        
        DoubleProperty startSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty startSecondInputY = new SimpleDoubleProperty(Y+37);
        DoubleProperty endSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty endSecondInputY = new SimpleDoubleProperty(Y+37);
        
        startSecondInput = new MyCircle(Color.CADETBLUE,startSecondInputX,startSecondInputY);
        endSecondInput = new MyCircle(Color.CADETBLUE,endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        
        lineSecondInput = new MyLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        
        Main.getController().getRoot().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
    }

    private void setOutput() {
        DoubleProperty startX = new SimpleDoubleProperty(X+75);
        DoubleProperty startY = new SimpleDoubleProperty(Y+25);
        DoubleProperty endX = new SimpleDoubleProperty(X+75);
        DoubleProperty endY = new SimpleDoubleProperty(Y+25);
        
        start    = new MyCircle(Color.CADETBLUE, startX, startY);
        end      = new MyCircle(Color.TOMATO,    endX,   endY);
        end.setVisible(false);
        
        line = new MyLine(startX, startY, endX, endY);
        
        Main.getController().getRoot().getChildren().addAll(start, end, line);
    }
    
    public LogicGate gate(){
        gate.getGate().setFirstInput(startFirstInput);
        gate.getGate().setSecondInput(startSecondInput);
        gate.getGate().setOutput(start);
        return gate.getGate();
    }
    
  }


