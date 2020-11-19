package Logic.Squares;

import Logic.Checkers.Checker;

public class WhiteSquare extends Square {

    public WhiteSquare() {
        this.colour = "white";
    }

    @Override
    public boolean setChecker(Checker checker) { return false; }

    @Override
    public boolean removeChecker() { return false; }

    @Override
    public Checker getChecker() { return null; }

    @Override
    public boolean canMoveTo() { return false; }
}
