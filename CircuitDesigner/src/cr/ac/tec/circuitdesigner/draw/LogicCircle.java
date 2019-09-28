
package cr.ac.tec.circuitdesigner.draw;

import cr.ac.tec.circuitdesigner.Main;
import cr.ac.tec.circuitdesigner.draw.Delta;
import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;

/**
 *
 * @author Emanuel Marín
 */
public class LogicCircle extends Circle implements Serializable {
    private Delta dragDelta = new Delta();
    private LogicGate gate;
    private Text textValue;
    private String type;
    private DoubleProperty x;
    private DoubleProperty y;
    private Boolean isCorrect = false;
    private double px;
    private double py;
    
    //Constructor circulo de enlace
    public LogicCircle(DoubleProperty x, DoubleProperty y, LogicGate gate, String type) {
      super(x.get(), y.get(), 5);
      setFill(Color.CADETBLUE);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.gate = gate;
      this.type = type;
      this.x = x;
      this.y = y;
      enableDrag();
    }
    
    //Constructor circulo acompañante
    public LogicCircle(DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 5);
      setFill(Color.CADETBLUE);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.x = x;
      this.y = y;
    }
    
    //Constructor para node de valores de verdad
    public LogicCircle(DoubleProperty x, DoubleProperty y, String type, Text textValue){
        super(x.get(), y.get(),10);
        setFill(Color.DODGERBLUE);
        this.type = type;
        this.textValue = textValue;
        enableSingleDrag();
        this.x = x;
        this.y = y;
    }
    
    private void enableDrag() {
      dragDelta = new Delta();
      setOnMousePressed((MouseEvent mouseEvent) -> {
          setUserData(gate.getInput_Output(type));
          
          setMouseTransparent(true);
          dragDelta.x = getCenterX() - mouseEvent.getX();
          dragDelta.y = getCenterY() - mouseEvent.getY();
          getScene().setCursor(Cursor.HAND);
          mouseEvent.setDragDetect(true);
      });
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            if(isIsCorrect()){
            }
            else if(isIsCorrect()==false){
                setDefaultPosition();
            }
            else{}
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
            Object obj = mouseEvent.getGestureSource();
            if(obj instanceof LogicCircle){
                if(((LogicCircle) obj).getType().equals("Valor")){
                    if(type.equals("Output")){
                        ((LogicCircle) obj).setIsCorrect(false);
                        Main.getController().getMessage().setText("Can´t be connected");
                        Main.getController().getMessage().setUnFocusColor(Color.RED);
                    }
                    else{
                        ((LogicCircle) obj).setIsCorrect(true);
                        gate.setInput_Output(type, (Boolean) ((LogicCircle) obj).getUserData());
                        gate.operate(gate.getType());
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1AEF86"));
                    }
                }
                else if(((LogicCircle) obj).getType().equals("Output") && type.equals("Output")){
                    ((LogicCircle) obj).setIsCorrect(false);
                    Main.getController().getMessage().setText("Can´t be connected");
                    Main.getController().getMessage().setUnFocusColor(Color.RED);
                }
                else{
                    if(((LogicCircle) obj).getType().equals("Output")){
                        ((LogicCircle) obj).setIsCorrect(true);
                        ((LogicCircle) obj).getGate().getOutputsReferences().add(gate);
                        ((LogicCircle) obj).getGate().operate(((LogicCircle) obj).getGate().getType());
                        gate.getInputsReferences().add(((LogicCircle) obj).getGate());
                        gate.setInput_Output(type, ((LogicCircle) obj).getGate().getInput_Output(((LogicCircle) obj).getType()));
                        gate.operate(gate.getType());
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                    }
                    else{
                        ((LogicCircle) obj).setIsCorrect(true);
                        ((LogicCircle) obj).getGate().getInputsReferences().add(gate);
                        gate.getOutputsReferences().add(((LogicCircle) obj).getGate());
                        gate.operate(type);
                        ((LogicCircle) obj).getGate().setInput_Output(((LogicCircle) obj).getType(), gate.getInput_Output(type));
                        ((LogicCircle) obj).getGate().operate(((LogicCircle) obj).getGate().getType());
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1AEF86"));
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
            setRadius(7);
          }
      });
      setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
        @Override public void handle(MouseDragEvent mouseEvent) {
          setRadius(5);
          Object obj = mouseEvent.getGestureSource();
          
          if(obj instanceof LogicCircle){
              if(((LogicCircle) obj).getType()!="Valor"){
                  ((LogicCircle) obj).setRadius(5);
              }
          }
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
          }
          
        }
      }); 
    }

    private void enableSingleDrag() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton()==MouseButton.SECONDARY){
                    Object obj = mouseEvent.getSource();
                    Main.getController().getPane().getChildren().removeAll((LogicCircle)obj, textValue);
                }
                else{
                    setMouseTransparent(true);
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.HAND);
                    mouseEvent.setDragDetect(true);
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(isIsCorrect()){
                }
                else if(isIsCorrect()==false){
                    setDefaultPosition();
                }
                else{}
                setMouseTransparent(false);
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
        setOnDragDetected((MouseEvent event) -> {
            startFullDrag();
        });
        setOnMouseDragEntered((MouseDragEvent mouseEvent) -> {
            Object obj = mouseEvent.getGestureSource();
            if(obj instanceof LogicCircle){
                if(((LogicCircle) obj).getType().equals("Output")){
                    ((LogicCircle) obj).setIsCorrect(false);
                    Main.getController().getMessage().setText("Can´t be connected");
                    Main.getController().getMessage().setUnFocusColor(Color.RED);
                }
                else{
                    ((LogicCircle) obj).setIsCorrect(true);
                    ((LogicCircle) obj).getGate().setInput_Output(((LogicCircle) obj).getType(), (Boolean) getUserData());
                    ((LogicCircle) obj).getGate().operate(((LogicCircle) obj).getGate().getType());
                    Main.getController().getMessage().setText("Connected");
                    Main.getController().getMessage().setUnFocusColor(Color.web("#1AEF86"));
                }
            }
            if (!mouseEvent.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.HAND);
            }
        });
        setOnMouseExited((MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.DEFAULT);
            }
        }); 
        
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the x
     */
    public DoubleProperty getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(DoubleProperty x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public DoubleProperty getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(DoubleProperty y) {
        this.y = y;
    }
    
     /**
     * @return the isCorrect
     */
    public Boolean isIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    private void setDefaultPosition() {
        if(type.equals("Output")){
            setCenterX(gate.getGateImage().getLayoutX()+97.5);
            setCenterY(gate.getGateImage().getLayoutY()+25);
        }
        else if(type.equals("Valor")){
            setCenterX(225);
            setCenterY(20);
        }
        else{
            switch(gate.getType()){
                case "1":
                    setCenterX(gate.getGateImage().getLayoutX()-0.5);
                    setCenterY(gate.getGateImage().getLayoutY()+25);
                    break;
                case "2":
                    if(type.equals("FirstInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-0.5);
                        setCenterY(gate.getGateImage().getLayoutY()+13);
                    }
                    else{
                        setCenterX(gate.getGateImage().getLayoutX()-0.5);
                        setCenterY(gate.getGateImage().getLayoutY()+37);
                    }
                    break;
                case "3":
                    if(type.equals("FirstInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+7);
                    }
                    else if(type.equals("SecondInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+25);
                    }
                    else{
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+43);
                    }
                    break;
                case "4":
                    if(type.equals("FirstInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+3.5);
                    }
                    else if(type.equals("SecondInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+17);
                    }
                    else if(type.equals("ThirdInput")){
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+32);
                    }
                    else{
                        setCenterX(gate.getGateImage().getLayoutX()-1.5);
                        setCenterY(gate.getGateImage().getLayoutY()+47);
                    }
                    break;
            }
        }
    }
    
    /**
     * @return the gate
     */
    public LogicGate getGate() {
        return gate;
    }

}
