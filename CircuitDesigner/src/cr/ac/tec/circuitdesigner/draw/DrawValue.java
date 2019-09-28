
package cr.ac.tec.circuitdesigner.draw;

import cr.ac.tec.circuitdesigner.Main;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Emanuel Marín
 */
public class DrawValue {
    private LogicCircle Value;
    private Text textValue;
    
    public DrawValue(){}
    
    public void setValue(boolean value){
        textValue = new Text();
        if(value){
            DoubleProperty circleX = new SimpleDoubleProperty(200);
            DoubleProperty circleY = new SimpleDoubleProperty(20);
            textValue.setText("1");
            Value = new LogicCircle(circleX, circleY, "Valor", textValue);
        }
        else{
            DoubleProperty circleX = new SimpleDoubleProperty(250);
            DoubleProperty circleY = new SimpleDoubleProperty(20);
            textValue.setText("0");
            Value = new LogicCircle(circleX, circleY, "Valor", textValue);
        }
        Value.setUserData(value);
        textValue.setFill(Color.WHITE);
        textValue.setFont(Font.font("Swis721 BT"));
        textValue.setTextOrigin(VPos.CENTER);
        textValue.xProperty().bind(Value.centerXProperty());
        textValue.yProperty().bind(Value.centerYProperty());
        textValue.setMouseTransparent(true);
        
        Main.getController().getPane().getChildren().addAll(Value,textValue);
    }   
}
