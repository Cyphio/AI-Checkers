package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

import static GUI.GameGUI.SQUARESIZE;

public class Square extends Rectangle implements Serializable {

    private int[] coor;
    private boolean canMoveTo;

    public Square(SquareType type, int[] coor) {
        this.coor = coor;

        if(type == SquareType.BLACK) {
            setFill(Color.BLACK);
            setCanMoveTo(true);
        }
        if(type == SquareType.WHITE) {
            setFill(Color.WHITE);
            setCanMoveTo(false);
        }

        setWidth(SQUARESIZE);
        setHeight(SQUARESIZE);
        relocate(coor[0] * SQUARESIZE, coor[1] * SQUARESIZE);
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
