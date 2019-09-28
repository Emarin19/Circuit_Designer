
package cr.ac.tec.circuitdesigner.draw;

import cr.ac.tec.circuitdesigner.factory.GateImage;
import cr.ac.tec.circuitdesigner.Main;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import cr.ac.tec.circuitdesigner.nodes.LogicGate;
import cr.ac.tec.circuitdesigner.storage.Deserialize;
import cr.ac.tec.circuitdesigner.storage.Serialize;
import javafx.scene.text.Text;


/**
 *
 * @author Emanuel Marín
 */
public class DrawGate {
    
    private String image;
    private final int num;
    private Image gateImage;
    private GateImage node;
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    private static int In;
    private static int Out;
    private Text first,second,third,fourth,out;

    private LogicCircle startFirstInput;
    private LogicCircle endFirstInput;
    private Line lineFirstInput;
    
    private LogicCircle startSecondInput;
    private LogicCircle endSecondInput;
    private Line lineSecondInput;
    
    private LogicCircle startThirdInput;
    private LogicCircle endThirdInput;
    private Line lineThirdInput;
    
    private LogicCircle startFourthInput;
    private LogicCircle endFourthInput;
    private Line lineFourthInput;
    
    private LogicCircle startOutput;
    private LogicCircle endOutput;
    private Line lineOutput;
    
    private Serialize serial;
   
    
    public DrawGate(String image, int num){
        this.image = image;
        this.num = num;
    }
    
    public DrawGate(int num){
        this.image = null;
        this.num = num;
    }

    public void setGate(){
        //Cargo la imagen del nodo y le asigno la referencia de la compuerta
        gateImage = new Image(image);
        node = new GateImage(image);
        node.setImage(gateImage);
        node.setFitWidth(95);
        node.setFitHeight(50);
        node.getCurrentGate().setGateImage(node);
        
        node.setOnMouseClicked(e ->{
            LogicGate currentGate;
            if(e.getButton() == MouseButton.SECONDARY){
                for(int i=0; i<Builder.getCircuit().getSize(); i++){
                    currentGate = Builder.getCircuit().getValue(i);
                    if(Builder.getCircuit().getSize()==1){
                        Builder.newCircuit();
                    }
                    else{
                        if(currentGate.equals(node.getCurrentGate())){
                            System.out.println(i);
                            Builder.getCircuit().remove(i);
                        } 
                    } 
                }
                
                Main.getController().getMessage().setText("Gate " + getGate().getName() + " removed");
                Main.getController().getMessage().setUnFocusColor(Color.web("#1AEF86"));
                Main.getController().getPane().getChildren().removeAll(node,startFirstInput,endFirstInput,lineFirstInput,startSecondInput,endSecondInput,lineSecondInput,startThirdInput,endThirdInput,lineThirdInput,startFourthInput,endFourthInput,lineFourthInput,startOutput,endOutput,lineOutput,first,second,third,fourth,out);
            }
        });
        
        ubicateNode();
        setInputs();
        setOutput();
        node.setOnMousePressed(gateOnMousePressed);
        node.getCurrentGate().setCircle("Output", startOutput);
        Main.getController().getPane().getChildren().addAll(node);
    }
    
    public void setAgainGate(){
        //Cargo la imagen del nodo y le asigno la referencia de la compuerta
        serial = Deserialize.serial();
        setImage();
        gateImage = new Image(image);
        node = new GateImage(image);
        node.setImage(gateImage);
        node.setFitWidth(95);
        node.setFitHeight(50);
        node.getCurrentGate().setGateImage(node);
        
        node.setOnMouseClicked(e ->{
            LogicGate currentGate;
            if(e.getButton() == MouseButton.SECONDARY){
                for(int i=0; i<Builder.getCircuit().getSize(); i++){
                    currentGate = Builder.getCircuit().getValue(i);
                    if(Builder.getCircuit().getSize()==1){
                        Builder.newCircuit();
                    }
                    else{
                        if(currentGate.equals(node.getCurrentGate())){
                            System.out.println(i);
                            Builder.getCircuit().remove(i);
                        } 
                    } 
                }
                
                Main.getController().getMessage().setText("Gate " + getGate().getName() + " removed");
                Main.getController().getMessage().setUnFocusColor(Color.web("#1AEF86"));
                Main.getController().getPane().getChildren().removeAll(node,startFirstInput,endFirstInput,lineFirstInput,startSecondInput,endSecondInput,lineSecondInput,startThirdInput,endThirdInput,lineThirdInput,startFourthInput,endFourthInput,lineFourthInput,startOutput,endOutput,lineOutput,first,second,third,fourth,out);
            }
        });

        node.setLayoutX(serial.getNodeX());
        node.setLayoutY(serial.getNodeY());
        setInputsAgain();
        setOutputAgain();
        node.setOnMousePressed(gateOnMousePressed);
        Main.getController().getPane().getChildren().addAll(node);
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

            startOutput.setTranslateX(newTranslateX);
            startOutput.setTranslateY(newTranslateY);
            endOutput.setTranslateX(newTranslateX);
            endOutput.setTranslateY(newTranslateY);
            lineOutput.setTranslateX(newTranslateX);
            lineOutput.setTranslateY(newTranslateY);
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
            
            startOutput.setTranslateX(newTranslateX);
            startOutput.setTranslateY(newTranslateY);
            endOutput.setTranslateX(newTranslateX);
            endOutput.setTranslateY(newTranslateY);
            lineOutput.setTranslateX(newTranslateX);
            lineOutput.setTranslateY(newTranslateY);
        }
    };
    
    EventHandler<MouseEvent> gateOnMouseDraggedThree = 
        
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
            
            startThirdInput.setTranslateX(newTranslateX);
            startThirdInput.setTranslateY(newTranslateY);
            endThirdInput.setTranslateX(newTranslateX);
            endThirdInput.setTranslateY(newTranslateY);
            lineThirdInput.setTranslateX(newTranslateX);
            lineThirdInput.setTranslateY(newTranslateY);
            
            startOutput.setTranslateX(newTranslateX);
            startOutput.setTranslateY(newTranslateY);
            endOutput.setTranslateX(newTranslateX);
            endOutput.setTranslateY(newTranslateY);
            lineOutput.setTranslateX(newTranslateX);
            lineOutput.setTranslateY(newTranslateY);
        }
    };
    
    EventHandler<MouseEvent> gateOnMouseDraggedFour = 
        
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
            
            startThirdInput.setTranslateX(newTranslateX);
            startThirdInput.setTranslateY(newTranslateY);
            endThirdInput.setTranslateX(newTranslateX);
            endThirdInput.setTranslateY(newTranslateY);
            lineThirdInput.setTranslateX(newTranslateX);
            lineThirdInput.setTranslateY(newTranslateY);
            
            startFourthInput.setTranslateX(newTranslateX);
            startFourthInput.setTranslateY(newTranslateY);
            endFourthInput.setTranslateX(newTranslateX);
            endFourthInput.setTranslateY(newTranslateY);
            lineFourthInput.setTranslateX(newTranslateX);
            lineFourthInput.setTranslateY(newTranslateY);
            
            startOutput.setTranslateX(newTranslateX);
            startOutput.setTranslateY(newTranslateY);
            endOutput.setTranslateX(newTranslateX);
            endOutput.setTranslateY(newTranslateY);
            lineOutput.setTranslateX(newTranslateX);
            lineOutput.setTranslateY(newTranslateY);
        }
    };

    private void ubicateNode() {
        switch (image) {
            case "AND.png":
            case "ANDTHREE.png":
            case "ANDFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(30);
                break;
            case "OR.png":
            case "ORTHREE.png":
            case "ORFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(100);
                break;
            case "NOT.png":
                node.setLayoutX(30);
                node.setLayoutY(170);
                break;
            case "NAND.png":
            case "NANDTHREE.png":
            case "NANDFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(240);
                break;
            case "NOR.png":
            case "NORTHREE.png":
            case "NORFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(310);
                break;
            case "XOR.png":
            case "XORTHREE.png":
            case "XORFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(380);
                break;
            case "XNOR.png":
            case "XNORTHREE.png":
            case "XNORFOUR.png":
                node.setLayoutX(30);
                node.setLayoutY(450);
                break;
            default:
                break;
        }
    }    

    private void setInputs() {
         switch(num){
             case 1:
                 setInputNot();
                 node.getCurrentGate().setType("1");
                 node.setOnMouseDragged(gateOnMouseDraggedNot);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 break;
             case 2:
                 setTwoInputs();
                 node.getCurrentGate().setType("2");
                 node.setOnMouseDragged(gateOnMouseDragged);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 break;
             case 3:
                 setThreeInputs();
                 node.getCurrentGate().setType("3");
                 node.setOnMouseDragged(gateOnMouseDraggedThree);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 node.getCurrentGate().setCircle("ThirdInput", startThirdInput);
                 break;
             case 4:
                 setFourInputs();
                 node.getCurrentGate().setType("4");
                 node.setOnMouseDragged(gateOnMouseDraggedFour);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 node.getCurrentGate().setCircle("ThirdInput", startThirdInput);
                 node.getCurrentGate().setCircle("FourthInput", startFourthInput);
                 break;     
         }
    }
    
    private void setInputNot() {
        DoubleProperty startFirstInputX = new SimpleDoublePosition(node.getLayoutX()-0.5);
        DoubleProperty startFirstInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput,endFirstInput,lineFirstInput,first);
    }

    private void setTwoInputs() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(node.getLayoutX()-0.5);
        DoubleProperty startFirstInputY = new SimpleDoublePosition(node.getLayoutY()+13);
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+13);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput,first);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(node.getLayoutX()-0.5);
        DoubleProperty startSecondInputY = new SimpleDoublePosition(node.getLayoutY()+37);
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+37);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput,second);
    }

    private void setThreeInputs() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startFirstInputY = new SimpleDoublePosition(node.getLayoutY()+7);
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+7);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startSecondInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
        //ThirdInput
        DoubleProperty startThirdInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startThirdInputY = new SimpleDoublePosition(node.getLayoutY()+43);
        DoubleProperty endThirdInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endThirdInputY = new SimpleDoublePosition(node.getLayoutY()+43);
        startThirdInput = new LogicCircle(startThirdInputX,startThirdInputY, node.getCurrentGate(), "ThirdInput");
        third = new Text();
        third.setText("I" + ++In);
        third.xProperty().bind(startThirdInput.translateXProperty());
        third.yProperty().bind(startThirdInput.translateYProperty());
        third.setLayoutX(startThirdInput.getCenterX());
        third.setLayoutY(startThirdInput.getCenterY()-5);
        endThirdInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endThirdInput.setVisible(false);
        lineThirdInput = new LogicLine(startThirdInputX,startThirdInputY,endThirdInputX,endThirdInputY);
        Main.getController().getPane().getChildren().addAll(startThirdInput, endThirdInput, lineThirdInput);
        
    }

    private void setFourInputs() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startFirstInputY = new SimpleDoublePosition(node.getLayoutY()+3.5);
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+3.5);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startSecondInputY = new SimpleDoublePosition(node.getLayoutY()+17);
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+17);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
        //ThirdInput
        DoubleProperty startThirdInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startThirdInputY = new SimpleDoublePosition(node.getLayoutY()+32);
        DoubleProperty endThirdInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endThirdInputY = new SimpleDoublePosition(node.getLayoutY()+32);
        startThirdInput = new LogicCircle(startThirdInputX,startThirdInputY, node.getCurrentGate(), "ThirdInput");
        third = new Text();
        third.setText("I" + ++In);
        third.xProperty().bind(startThirdInput.translateXProperty());
        third.yProperty().bind(startThirdInput.translateYProperty());
        third.setLayoutX(startThirdInput.getCenterX());
        third.setLayoutY(startThirdInput.getCenterY()-5);
        endThirdInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endThirdInput.setVisible(false);
        lineThirdInput = new LogicLine(startThirdInputX,startThirdInputY,endThirdInputX,endThirdInputY);
        Main.getController().getPane().getChildren().addAll(startThirdInput, endThirdInput, lineThirdInput);
        
        //FourthInput
        DoubleProperty startFourthInputX = new SimpleDoublePosition(node.getLayoutX()-1.5);
        DoubleProperty startFourthInputY = new SimpleDoublePosition(node.getLayoutY()+47);
        DoubleProperty endFourthInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFourthInputY = new SimpleDoublePosition(node.getLayoutY()+47);
        startFourthInput = new LogicCircle(startFourthInputX,startFourthInputY, node.getCurrentGate(), "FourthInput");
        fourth = new Text();
        fourth.setText("I" + ++In);
        fourth.xProperty().bind(startFourthInput.translateXProperty());
        fourth.yProperty().bind(startFourthInput.translateYProperty());
        fourth.setLayoutX(startFourthInput.getCenterX());
        fourth.setLayoutY(startFourthInput.getCenterY()-5);
        endFourthInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endFourthInput.setVisible(false);
        lineFourthInput = new LogicLine(startFourthInputX,startFourthInputY,endFourthInputX,endFourthInputY);
        Main.getController().getPane().getChildren().addAll(startFourthInput, endFourthInput, lineFourthInput);
        
    }
    
    private void setOutput() {
        DoubleProperty startX = new SimpleDoublePosition(node.getLayoutX()+97.5);
        DoubleProperty startY = new SimpleDoublePosition(node.getLayoutY()+25);
        DoubleProperty endX = new SimpleDoublePosition(node.getLayoutX()+95);
        DoubleProperty endY = new SimpleDoublePosition(node.getLayoutY()+25);
        startOutput = new LogicCircle(startX, startY, node.getCurrentGate(), "Output");
        out = new Text();
        out.setText("O" + ++Out);
        out.xProperty().bind(startOutput.translateXProperty());
        out.yProperty().bind(startOutput.translateYProperty());
        out.setLayoutX(startOutput.getCenterX()-10);
        out.setLayoutY(startOutput.getCenterY()-5);
        endOutput = new LogicCircle(endX,   endY);
        endOutput.setVisible(false);
        lineOutput = new LogicLine(startX, startY, endX, endY);
        Main.getController().getPane().getChildren().addAll(startOutput, endOutput, lineOutput,out);
    }
    
    private void setInputsAgain() {
        switch(num){
             case 1:
                 setInputNotAgain();
                 node.getCurrentGate().setType("1");
                 node.setOnMouseDragged(gateOnMouseDraggedNot);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 break;
             case 2:
                 setTwoInputsAgain();
                 node.getCurrentGate().setType("2");
                 node.setOnMouseDragged(gateOnMouseDragged);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 break;
             case 3:
                 setThreeInputsAgain();
                 node.getCurrentGate().setType("3");
                 node.setOnMouseDragged(gateOnMouseDraggedThree);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 node.getCurrentGate().setCircle("ThirdInput", startThirdInput);
                 break;
             case 4:
                 setFourInputsAgain();
                 node.getCurrentGate().setType("4");
                 node.setOnMouseDragged(gateOnMouseDraggedFour);
                 node.getCurrentGate().setCircle("FirstInput", startFirstInput);
                 node.getCurrentGate().setCircle("SecondInput", startSecondInput);
                 node.getCurrentGate().setCircle("ThirdInput", startThirdInput);
                 node.getCurrentGate().setCircle("FourthInput", startFourthInput);
                 break;     
         }
    }
    
    private void setInputNotAgain() {
        DoubleProperty startFirstInputX = new SimpleDoublePosition(serial.getFirstCircleX());
        DoubleProperty startFirstInputY = new SimpleDoublePosition(serial.getFirstCircleY());
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput,endFirstInput,lineFirstInput,first);
        
    }
    
    private void setTwoInputsAgain() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(serial.getFirstCircleX());
        DoubleProperty startFirstInputY = new SimpleDoublePosition(serial.getFirstCircleY());
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+13);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput,first);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(serial.getSecondCircleX());
        DoubleProperty startSecondInputY = new SimpleDoublePosition(serial.getSecondCircleY());
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+37);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput,second);
    }

    private void setThreeInputsAgain() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(serial.getFirstCircleX());
        DoubleProperty startFirstInputY = new SimpleDoublePosition(serial.getFirstCircleY());
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+7);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(serial.getSecondCircleX());
        DoubleProperty startSecondInputY = new SimpleDoublePosition(serial.getSecondCircleY());
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+25);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
        //ThirdInput
        DoubleProperty startThirdInputX = new SimpleDoublePosition(serial.getThirdCircleX());
        DoubleProperty startThirdInputY = new SimpleDoublePosition(serial.getThirdCircleY());
        DoubleProperty endThirdInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endThirdInputY = new SimpleDoublePosition(node.getLayoutY()+43);
        startThirdInput = new LogicCircle(startThirdInputX,startThirdInputY, node.getCurrentGate(), "ThirdInput");
        third = new Text();
        third.setText("I" + ++In);
        third.xProperty().bind(startThirdInput.translateXProperty());
        third.yProperty().bind(startThirdInput.translateYProperty());
        third.setLayoutX(startThirdInput.getCenterX());
        third.setLayoutY(startThirdInput.getCenterY()-5);
        endThirdInput = new LogicCircle(endThirdInputX,endThirdInputY);
        endThirdInput.setVisible(false);
        lineThirdInput = new LogicLine(startThirdInputX,startThirdInputY,endThirdInputX,endThirdInputY);
        Main.getController().getPane().getChildren().addAll(startThirdInput, endThirdInput, lineThirdInput);
    }

    private void setFourInputsAgain() {
        //FirstInput
        DoubleProperty startFirstInputX = new SimpleDoublePosition(serial.getFirstCircleX());
        DoubleProperty startFirstInputY = new SimpleDoublePosition(serial.getFirstCircleY());
        DoubleProperty endFirstInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFirstInputY = new SimpleDoublePosition(node.getLayoutY()+3.5);
        startFirstInput = new LogicCircle(startFirstInputX,startFirstInputY, node.getCurrentGate(), "FirstInput");
        first = new Text();
        first.setText("I" + ++In);
        first.xProperty().bind(startFirstInput.translateXProperty());
        first.yProperty().bind(startFirstInput.translateYProperty());
        first.setLayoutX(startFirstInput.getCenterX());
        first.setLayoutY(startFirstInput.getCenterY()-5);
        endFirstInput = new LogicCircle(endFirstInputX,endFirstInputY);
        endFirstInput.setVisible(false);
        lineFirstInput = new LogicLine(startFirstInputX,startFirstInputY,endFirstInputX,endFirstInputY);
        Main.getController().getPane().getChildren().addAll(startFirstInput, endFirstInput, lineFirstInput);
        
        //SecondInput
        DoubleProperty startSecondInputX = new SimpleDoublePosition(serial.getSecondCircleX());
        DoubleProperty startSecondInputY = new SimpleDoublePosition(serial.getSecondCircleY());
        DoubleProperty endSecondInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endSecondInputY = new SimpleDoublePosition(node.getLayoutY()+17);
        startSecondInput = new LogicCircle(startSecondInputX,startSecondInputY, node.getCurrentGate(), "SecondInput");
        second = new Text();
        second.setText("I" + ++In);
        second.xProperty().bind(startSecondInput.translateXProperty());
        second.yProperty().bind(startSecondInput.translateYProperty());
        second.setLayoutX(startSecondInput.getCenterX());
        second.setLayoutY(startSecondInput.getCenterY()-5);
        endSecondInput = new LogicCircle(endSecondInputX,endSecondInputY);
        endSecondInput.setVisible(false);
        lineSecondInput = new LogicLine(startSecondInputX,startSecondInputY,endSecondInputX,endSecondInputY);
        Main.getController().getPane().getChildren().addAll(startSecondInput, endSecondInput, lineSecondInput);
        
        //ThirdInput
        DoubleProperty startThirdInputX = new SimpleDoublePosition(serial.getThirdCircleX());
        DoubleProperty startThirdInputY = new SimpleDoublePosition(serial.getThirdCircleY());
        DoubleProperty endThirdInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endThirdInputY = new SimpleDoublePosition(node.getLayoutY()+32);
        startThirdInput = new LogicCircle(startThirdInputX,startThirdInputY, node.getCurrentGate(), "ThirdInput");
        third = new Text();
        third.setText("I" + ++In);
        third.xProperty().bind(startThirdInput.translateXProperty());
        third.yProperty().bind(startThirdInput.translateYProperty());
        third.setLayoutX(startThirdInput.getCenterX());
        third.setLayoutY(startThirdInput.getCenterY()-5);
        endThirdInput = new LogicCircle(endThirdInputX,endThirdInputY);
        endThirdInput.setVisible(false);
        lineThirdInput = new LogicLine(startThirdInputX,startThirdInputY,endThirdInputX,endThirdInputY);
        Main.getController().getPane().getChildren().addAll(startThirdInput, endThirdInput, lineThirdInput);
        
        //FourthInput
        DoubleProperty startFourthInputX = new SimpleDoublePosition(serial.getFourthCircleX());
        DoubleProperty startFourthInputY = new SimpleDoublePosition(serial.getFourthCircleY());
        DoubleProperty endFourthInputX = new SimpleDoublePosition(node.getLayoutX()+2);
        DoubleProperty endFourthInputY = new SimpleDoublePosition(node.getLayoutY()+47);
        startFourthInput = new LogicCircle(startFourthInputX,startFourthInputY, node.getCurrentGate(), "FourthInput");
        fourth = new Text();
        fourth.setText("I" + ++In);
        fourth.xProperty().bind(startFourthInput.translateXProperty());
        fourth.yProperty().bind(startFourthInput.translateYProperty());
        fourth.setLayoutX(startFourthInput.getCenterX());
        fourth.setLayoutY(startFourthInput.getCenterY()-5);
        endFourthInput = new LogicCircle(endFourthInputX,endFourthInputY);
        endFourthInput.setVisible(false);
        lineFourthInput = new LogicLine(startFourthInputX,startFourthInputY,endFourthInputX,endFourthInputY);
        Main.getController().getPane().getChildren().addAll(startFourthInput, endFourthInput, lineFourthInput);
    }
    
    private void setOutputAgain() {
        DoubleProperty startX = new SimpleDoublePosition(serial.getOutputCircleX());
        DoubleProperty startY = new SimpleDoublePosition(serial.getOutputCircleY());
        DoubleProperty endX = new SimpleDoublePosition(node.getLayoutX()+95);
        DoubleProperty endY = new SimpleDoublePosition(node.getLayoutY()+25);
        startOutput = new LogicCircle(startX, startY, node.getCurrentGate(), "Output");
        out = new Text();
        out.setText("O" + ++Out);
        out.xProperty().bind(startOutput.translateXProperty());
        out.yProperty().bind(startOutput.translateYProperty());
        out.setLayoutX(startOutput.getCenterX()-10);
        out.setLayoutY(startOutput.getCenterY()-5);
        endOutput = new LogicCircle(endX,endY);
        endOutput.setVisible(false);
        lineOutput = new LogicLine(startX, startY, endX, endY);
        Main.getController().getPane().getChildren().addAll(startOutput, endOutput, lineOutput,out);
    }
    
    private void setImage() {
        switch(num){
            case 1:
                image = "NOT.png";
                break;
            case 2:
                image = serial.getName() + ".png";
                break;
            case 3:
                image = serial.getName() + "THREE.png";
                break;
            case 4:
                image = serial.getName() + "FOUR.png";
                break;
        }
    }
    
    public LogicGate getGate(){
        return node.getCurrentGate();
    }

  }


