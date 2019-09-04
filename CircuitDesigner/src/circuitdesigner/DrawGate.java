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
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;


/**
 *
 * @author Emanuel
 */
public class DrawGate {
    
    Line currentLine;
    private String image;
    static double X = 100;
    static double Y = 50;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    //AnchorPane base;
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
    
    public AnchorPane setImage(){
        AnchorPane baseT = new AnchorPane();
        try {
            gateImage = new Image(new FileInputStream(getImage()));
            logicGateImage = new ImageView(gateImage);
            logicGateImage.setFitWidth(80);
            logicGateImage.setFitHeight(50);
            
            AnchorPane firstInput = new AnchorPane();
            firstInput.setPrefSize(10, 10);
            firstInput.setStyle(("-fx-background-color: #007e8f;"));
            firstInput.setLayoutX(0);
            firstInput.setLayoutY(8.5);
            firstInput.setOnMouseClicked(e -> {
                System.out.println("FirstInputClicked");
                if (currentLine == null) {
                    currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
                    firstInput.getChildren().add(currentLine);
                } else {
                    currentLine = null ;
                }
            });
            
            firstInput.setOnMouseMoved(e -> {
                if (currentLine != null) {
                    currentLine.setEndX(e.getX());
                    currentLine.setEndY(e.getY());
                }
            });
            
            AnchorPane secondInput = new AnchorPane();
            secondInput.setPrefSize(10, 10);
            secondInput.setStyle(("-fx-background-color: #007e8f;"));
            secondInput.setLayoutX(0);
            secondInput.setLayoutY(32.5);
            
            AnchorPane ouput = new AnchorPane();
            ouput.setPrefSize(10, 10);
            ouput.setStyle(("-fx-background-color: #007e8f;"));
            ouput.setLayoutX(70);
            ouput.setLayoutY(20);
            
            baseT.setPrefSize(80, 50);
            baseT.setLayoutX(X);
            baseT.setLayoutY(Y);
            baseT.getChildren().addAll(logicGateImage,firstInput,secondInput,ouput);
            baseT.setOnMouseClicked(clicked);
            baseT.setOnMousePressed(gateOnMousePressed);
            baseT.setOnMouseDragged(gateOnMouseDragged);
            Y = Y + 100;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrawGate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return baseT;
        
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
            orgTranslateX = ((AnchorPane)(t.getSource())).getTranslateX();
            orgTranslateY = ((AnchorPane)(t.getSource())).getTranslateY();
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
             
            ((AnchorPane)(t.getSource())).setTranslateX(newTranslateX);
            ((AnchorPane)(t.getSource())).setTranslateY(newTranslateY);
        }
    };
    
    public AnchorPane setAnchor(){
        AnchorPane prueba = new AnchorPane();
        //prueba.setPrefSize(80, 50);
        try {
            Image image1 = new Image(new FileInputStream("C:\\Users\\Emanuel\\Desktop\\Circuit_Designer\\CircuitDesigner\\src\\resources\\images\\AND.png"));
            ImageView gateImage1 = new ImageView(image1);
            gateImage1.setFitWidth(80);
            gateImage1.setFitHeight(50);
            gateImage1.setOnMouseClicked(clicked);
            gateImage1.setOnMousePressed(gateOnMousePressed);
            gateImage1.setOnMouseDragged(gateOnMouseDragged);
            prueba.setPrefSize(80, 50);
            prueba.getChildren().add(gateImage1);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrawGate.class.getName()).log(Level.SEVERE, null, ex);
        }
        //prueba.setStyle("-fx-background-color: #008f6d;");
        return prueba;
    }
}
