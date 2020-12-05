package GUI;

import Logic.Checkers.Checker;
import javafx.scene.paint.Color;

public class WhiteSquare extends Square {

    public WhiteSquare(int[] coor) {
        colour = Color.WHITE;
        this.setFill(colour);
        this.setWidth(GameGUI.squareSize);
        this.setHeight(GameGUI.squareSize);
        relocate(coor[0] * GameGUI.squareSize, coor[1] * GameGUI.squareSize);
        checker = null;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public boolean setChecker(Checker checker) {
        return false;
    }

    @Override
    public boolean removeChecker() {
        return false;
    }

    @Override
    public Checker getChecker() {
        return null;
    }

    @Override
    public boolean canMoveTo() {
        return false;
    }
}
