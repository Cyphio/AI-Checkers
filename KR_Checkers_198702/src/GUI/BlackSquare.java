package GUI;

import javafx.scene.paint.Color;

public class BlackSquare extends Square {

    public BlackSquare(int[] coor) {
        colour = Color.BLACK;
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
        this.checker = checker;
        return true;
    }

    @Override
    public boolean removeChecker() {
        checker = null;
        return true;
    }

    @Override
    public Checker getChecker() {
        return checker;
    }

    @Override
    public boolean canMoveTo() {
        return true;
    }
}
