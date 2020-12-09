package GUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GameState implements Serializable {

    private final int boardSize;

    private Checker[][] checkerState;
    private Square[][] squareState;

    private ArrayList<Checker> rCheckers;
    private ArrayList<Checker> bCheckers;
    private ArrayList<Square> wSquares;
    private ArrayList<Square> bSquares;

    private int redLeft;
    private int blackLeft;
    private int nRedKings;
    private int nBlackKings;

    private int blackPoints;
    private int redPoints;

    private CheckerType whosTurn;

    public GameState(int boardSize, ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers, ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        this.boardSize = boardSize;

        this.rCheckers = rCheckers;
        this.bCheckers = bCheckers;
        this.wSquares = wSquares;
        this.bSquares = bSquares;

        redLeft = rCheckers.size();
        blackLeft = bCheckers.size();
        nRedKings = 0;
        nBlackKings = 0;
        redPoints = 0;
        blackPoints = 0;

        whosTurn = CheckerType.BLACK;

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

    public ArrayList<Checker> getCheckers(CheckerType type) {
        if(type == CheckerType.BLACK) { return bCheckers; }
        else { return rCheckers; }
    }

    public ArrayList<Checker> getAllCheckers() {
        ArrayList<Checker> allCheckers = new ArrayList<>();
        allCheckers.addAll(rCheckers);
        allCheckers.addAll(bCheckers);
        return allCheckers;
    }

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

    public void changeTurn() {
        if(whosTurn == CheckerType.BLACK) { whosTurn = CheckerType.RED; }
        else if(whosTurn == CheckerType.RED) { whosTurn = CheckerType.BLACK; }
    }

    public String getWhosTurnName() {
        if(whosTurn == CheckerType.BLACK) { return "Black"; }
        else { return "Red"; }
    }

    public CheckerType getWhosTurn() { return whosTurn; }

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

    public Checker getCheckerAt(int[] coor) {
        return checkerState[coor[0]][coor[1]];
    }

    public void removeCheckerAt(int[] coor) {
        checkerState[coor[0]][coor[1]] = null;
    }

    public void removeCheckerFromGame(Checker checker) {
        if(checker.getType() == CheckerType.BLACK) {
            bCheckers.remove(checker);
            blackLeft--;
        }
        else if(checker.getType() == CheckerType.RED) {
            rCheckers.remove(checker);
            redLeft--;
        }
    }

    public void makeKing(Checker checker) {
        checker.turnIntoKing();
        if(checker.getType() == CheckerType.BLACK) { nBlackKings++; }
        else { nRedKings++; }
    }

    public void regicide(Checker midChecker, Checker checker) {
        makeKing(checker);
        if(midChecker.getType() == CheckerType.BLACK) { nBlackKings--; }
        else { nRedKings--; }
    }

    public Square getSquareAt(int[] coor) {
        return squareState[coor[0]][coor[1]];
    }

    public boolean isAtBaseline(Checker checker) {
        if(checker.getType() == CheckerType.BLACK) {
            return checker.getCurrCoor()[1] == 0;
        }
        else return checker.getCurrCoor()[1] == boardSize - 1;
    }

    public void takeTurn(Checker checker, int[] newCoor) {
        if (tryMove(checker, newCoor)) {
            if (isAtBaseline(checker)) {
                makeKing(checker);
            }

            if(isComplete()) {
                System.out.println(getWhosTurnName() + " wins!!!");
            }

            changeTurn();
        }
    }

    public boolean tryMove(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        try {
            if (checker.getType() == getWhosTurn() && getSquareAt(newCoor).canMoveTo()) {
                if (isLegalMove(currCoor, newCoor)) {
                    removeCheckerAt(currCoor);
                    checker.setCurrCoor(newCoor);
                    update();
                    return true;
                }
                if (isLegalJump(currCoor, newCoor)) {
                    capture(checker, newCoor);
                    update();
                    return true;
                }
            }
        } catch (Exception e) { e.getMessage(); }
        return false;
    }

    private boolean isLegalMove(int[] currCoor, int[] newCoor) {
        Checker checker = getCheckerAt(currCoor);
        int[] proposedMoveCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        try {
            for (int[] moveCoor : checker.getMoveCoors()) {
                if (Arrays.equals(moveCoor, proposedMoveCoor)) {
                    return true;
                }
            }
        } catch(Exception e) { e.getMessage(); }
        return false;
    }

    private boolean isLegalJump(int[] currCoor, int[] newCoor) {
        Checker checker = getCheckerAt(currCoor);
        int[] proposedJumpCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        if(newCoor[0] >= 0 && newCoor[0] < boardSize && newCoor[1] >= 0 && newCoor[1] < boardSize) {
            for (int[] jumpCoor : checker.getJumpCoors()) {
                if (Arrays.equals(jumpCoor, proposedJumpCoor)) {
                    Checker midChecker = getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
                    if (midChecker == null) {
                        return false;
                    }
                    return (midChecker.getType() != checker.getType());
                }
            }
        }
        return false;
    }

    private void capture(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        removeCheckerAt(currCoor);
        checker.setCurrCoor(newCoor);

        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        if(getCheckerAt(midCoor).getKing()) {
            regicide(getCheckerAt(midCoor), checker);
        }
        removeCheckerFromGame(getCheckerAt(midCoor));
        removeCheckerAt(midCoor);

        if(checker.getType() == CheckerType.BLACK) { incrementBlackPoints(); }
        else if (checker.getType() == CheckerType.RED) { incrementRedPoints(); }
    }

//    public void markCheckersAtRisk() {
//        // Iterate through each checker & check whether they pose a risk of capture to any other checker.
//        ArrayList<Checker> checkers = getCheckers(getWhosTurn());
//        for (Checker checker : checkers) {
//            int[] currCoor = checker.getCurrCoor();
//            for (int[] jumpCoor : checker.getJumpCoors()) {
//                int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
//                if (isLegalJump(currCoor, newCoor) && getSquareAt(newCoor).canMoveTo()) {
//                    Checker midChecker = getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
//                    // set the checker at risk of being captured to at risk - changes the outline of the checker
//                    midChecker.setAtRisk();
//                }
//            }
//        }
//    }
//
//    public void unMarkCheckersAtRisk() {
//        ArrayList<Checker> checkers = getAllCheckers();
//        for(Checker checker : checkers) {
//            checker.removeAtRisk();
//        }
//    }

    public ArrayList<int[]> getAllValidMoves(Checker checker) {
        ArrayList<int[]> validMoves = new ArrayList<>();

        int[] currCoor = checker.getCurrCoor();

        for (int[] moveCoor : checker.getMoveCoors()) {
            int[] newCoor = new int[]{currCoor[0] + moveCoor[0], currCoor[1] + moveCoor[1]};
            if(newCoor[0] >= 0 && newCoor[0] < boardSize && newCoor[1] >= 0 && newCoor[1] < boardSize) {
                if (isLegalMove(currCoor, newCoor) && getSquareAt(newCoor).canMoveTo()) {
                    validMoves.add(moveCoor);
                }
            }
        }

        for (int[] jumpCoor : checker.getJumpCoors()) {
            int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
            if(newCoor[0] >= 0 && newCoor[0] < boardSize && newCoor[1] >= 0 && newCoor[1] < boardSize) {
                if (isLegalJump(currCoor, newCoor) && getSquareAt(newCoor).canMoveTo()) {
                    validMoves.add(jumpCoor);
                }
            }
        }

        return validMoves;
    }

    public boolean isComplete() {
        return redLeft == 0 || blackLeft == 0;
    }

    public CheckerType winner() {
        if(redLeft <= 0) { return CheckerType.RED; }
        else if(blackLeft <= 0) { return CheckerType.BLACK; }
        return null;
    }

    // Evaluation function quantifies red performance: the more black checkers captured & the more red kings, the higher
    // the fitness of the AI.
    public double evaluateFitness() {
        return redLeft - blackLeft + (nRedKings * 0.5 - nBlackKings * 0.5);
    }
}