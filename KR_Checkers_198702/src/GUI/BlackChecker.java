package GUI;

import javafx.scene.paint.Color;

public class BlackChecker extends Checker {

    public BlackChecker(int[] coor, double size) {
        create(Color.BLACK, Color.WHITE, coor, size);
        isKing = false;
        moveCoors = new int[][]{{1, -1}, {1, 1}};
        jumpCoors = new int[][]{{2, -2}, {2, 2}};
    }

    @Override
    public Color getColour() { return colour; }

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
