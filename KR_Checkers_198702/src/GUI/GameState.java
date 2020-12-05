package GUI;

import java.util.ArrayList;

public class GameState {

    private Checker[][] checkerState;
    private Square[][] squareState;

    public GameState(int boardSize, ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers, ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        checkerState = new Checker[rCheckers.size()][bCheckers.size()];
        squareState = new Square[wSquares.size()][bSquares.size()];
        updateCheckers(rCheckers, bCheckers);
        updateSquares(wSquares, bSquares);
    }

    public void updateCheckers(ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers) {
        for(Checker red : rCheckers) {
            int[] coor = red.getCurrCoor();
            checkerState[coor[0]][coor[1]] = red;
        }
        for(Checker black : bCheckers) {
            int[] coor = black.getCurrCoor();
            checkerState[coor[0]][coor[1]] = black;
        }
    }

    public Checker[][] getCheckerState() {
        return checkerState;
    }

    public Checker getCheckerAt(int[] coor) {
        return checkerState[coor[0]][coor[1]];
    }

    public void removeCheckerAt(int[] coor) {
        checkerState[coor[0]][coor[1]] = null;
    }

    public void updateSquares(ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        for(Square white : wSquares) {
            int[] coor = white.getCoor();
            squareState[coor[0]][coor[1]] = white;
        }
        for(Square black : bSquares) {
            int[] coor = black.getCoor();
            squareState[coor[0]][coor[1]] = black;
        }
    }

    public Square[][] getSquareState() {
        return squareState;
    }

    public Square getSquareAt(int[] coor) {
        return squareState[coor[0]][coor[1]];
    }
}
