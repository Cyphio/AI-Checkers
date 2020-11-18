package Logic;

public abstract class Square {

    String colour;

    public String getColour() {
        return colour;
    }

    public abstract void setChecker(Checker checker);

    public abstract Checker getChecker();

    public abstract Boolean canMoveTo();
}

