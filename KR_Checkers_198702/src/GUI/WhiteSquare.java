package GUI;

import javafx.scene.paint.Color;

public class WhiteSquare extends Square {

    public WhiteSquare(int[] coor) {
        colour = Color.WHITE;
        this.coor = coor;
        this.setFill(colour);
        this.setWidth(GameGUI.squareSize);
        this.setHeight(GameGUI.squareSize);
        relocate(coor[0] * GameGUI.squareSize, coor[1] * GameGUI.squareSize);
    }

    @Override
    public boolean canMoveTo() {
        return false;
    }
}
