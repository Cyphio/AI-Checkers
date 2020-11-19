package Logic.Squares;

import Logic.Checkers.Checker;

public class BlackSquare extends Square {

    private Checker checker;

    public BlackSquare() {
        this.colour = "black";
        checker = null;
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
    public boolean canMoveTo() { return getChecker() == null; }
}
