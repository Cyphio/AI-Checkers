package GUI.CHECKERS;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import jfxtras.labs.util.event.MouseControlUtil;

import static GUI.GameGUI.squareSize;

public class Checker extends StackPane {

    Type type;
    Color colour;
    int[] currCoor;
    boolean isKing;
    int[][] moveCoors;
    int[][] jumpCoors;
    int[][] kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    int[][] kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

    public Checker(Type type, int[] coor, double size) {
        this.type = type;
        currCoor = coor;
        isKing = false;
        MouseControlUtil.makeDraggable(this);

        if(type == Type.RED) {
            create(Color.RED, Color.WHITE, coor, size);
            moveCoors = new int[][]{{-1, -1}, {-1, 1}};
            jumpCoors = new int[][]{{-2, -2}, {-2, 2}};
        }
        if(type == Type.BLACK) {
            create(Color.BLACK, Color.WHITE, coor, size);
            moveCoors = new int[][]{{1, -1}, {1, 1}};
            jumpCoors = new int[][]{{2, -2}, {2, 2}};
        }
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