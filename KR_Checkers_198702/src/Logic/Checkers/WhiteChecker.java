package Logic.Checkers;

public class WhiteChecker extends Checker {

    public WhiteChecker() {
        this.colour = "white";
        this.isKing = false;
        this.moveCoors = new int[][]{{1, -1}, {1, 1}};
        this.jumpCoors = new int[][]{{2, -2}, {2, 2}};
    }

    @Override
    public String getColour() { return colour; }

    @Override
    public void setKing() {
        isKing = true;
    }

    @Override
    public boolean getKing() {
        return isKing;
    }

    @Override
    public int[][] getMoveCoors() {
        if(isKing) { return kingMoveCoors; }
        else { return moveCoors; }
    }

    @Override
    public int[][] getJumpCoors() {
        if(isKing) { return kingJumpCoors; }
        else { return jumpCoors; }
    }
}
