package GUI;

import javafx.scene.paint.Color;

public class BlackSquare extends Square {

    public BlackSquare(int[] coor) {
        colour = Color.BLACK;
        this.setFill(colour);
        this.setWidth(GameGUI.squareSize);
        this.setHeight(GameGUI.squareSize);
        relocate(coor[0] * GameGUI.squareSize, coor[1] * GameGUI.squareSize);
    }

    @Override
    public boolean canMoveTo() {
        return true;
    }
}
