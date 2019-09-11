/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import drawgate.Delta;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import nodes.LogicGate;


/**
 *
 * @author Emanuel
 */
public class MyCircle extends Circle {
    LogicGate gate;
    String type;
    Delta dragDelta = new Delta();
    MyCircle(Color color, DoubleProperty x, DoubleProperty y, LogicGate gate, String type) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.gate = gate;
      this.type = type;
      enableDrag();
    }
    
    MyCircle(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
    }
    
    private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            System.out.println("MousePressed");
            setMouseTransparent(true);
            dragDelta.x = getCenterX() - mouseEvent.getX();
            dragDelta.y = getCenterY() - mouseEvent.getY();
            getScene().setCursor(Cursor.HAND);
            mouseEvent.setDragDetect(true);
        }
      });
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            System.out.println("Mouse Released");
            getScene().setCursor(Cursor.HAND);
            setMouseTransparent(false);
        }
      });
      setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            mouseEvent.setDragDetect(false);
            double newX = mouseEvent.getX() + dragDelta.x;
            if (newX > 0 && newX < getScene().getWidth()) {
                setCenterX(newX);
            }  
            double newY = mouseEvent.getY() + dragDelta.y;
            if (newY > 0 && newY < getScene().getHeight()) {
                setCenterY(newY);
            }  
        }
      });
      setOnDragDetected(new EventHandler <MouseEvent>(){
        @Override public void handle(MouseEvent event){
            //System.out.println(gate.foo());
            System.out.println("Soy: " + type + " " + gate.getClass());
            System.out.println("Drag Detected");
            startFullDrag();
        }
      });
      setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
        @Override public void handle(MouseDragEvent mouseEvent) {
            System.out.println("Mouse Entered");
            if (!mouseEvent.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.HAND);
            }
        }
      });
      setOnMouseDragOver(new EventHandler<MouseDragEvent>(){
          @Override public void handle(MouseDragEvent mouseEvent){
              //mouseEvent.getClass().
              System.out.println(mouseEvent.getTarget());
              System.out.println("Holi");
          }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
          }
        }
      }); 
    }
    
    public void saySomething(String something){
        System.out.println(something);
    }
    
    public void enable(Boolean value){
        
        this.setUserData(value);
        System.out.println(this.getUserData());
    }
}
