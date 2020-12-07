package GUI;

import java.util.ArrayList;

public class GameState {

    private Checker[][] checkerState;
    private Square[][] squareState;
    private ArrayList<Checker> rCheckers;
    private ArrayList<Checker> bCheckers;
    private ArrayList<Square> wSquares;
    private ArrayList<Square> bSquares;
    private int blackPoints;
    private int redPoints;


    public GameState(int boardSize, ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers, ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        this.rCheckers = rCheckers;
        this.bCheckers = bCheckers;
        this.wSquares = wSquares;
        this.bSquares = bSquares;

        blackPoints = 0;
        redPoints = 0;

        checkerState = new Checker[rCheckers.size()][bCheckers.size()];
        squareState = new Square[wSquares.size()][bSquares.size()];
        for(Square white : wSquares) {
            int[] coor = white.getCoor();
            squareState[coor[0]][coor[1]] = white;
        }
        for(Square black : bSquares) {
            int[] coor = black.getCoor();
            squareState[coor[0]][coor[1]] = black;
        }
        update();
    }

    public ArrayList<Checker> getRCheckers() { return rCheckers; }

    public ArrayList<Checker> getBCheckers() { return bCheckers; }

    public ArrayList<Square> getWSquares() { return wSquares; }

    public ArrayList<Square> getBSquares() { return bSquares; }

    public int getRedPoints() { return redPoints; }

    public void incrementRedPoints() {
        redPoints++;
    }

    public int getBlackPoints() { return blackPoints; }

    public void incrementBlackPoints() {
        blackPoints++;
    }

    public void update() {
        for(Square black : bSquares) {
            int[] coor = black.getCoor();
            squareState[coor[0]][coor[1]].setCanMoveTo(true);
        }
        for(Checker red : rCheckers) {
            int[] coor = red.getCurrCoor();
            checkerState[coor[0]][coor[1]] = red;
            squareState[coor[0]][coor[1]].setCanMoveTo(false);
        }
        for(Checker black : bCheckers) {
            int[] coor = black.getCurrCoor();
            checkerState[coor[0]][coor[1]] = black;
            squareState[coor[0]][coor[1]].setCanMoveTo(false);
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

    public void removeCheckerFromGame(Checker checker) {
        if(checker.getType() == CheckerType.BLACK) {
            bCheckers.remove(checker);
        }
        else if(checker.getType() == CheckerType.RED) {
            rCheckers.remove(checker);
        }
    }

    public Square[][] getSquareState() {
        return squareState;
    }

    public Square getSquareAt(int[] coor) {
        return squareState[coor[0]][coor[1]];
    }

    public boolean isComplete() {
        return blackPoints == rCheckers.size() || redPoints == bCheckers.size();
    }
}
