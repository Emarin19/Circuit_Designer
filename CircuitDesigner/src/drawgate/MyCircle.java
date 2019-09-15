/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import circuitdesigner.Main;
import drawgate.Delta;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
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
    
    private Delta dragDelta = new Delta();
    private LogicGate gate;
    private String type;
    
    public MyCircle(Color color, DoubleProperty x, DoubleProperty y, LogicGate gate, String type) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.gate = gate;
      this.type = type;
      enableDrag();
    }
    
    public MyCircle(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
    }
    
    public MyCircle(DoubleProperty x, DoubleProperty y, String type){
        super(x.get(), y.get(),10);
        setFill(Color.DODGERBLUE);
        this.type = type;
        enableSingleDrag();
    }
    
    private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            System.out.println("MousePressed");
            System.out.println(gate.foo());
            System.out.println(type);
            setUserData(gate.getValue(type));
            System.out.println(getUserData());
            
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
            startFullDrag();
        }
      });
      setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
        @Override public void handle(MouseDragEvent mouseEvent) {
            System.out.println(gate.toString());

            Object obj = mouseEvent.getGestureSource();
            
            if(obj instanceof MyCircle){
                if(((MyCircle) obj).getType().equals("Valor")){
                    if(type.equals("Salida")){
                        System.out.println("No se puede");
                    }
                    if(type.equals("FirstInput")){
                        System.out.println("Seteando primer entrada");
                        gate.setFirstInput((Boolean) ((MyCircle) obj).getUserData());
                        System.out.println(gate.getFirstInput());
                    }
                    if(type.equals("SecondInput")){
                        System.out.println("Seteando segunda entrada");
                        gate.setSecondInput((Boolean) ((MyCircle) obj).getUserData());
                        System.out.println(gate.getSecondInput());
                    }
                    if(gate.getFirstInput()!= null && gate.getSecondInput()!= null){
                        System.out.println("El valor de salida de la cumpuerta " + gate.toString() + "es " + gate.getOutput());
                    }
                }
                else if(((MyCircle) obj).getType().equals("Salida") && type.equals("Salida")){
                    System.out.println("No se pueden conectar");
                }
                else{
                    if(((MyCircle) obj).getType().equals("Salida")){
                        ((MyCircle) obj).getGate().getOutputs().add(gate);
                        gate.getInputs().add(((MyCircle) obj).getGate());
                        
                        gate.setValue(type, (Boolean) ((MyCircle) obj).getUserData());
                        
                    }
                }
            }
            
            
            if (!mouseEvent.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.HAND);
            }
        }
      });
      setOnMouseDragOver(new EventHandler<MouseDragEvent>(){
          @Override public void handle(MouseDragEvent mouseEvent){

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

    private void enableSingleDrag() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            
            if(mouseEvent.getButton()==MouseButton.SECONDARY){
                Object obj = mouseEvent.getSource();
                Main.getController().getRoot().getChildren().remove((MyCircle)obj);
            }
            else{
                System.out.println("Soy " + type);
                System.out.println(getUserData());
                setMouseTransparent(true);
                dragDelta.x = getCenterX() - mouseEvent.getX();
                dragDelta.y = getCenterY() - mouseEvent.getY();
                getScene().setCursor(Cursor.HAND);
                mouseEvent.setDragDetect(true);
            }
        }
      });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
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
            startFullDrag();
        }
      });
        setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
        @Override public void handle(MouseDragEvent mouseEvent) {
            if (!mouseEvent.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.HAND);
            }
        }
      });
        setOnMouseDragOver(new EventHandler<MouseDragEvent>(){
          @Override public void handle(MouseDragEvent mouseEvent){
              /*if(mouseEvent.getButton() == MouseButton.SECONDARY){
                Main.getController().getRoot().getChildren().remove(this);
            }*/
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
    
    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

}
