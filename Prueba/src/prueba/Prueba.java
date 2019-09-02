/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Emanuel
 */
public class Prueba extends Application {
    
    Circle circle_Red, circle_Green, circle_Blue, circle;
    Image image;
    ImageView imageview, imageview2;
    AnchorPane center, anchor, anchor2;
    BorderPane borderpane;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    private EventHandler mGateOverRoot = null;
    private EventHandler mGateDragDropped = null;
    private EventHandler mGateOverLeftPane = null;
    private AnchorPane mDragOverGate = null;
     
    @Override
    public void start(Stage primaryStage) {
        
        try {
            image = new Image(new FileInputStream("C:\\Users\\Emanuel\\Desktop\\Circuit_Designer\\Prueba\\src\\AND.png"));
            imageview = new ImageView(image);
            imageview2 =new ImageView(image);
            imageview.setCursor(Cursor.HAND);
            imageview.setX(50);
            imageview.setY(50);
            //imageview.setOnMousePressed(imageOnMousePressedEventHandler);
            //imageview.setOnMouseDragged(imageOnMouseDraggedEventHandler);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        anchor = new AnchorPane();
        anchor.getChildren().add(imageview);
        anchor.setCursor(Cursor.HAND);
        anchor.setId("AND");
        anchor.setOnMousePressed(imageOnMousePressedEventHandler);
        anchor.setOnMouseDragged(imageOnMouseDraggedEventHandler);
        anchor.setVisible(true);
        
        anchor2 = new AnchorPane();
        anchor2.getChildren().add(imageview2);
        anchor2.setCursor(Cursor.HAND);
        anchor2.setOnDragDetected(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                borderpane.setOnDragOver(mGateOverRoot);
                center.setOnDragOver(mGateOverLeftPane);
                center.setOnDragDropped(mGateDragDropped);
                
                
                AnchorPane gate = (AnchorPane) event.getSource();
                mDragOverGate = gate;
                ClipboardContent content = new ClipboardContent();
                content.putString("AND");
                
                mDragOverGate.startDragAndDrop(TransferMode.ANY).setContent(content);
                mDragOverGate.setVisible(true);
                mDragOverGate.setMouseTransparent(true);
                event.consume();
            }
            
        });
        //anchor2.setId("AND");
        //anchor2.setOnMousePressed(imageOnMousePressedEventHandler);
        //anchor2.setOnMouseDragged(imageOnMouseDraggedEventHandler);
        anchor2.setVisible(true);
         
        //Create Circles
        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);
         
        circle_Green = new Circle(50.0f, Color.GREEN);
        circle_Green.setCursor(Cursor.MOVE);
        circle_Green.setCenterX(150);
        circle_Green.setCenterY(150);
        circle_Green.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Green.setOnMouseDragged(circleOnMouseDraggedEventHandler);
         
        circle_Blue = new Circle(50.0f, Color.BLUE);
        circle_Blue.setCursor(Cursor.HAND);
        circle_Blue.setCenterX(150);
        circle_Blue.setCenterY(150);
        //circle_Blue.setTranslateX(300);
        //circle_Blue.setTranslateY(100);
        //circle_Blue.setOnMousePressed(circleOnMousePressedEventHandler);
        //circle_Blue.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        circle_Blue.setOnMouseClicked(circleOnMouseClickedEventHandler);
        
        Button run = new Button("Run");
        run.setOnAction(e->generatetable());
                 
        //Group root = new Group();
        borderpane = new BorderPane();
        AnchorPane center = new AnchorPane();
        center.getChildren().addAll(circle_Red,circle_Green,circle_Blue);
        VBox right = new VBox();
        right.setSpacing(50);
        right.setBorder(Border.EMPTY);
        right.getChildren().addAll(anchor, anchor2);
        borderpane.setCenter(center);
        borderpane.setRight(right);
        borderpane.setBottom(run);
        //root.getChildren().addAll(circle_Red, circle_Green, circle_Blue, anchor, run);
         
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(borderpane, 400,350));
         
        primaryStage.setTitle("java-buddy");
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    EventHandler<MouseEvent> circleOnMouseClickedEventHandler =
        new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t){
            circle = new Circle();
            circle = circle_Blue;
            circle.setOnMousePressed(circleOnMousePressedEventHandler);
            circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            System.out.println("Clicked");
        }
    };
     
    EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
            orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
        }
    };
     
    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((Circle)(t.getSource())).setTranslateX(newTranslateX);
            ((Circle)(t.getSource())).setTranslateY(newTranslateY);
        }
    };
    
    EventHandler<MouseEvent> imageOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((AnchorPane)(t.getSource())).getTranslateX();
            orgTranslateY = ((AnchorPane)(t.getSource())).getTranslateY();
        }
    };
    
    EventHandler<MouseEvent> imageOnMouseDraggedEventHandler = 
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

    private void generatetable() {
        String id = anchor.getId();
        System.out.println("I have a " + id + "gate");
    }
    
    /*private void buildDragHandlers(){
        mGateOverRoot = new EventHandler <DragEvent> (){
            @Override
            public void handle(DragEvent event){
                Point2D p = borderpane.sceneToLocal(event.getSceneX(),event.getSceneY());
                if(!borderpane.boundsInLocalProperty().get().contains(p)){
                    mDragOverGate.
                }
            }
        }   
    }
    
    private void relocoteToPoint(Point2D p){
        //Point2D localCoords = new Point2D(getParent().sceneToLocal(p));
        relocate (
            (int) ()
        )
    }*/
    
}
