package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Square extends Rectangle {

    Color colour;
    int[] coor;

    public Color getColour() {
        return colour;
    }

    public int[] getCoor() {
        return coor;
    }

    public abstract boolean canMoveTo();
}
