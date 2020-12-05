package GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static GUI.GameGUI.squareSize;

public abstract class Checker extends StackPane {

    Color colour;
    int[] currCoor;
    boolean isKing;
    int[][] moveCoors;
    int[][] jumpCoors;
    int[][] kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    int[][] kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

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
        piece.setStrokeWidth(1);

        piece.setTranslateX((squareSize - squareSize * size * 2) / 2);
        piece.setTranslateY((squareSize - squareSize * size * 2) / 2);
        getChildren().addAll(piece);
    }
}