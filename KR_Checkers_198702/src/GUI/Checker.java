package GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.io.Serializable;

import static GUI.GameGUI.SQUARESIZE;

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

    public Checker(CheckerType type, int[] coor, double size) {
        this.type = type;

        isKing = false;
        kingMoveCoors = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};

        if(type == CheckerType.RED) {
            create(Color.RED, Color.WHITE, coor, size);
            moveCoors = new int[][]{{-1, 1}, {1, 1}};
            jumpCoors = new int[][]{{-2, 2}, {2, 2}};
        }
        if(type == CheckerType.BLACK) {
            create(Color.BLACK, Color.WHITE, coor, size);
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

    public void create(Color colour, Color secColour, int[] coor, double size) {
        currCoor = coor;

        relocate(SQUARESIZE + SQUARESIZE / 2, SQUARESIZE + SQUARESIZE / 2);

        Ellipse checkerPiece = new Ellipse(SQUARESIZE * size, SQUARESIZE * size);
        checkerPiece.setFill(colour);
        checkerPiece.setStroke(secColour);
        checkerPiece.setStrokeWidth(3);

        checkerPiece.setTranslateX((SQUARESIZE - checkerPiece.getRadiusX() * 2) / 2);
        checkerPiece.setTranslateY((SQUARESIZE - checkerPiece.getRadiusY() * 2) / 2);

        getChildren().add(checkerPiece);

        setOnMousePressed(e -> {
            mouseCoor = new double[]{e.getSceneX(), e.getSceneY()};
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseCoor[0] + (currCoor[0] * SQUARESIZE), e.getSceneY() - mouseCoor[1] + (currCoor[1] * SQUARESIZE));
        });

        setOnMouseReleased(e -> {
            if(isAtRisk()) {
                checkerPiece.setStroke(Color.GREENYELLOW);
            } else {
                if(isKing) { checkerPiece.setStroke(Color.GOLD); }
                else { checkerPiece.setStroke(Color.WHITE); }
            }
        });
    }
}