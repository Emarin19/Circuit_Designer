
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
    
    public LogicCircle(Color color, DoubleProperty x, DoubleProperty y, LogicGate gate, String type) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.gate = gate;
      this.type = type;
      enableDrag();
    }
    
    public LogicCircle(Color color, DoubleProperty x, DoubleProperty y, LogicGate gate, String type, Text textvalue) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
      this.gate = gate;
      this.type = type;
      this.textValue = textValue;
      enableDrag();
    }
    
    public LogicCircle(Color color, DoubleProperty x, DoubleProperty y) {
      super(x.get(), y.get(), 5);
      setFill(color);
      x.bind(centerXProperty());
      y.bind(centerYProperty());
    }
    
    public LogicCircle(DoubleProperty x, DoubleProperty y, String type, Text textValue){
        super(x.get(), y.get(),10);
        setFill(Color.DODGERBLUE);
        this.type = type;
        this.textValue = textValue;
        enableSingleDrag();
    }
    
    private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            System.out.println("MousePressed");
            System.out.println(gate.getName());
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
            
            if(obj instanceof LogicCircle){
                if(((LogicCircle) obj).getType().equals("Valor")){
                    if(type.equals("Salida")){
                        Main.getController().getMessage().setText("Can´t be connected");
                        Main.getController().getMessage().setUnFocusColor(Color.RED);
                        //OnMouseRealesded restablecer la ubicación de circulo
                    }
                    if(type.equals("FirstInput")){
                        System.out.println("Seteando primer entrada");
                        gate.setFirstInput((Boolean) ((LogicCircle) obj).getUserData());
                        System.out.println(gate.getFirstInput());
                        gate.operate();
                        System.out.println("Salida actual " + gate.getOutput());
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                    }
                    if(type.equals("SecondInput")){
                        System.out.println("Seteando segunda entrada");
                        gate.setSecondInput((Boolean) ((LogicCircle) obj).getUserData());
                        System.out.println(gate.getSecondInput());
                        gate.operate();
                        System.out.println("Salida actual " + gate.getOutput());
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                    }
                }
                else if(((LogicCircle) obj).getType().equals("Salida") && type.equals("Salida")){
                    Main.getController().getMessage().setText("Can´t be connected");
                    Main.getController().getMessage().setUnFocusColor(Color.RED);
                    //OnMouseRealesed poner en las misma coordenadas iniciales
                }
                else{
                    if(((LogicCircle) obj).getType().equals("Salida")){
                        
                        ((LogicCircle) obj).getGate().getOutputsReferences().add(gate);
                        gate.getInputsReferences().add(((LogicCircle) obj).getGate());
                        gate.setValue(type, (Boolean) ((LogicCircle) obj).getUserData());
                        gate.operate();
                        ((LogicCircle) obj).getGate().operate();
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
                        
                    }
                    else{
                        System.out.println("Hola soy de tipo "  + ((LogicCircle) obj).getType());
                        ((LogicCircle) obj).getGate().getInputsReferences().add(gate);
                        gate.getOutputsReferences().add(((LogicCircle) obj).getGate());
                        ((LogicCircle) obj).getGate().setValue(((LogicCircle) obj).getType(), gate.getOutput());
                        gate.operate();
                        ((LogicCircle) obj).getGate().operate();
                        Main.getController().getMessage().setText("Connected");
                        Main.getController().getMessage().setUnFocusColor(Color.web("#1aef86"));
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
        @Override public void handle(MouseEvent mouseEvent) {
            
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
            System.out.println("You entered");
            Object obj = mouseEvent.getGestureSource();
            if(obj instanceof LogicCircle){
                System.out.println(((LogicCircle) obj).getGate().getName());
                if(((LogicCircle) obj).getGate().getType().equals("Salida")){
                    Main.getController().getMessage().setText("Can´t be connected");
                    Main.getController().getMessage().setUnFocusColor(Color.RED);
                }
                else{
                    
                    ((LogicCircle) obj).getGate().setValue(((LogicCircle) obj).getType(), (Boolean) getUserData());
                    ((LogicCircle) obj).getGate().operate();
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
