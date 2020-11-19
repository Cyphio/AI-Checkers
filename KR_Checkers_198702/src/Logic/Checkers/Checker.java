package Logic.Checkers;

public abstract class Checker {

    String colour;
    boolean isKing;
    int[][] moveCoors;
    int[][] jumpCoors;
    int[][] kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    int[][] kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

    public abstract String getColour();

    public abstract void setKing();

    public abstract boolean getKing();

    public abstract int[][] getMoveCoors();

    public abstract  int[][] getJumpCoors();
}
