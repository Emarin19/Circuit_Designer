/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitdesigner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;


/**
 *
 * @author Emanuel
 */
public class DrawGate {
    
    //private Line currentLine;
    
    private String image;
    Image gateImage;
    ImageView logicGateImage;
    
    static double X = 100;
    static double Y = 50;
    
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    Anchor startFirstInput;
    Anchor endFirstInput;
    Line lineFirstInput;
    
    Anchor startSecondInput;
    Anchor endSecondInput;
    Line lineSecondInput; 
    
    Anchor start;
    Anchor end;
    Line line;
    
    double newTranslateX;
    double newTranslateY;
    
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
    
    public void setGate(){
        
        gateImage = new Image(getImage());
        logicGateImage = new ImageView(gateImage);
        
        logicGateImage.setFitWidth(80);
        logicGateImage.setFitHeight(50);
        
        logicGateImage.setX(X);
        logicGateImage.setY(Y);
        
        logicGateImage.setOnMouseClicked(clicked);
        logicGateImage.setOnMousePressed(gateOnMousePressed);
        logicGateImage.setOnMouseDragged(gateOnMouseDragged);

        Main.getController().getRoot().getChildren().addAll(logicGateImage);
        setOutput();
        setFirstInput();
        setSecondInput();
        
        Y = Y + 100;
    }
    
    EventHandler<MouseEvent> clicked = 
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
        
        startFirstInput = new Anchor(Color.CADETBLUE,startFirstInputX,startFirstInputY);
        endFirstInput = new Anchor(Color.CADETBLUE,endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        
        lineFirstInput = new BoundLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        
        Main.getController().getRoot().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
    }
    
    private void setSecondInput() {
        
        DoubleProperty startSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty startSecondInputY = new SimpleDoubleProperty(Y+37);
        DoubleProperty endSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty endSecondInputY = new SimpleDoubleProperty(Y+37);
        
        startSecondInput = new Anchor(Color.CADETBLUE,startSecondInputX,startSecondInputY);
        endSecondInput = new Anchor(Color.CADETBLUE,endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        
        lineSecondInput = new BoundLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        
        Main.getController().getRoot().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
    }

    private void setOutput() {
        DoubleProperty startX = new SimpleDoubleProperty(X+75);
        DoubleProperty startY = new SimpleDoubleProperty(Y+25);
        DoubleProperty endX = new SimpleDoubleProperty(X+75);
        DoubleProperty endY = new SimpleDoubleProperty(Y+25);
        
        start    = new Anchor(Color.CADETBLUE, startX, startY);
        end      = new Anchor(Color.TOMATO,    endX,   endY);
        end.setVisible(false);
        
        line = new BoundLine(startX, startY, endX, endY);
        
        Main.getController().getRoot().getChildren().addAll(start, end, line);
    }

    class BoundLine extends Line {
      BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);
      setStrokeWidth(2);
      setStroke(Color.BLACK);
      setStrokeLineCap(StrokeLineCap.BUTT);
      setMouseTransparent(true);
    }
  }
    
    class Anchor extends Circle { 
    Anchor(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      enableDrag();
    }
    
    private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          // record a delta distance for the drag and drop operation.
          dragDelta.x = getCenterX() - mouseEvent.getX();
          dragDelta.y = getCenterY() - mouseEvent.getY();
          getScene().setCursor(Cursor.MOVE);
        }
      });
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getScene().setCursor(Cursor.HAND);
        }
      });
      setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
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
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.HAND);
          }
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

    // records relative x and y co-ordinates.
    private class Delta { double x, y; }
  }

}
