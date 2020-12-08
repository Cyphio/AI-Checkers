package GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static GUI.GameGUI.SQUARESIZE;

public class Checker extends StackPane {

    private CheckerType type;
    private Ellipse checkerPiece;
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
        kingJumpCoors = new int[][]{{-2, -2}, {-2, 2}, {2, -2}, {-2, -2}};

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
        checkerPiece.setStroke(Color.GREENYELLOW);
    }

    public void removeAtRisk() {
        if(isKing) { checkerPiece.setStroke(Color.GOLD); }
        else { checkerPiece.setStroke(Color.WHITE); }
    }

    public void turnIntoKing() {
        isKing = true;
        checkerPiece.setStroke(Color.GOLD);
    }

    public boolean getKing() {
        return isKing;
    }

    public void create(Color colour, Color secColour, int[] coor, double size) {
        currCoor = coor;

        relocate(SQUARESIZE + SQUARESIZE / 2, SQUARESIZE + SQUARESIZE / 2);

        checkerPiece = new Ellipse(SQUARESIZE * size, SQUARESIZE * size);
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
    }
}