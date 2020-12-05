package GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static GUI.GameGUI.squareSize;

public abstract class Checker extends StackPane {

    Color colour;
    double size;
    boolean isKing;
    int[][] moveCoors;
    int[][] jumpCoors;
    int[][] kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    int[][] kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

    public abstract Color getColour();

    public abstract void setKing();

    public abstract boolean getKing();

    public abstract int[][] getMoveCoors();

    public abstract  int[][] getJumpCoors();

    public void create(Color mainColour, Color secColour, int[] coor, double size) {
        this.size = size;
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
