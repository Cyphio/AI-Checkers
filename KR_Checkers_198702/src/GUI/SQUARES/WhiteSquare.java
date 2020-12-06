package GUI.SQUARES;

import javafx.scene.paint.Color;

import static GUI.GameGUI.squareSize;

public class WhiteSquare extends Square {

    public WhiteSquare(int[] coor) {
        colour = Color.WHITE;
        this.coor = coor;
        this.setFill(colour);
        this.setWidth(squareSize);
        this.setHeight(squareSize);
        relocate(coor[0] * squareSize, coor[1] * squareSize);
    }

    @Override
    public boolean canMoveTo() {
        return false;
    }
}
