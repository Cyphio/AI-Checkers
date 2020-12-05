package GUI;

import Logic.Checkers.Checker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Square extends Rectangle {

    Checker checker;
    Color colour;

    public abstract Color getColour();

    public abstract boolean setChecker(Checker checker);

    public abstract boolean removeChecker();

    public abstract Checker getChecker();

    public abstract boolean canMoveTo();
}
