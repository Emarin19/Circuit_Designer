
package cr.ac.tec.circuitdesigner.draw;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Emanuel Marín
 */
public class SimpleDoublePosition extends SimpleDoubleProperty implements Serializable {
    
    public SimpleDoublePosition(double value){
        super(value);
    }
    
}
