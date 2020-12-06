package GUI.CHECKERS;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import jfxtras.labs.util.event.MouseControlUtil;

import static GUI.GameGUI.squareSize;

public class Checker extends StackPane {

    private CheckerType type;
    private Color colour;
    private int[] currCoor;
    private boolean isKing;
    private int[][] moveCoors;
    private int[][] jumpCoors;
    private int[][] kingMoveCoors;
    private int[][] kingJumpCoors;

    public Checker(CheckerType type, int[] coor, double size) {
        this.type = type;
        currCoor = coor;
        isKing = false;

        kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

        if(type == CheckerType.RED) {
            create(Color.RED, Color.WHITE, coor, size);
            moveCoors = new int[][]{{-1, -1}, {-1, 1}};
            jumpCoors = new int[][]{{-2, -2}, {-2, 2}};
        }
        if(type == CheckerType.BLACK) {
            create(Color.BLACK, Color.WHITE, coor, size);
            moveCoors = new int[][]{{1, -1}, {1, 1}};
            jumpCoors = new int[][]{{2, -2}, {2, 2}};
        }

        MouseControlUtil.makeDraggable(this);
    }

    public Color getColour() { return colour; }

    public int[] getCurrCoor() {
        return currCoor;
    }

    public void setCurrCoor(int[] coor) {
        currCoor = coor;
    }

    public int[][] getMoveCoors() {
        if(isKing) { return kingMoveCoors; }
        else { return moveCoors; }
    }

    public int[][] getJumpCoors() {
        if(isKing) { return kingJumpCoors; }
        else { return jumpCoors; }
    }

    public void setKing() {
        isKing = true;
    }

    public boolean getKing() {
        return isKing;
    }

    public void create(Color mainColour, Color secColour, int[] coor, double size) {
        colour = mainColour;
        relocate(coor[0] * squareSize, coor[1] * squareSize);

        Ellipse piece = new Ellipse(squareSize*size, squareSize*size);
        piece.setFill(mainColour);
        piece.setStroke(secColour);
        piece.setStrokeWidth(3);

        piece.relocate(coor[0] * squareSize, coor[1] * squareSize);
        getChildren().addAll(piece);
    }
}