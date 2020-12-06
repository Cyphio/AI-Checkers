package GUI.SQUARES;

import javafx.scene.paint.Color;

import static GUI.GameGUI.squareSize;

public class BlackSquare extends Square {

    public BlackSquare(int[] coor) {
        colour = Color.BLACK;
        this.coor = coor;
        this.setFill(colour);
        this.setWidth(squareSize);
        this.setHeight(squareSize);
        relocate(coor[0] * squareSize, coor[1] * squareSize);
    }

    @Override
    public boolean canMoveTo() {
        return true;
    }
}
