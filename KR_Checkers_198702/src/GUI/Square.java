package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static GUI.GameGUI.SQUARESIZE;

public class Square extends Rectangle {

    private Color colour;
    private int[] coor;
    private boolean canMoveTo;

    public Square(SquareType type, int[] coor) {
        this.coor = coor;

        if(type == SquareType.BLACK) {
            colour = Color.BLACK;
            setCanMoveTo(true);
        }
        if(type == SquareType.WHITE) {
            colour = Color.WHITE;
            setCanMoveTo(false);
        }

        setFill(colour);
        setWidth(SQUARESIZE);
        setHeight(SQUARESIZE);
        relocate(coor[0] * SQUARESIZE, coor[1] * SQUARESIZE);
    }

    public Color getColour() {
        return colour;
    }

    public int[] getCoor() {
        return coor;
    }

    public void setCanMoveTo(boolean canMoveTo) {
        this.canMoveTo = canMoveTo;
    }

    public boolean canMoveTo() {
        return canMoveTo;
    }
}
