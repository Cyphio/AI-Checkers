package GUI;

import javafx.scene.layout.StackPane;

import java.io.Serializable;

public class Checker extends StackPane implements Serializable {

    private CheckerType type;
    //private transient Ellipse checkerPiece;
    private boolean atRisk;
    private int[] currCoor;
    private double[] mouseCoor;
    private boolean isKing;
    private int[][] moveCoors;
    private int[][] jumpCoors;
    private int[][] kingMoveCoors;
    private int[][] kingJumpCoors;

    public Checker(CheckerType type, int[] currCoor) {
        this.type = type;
        this.currCoor = currCoor;

        isKing = false;
        kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};

        if(type == CheckerType.RED) {
            moveCoors = new int[][]{{-1, 1}, {1, 1}};
            jumpCoors = new int[][]{{-2, 2}, {2, 2}};
        }
        if(type == CheckerType.BLACK) {
            moveCoors = new int[][]{{-1, -1}, {1, -1}};
            jumpCoors = new int[][]{{-2, -2}, {2, -2}};
        }
    }

    public CheckerType getType() { return type; }

    public int[] getCurrCoor() {
        return currCoor;
    }

    public void setCurrCoor(int[] coor) {
        currCoor = coor;
    }

    public void setMouseCoor(double[] mouseCoor) { this.mouseCoor = mouseCoor; }

    public double[] getMouseCoor() { return mouseCoor; }

    public int[][] getMoveCoors() {
        if(isKing) { return kingMoveCoors; }
        else { return moveCoors; }
    }

    public int[][] getJumpCoors() {
        if(isKing) { return kingJumpCoors; }
        else { return jumpCoors; }
    }

    public void setAtRisk() {
        atRisk = true;
    }

    public void removeAtRisk() {
        atRisk = false;

    }

    public boolean isAtRisk() {
        return atRisk;
    }

    public void turnIntoKing() {
        isKing = true;
    }

    public boolean getKing() {
        return isKing;
    }
}