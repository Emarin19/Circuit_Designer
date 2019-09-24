/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec.circuitdesigner.draw;

import cr.ac.tec.circuitdesigner.Facade;
import cr.ac.tec.circuitdesigner.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import cr.ac.tec.circuitdesigner.nodes.AndTwo;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;


/**
 *
 * @author Emanuel
 */
public class DrawGate {
    
    private String image;
    private Image gateImage;
    private GateImage gate;
    
    private static double X = 100;
    private static double Y = 50;
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    private LogicCircle Value;
    private Text textValue;
    
    private LogicCircle startFirstInput;
    private LogicCircle endFirstInput;
    private Line lineFirstInput;
    
    private LogicCircle startSecondInput;
    private LogicCircle endSecondInput;
    private Line lineSecondInput;
    
    private LogicCircle start;
    private LogicCircle end;
    private Line line;
    
    public DrawGate(){
        
    }
    
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
        gate = new GateImage(getImage());
        gate.setImage(gateImage);
        gate.setFitWidth(95);
        gate.setFitHeight(50);

        gate.setX(X);
        gate.setY(Y);
        
        gate.setOnMouseClicked(e ->{
            
            LogicGate actualGate;
            if(e.getButton() == MouseButton.SECONDARY){
                for(int i=0; i<Facade.getCircuit().getSize(); i++){
                    actualGate = Facade.getCircuit().getValue(i);
                    if(actualGate.equals(gate())){
                        System.out.println(i);
                        Facade.getCircuit().remove(i);
                    }
                }
                
                Main.getController().getMessage().setText("Gate " + gate().foo() + " removed");
                Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                Main.getController().getRoot().getChildren().removeAll(gate,startFirstInput,endFirstInput,lineFirstInput,startSecondInput,endSecondInput,lineSecondInput,start,end,line);
            }
        });
        
        gate.setOnMousePressed(gateOnMousePressed);
        gate.setOnMouseDragged(gateOnMouseDragged);

        Main.getController().getRoot().getChildren().addAll(gate);
        setOutput();
        setFirstInput();
        setSecondInput();
        
        Y = Y + 60;
    }
    
    public void setNot(){
       
        gateImage = new Image(getImage());
        gate = new GateImage(getImage());
        gate.setImage(gateImage);
        gate.setFitWidth(95);
        gate.setFitHeight(50);

        gate.setX(X);
        gate.setY(Y);
        
        gate.setOnMouseClicked(e ->{
            LogicGate actualGate;
            if(e.getButton() == MouseButton.SECONDARY){
                for(int i=0; i<Facade.getCircuit().getSize(); i++){
                    actualGate = Facade.getCircuit().getValue(i);
                    if(actualGate.equals(gate())){
                        System.out.println(i);
                        Facade.getCircuit().remove(i);
                    }
                }
                Main.getController().getMessage().setText("Gate " + gate().foo() + " removed");
                Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                Main.getController().getRoot().getChildren().removeAll(gate,startFirstInput,endFirstInput,lineFirstInput,start,end,line);
            }
        });
        gate.setOnMousePressed(gateOnMousePressed);
        gate.setOnMouseDragged(gateOnMouseDraggedNot);

        Main.getController().getRoot().getChildren().addAll(gate);
        setInput();
        setOutput();
        
        Y = Y + 60;
        
    }
    
    public void setBooleanValue(Boolean value){
        DoubleProperty  circleX = new SimpleDoubleProperty(50);
        DoubleProperty circleY = new SimpleDoubleProperty(50);
        textValue = new Text();
        Value = new LogicCircle(circleX, circleY, "Valor", textValue);
        Value.setUserData(value);
        
        if(value){
            textValue.setText("1");
        }
        else{
            textValue.setText("0");
        }
        textValue.setFill(Color.WHITE);
        textValue.setFont(Font.font("Swis721 BT"));
        textValue.setTextOrigin(VPos.CENTER);
        textValue.xProperty().bind(Value.centerXProperty());
        textValue.yProperty().bind(Value.centerYProperty());
        textValue.setMouseTransparent(true);
        
        Main.getController().getRoot().getChildren().addAll(Value,textValue);
        
    }
    
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
    
    EventHandler<MouseEvent> gateOnMouseDraggedNot = 
        
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
        
        startFirstInput = new LogicCircle(Color.CADETBLUE,startFirstInputX,startFirstInputY, gate.getGate(), "FirstInput");
        endFirstInput = new LogicCircle(Color.CADETBLUE,endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        
        Main.getController().getRoot().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
    }
    
    private void setInput() {
        
        DoubleProperty startFirstInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty startFirstInputY = new SimpleDoubleProperty(Y+25);
        DoubleProperty endFirstInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty endFirstInputY = new SimpleDoubleProperty(Y+25);
        
        startFirstInput = new LogicCircle(Color.CADETBLUE,startFirstInputX,startFirstInputY, gate.getGate(), "FirstInput");
        endFirstInput = new LogicCircle(Color.CADETBLUE,endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        
        Main.getController().getRoot().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
    }
    
    private void setSecondInput() {
        
        DoubleProperty startSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty startSecondInputY = new SimpleDoubleProperty(Y+37);
        DoubleProperty endSecondInputX = new SimpleDoubleProperty(X+2);
        DoubleProperty endSecondInputY = new SimpleDoubleProperty(Y+37);
        
        startSecondInput = new LogicCircle(Color.CADETBLUE,startSecondInputX,startSecondInputY, gate.getGate(), "SecondInput");
        endSecondInput = new LogicCircle(Color.CADETBLUE,endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        
        Main.getController().getRoot().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
    }

    private void setOutput() {
        DoubleProperty startX = new SimpleDoubleProperty(X+95);
        DoubleProperty startY = new SimpleDoubleProperty(Y+25);
        DoubleProperty endX = new SimpleDoubleProperty(X+95);
        DoubleProperty endY = new SimpleDoubleProperty(Y+25);
        
        start    = new LogicCircle(Color.CADETBLUE, startX, startY, gate.getGate(), "Salida");
        end      = new LogicCircle(Color.TOMATO,    endX,   endY);
        end.setVisible(false);
        
        line = new LogicLine(startX, startY, endX, endY);
        
        Main.getController().getRoot().getChildren().addAll(start, end, line);
    }
    
    public LogicGate gate(){
        gate.getGate().setImage(image);
        gate.getGate().Gate(gate);
        gate.getGate().setFirstCircle(startFirstInput);
        gate.getGate().setSecondCircle(startSecondInput);
        gate.getGate().setOutCircle(start);
        return gate.getGate();
    }
    
    
  }


