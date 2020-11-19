package Logic.Squares;

import Logic.Checkers.Checker;

public abstract class Square {

    String colour;

    public String getColour() {
        return colour;
    }

    public abstract boolean setChecker(Checker checker);

    public abstract boolean removeChecker();

    public abstract Checker getChecker();

    public abstract boolean canMoveTo();
}

