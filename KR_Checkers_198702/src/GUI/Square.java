package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Square extends Rectangle {

    Color colour;

    public Color getColour() {
        return colour;
    }

    public abstract boolean canMoveTo();
}
