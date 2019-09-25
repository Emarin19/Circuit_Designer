
package cr.ac.tec.circuitdesigner.draw;

import java.io.Serializable;
import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Emanuel Marín
 */
public class LogicLine extends Line implements Serializable {
    
    LogicLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);
      setStrokeWidth(2);
      setStroke(randomColor());
      setStrokeLineCap(StrokeLineCap.BUTT);
      setMouseTransparent(true);
    }
    
    public Paint randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }
}
