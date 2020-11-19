package Logic.Squares;

import Logic.Checkers.Checker;

public class WhiteSquare extends Square {

    public WhiteSquare() {
        this.colour = "white";
    }

    @Override
    public void setChecker(Checker checker) { }

    @Override
    public Checker getChecker() { return null; }

    @Override
    public Boolean canMoveTo() { return false; }
}
