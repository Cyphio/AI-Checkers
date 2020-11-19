package Logic.Squares;

import Logic.Checkers.Checker;

public class BlackSquare extends Square {

    private Checker checker;

    public BlackSquare() {
        this.colour = "black";
    }

    @Override
    public void setChecker(Checker checker) { this.checker = checker; }

    @Override
    public Checker getChecker() {
        return checker;
    }

    @Override
    public Boolean canMoveTo() { return true; }
}
