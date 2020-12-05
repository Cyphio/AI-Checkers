package GUI;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private final int boardSize;
    private ArrayList<Checker> rCheckers;
    private ArrayList<Checker> bCheckers;
    private int blackPoints;
    private int redPoints;
    private GameState state;

    public Game(int boardSize, ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers, ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        this.boardSize = boardSize;
        blackPoints = 0;
        redPoints = 0;
        this.bCheckers = rCheckers;
        this.rCheckers = bCheckers;
        state = new GameState(boardSize, rCheckers, bCheckers, wSquares, bSquares);
    }

    public ArrayList<Checker> getrCheckers() { return rCheckers; }

    public ArrayList<Checker> getbCheckers() { return bCheckers; }

    public int getBlackPoints() { return blackPoints; }

    public int getRedPoints() { return redPoints; }

    public boolean move(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        if(state.getSquareAt(newCoor).canMoveTo() && isLegalMove(currCoor, newCoor)) {
            tryForcedCapture(currCoor);
            state.removeCheckerAt(currCoor);
            checker.setCurrCoor(newCoor);
            return true;
        } else if(isLegalJump(currCoor, newCoor)) {
            capture(currCoor, newCoor);
        }
        return false;
    }

    private boolean isLegalJump(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        int[] proposedJumpCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for (int[] jumpCoor : checker.getJumpCoors()) {
            if(Arrays.equals(jumpCoor, proposedJumpCoor)) {
                Checker midChecker = state.getCheckerAt(new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2});
                if(midChecker == null) { return false; }
                return(!midChecker.getColour().equals(checker.getColour()));
            }
        }
        return false;
    }

    private boolean tryForcedCapture(int[] currCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        if(checker == null) { return false; }
        for (int[] jumpCoor : checker.getJumpCoors()) {
            int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
            if ((newCoor[0] >= 0 && newCoor[0] < boardSize - 1) && (newCoor[1] >= 0 && newCoor[1] < boardSize - 1)) {
                if(isLegalJump(currCoor, newCoor)) {
                    System.out.println("FORCED CAPTURE");
                    capture(currCoor, newCoor);
                    return true;
                }
            }
        }
        return false;
    }

    private void capture(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        state.removeCheckerAt(currCoor);
        checker.setCurrCoor(newCoor);
        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        state.removeCheckerAt(midCoor);
        state.updateCheckers(rCheckers, bCheckers);
        if(checker.getColour() == Color.BLACK) { blackPoints++; }
        else if (checker.getColour() == Color.RED) { redPoints++; }
    }

    private boolean isLegalMove(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        int[] proposedMoveCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for (int[] moveCoor : checker.getMoveCoors()) {
            if(Arrays.equals(moveCoor, proposedMoveCoor)) { return true; }
        }
        return false;
    }

    public boolean isComplete() {
        return blackPoints == rCheckers.size() || redPoints == bCheckers.size();
    }
//
//    public static void main(String[] args) {
//        Game g = new Game(8, 12);
//        System.out.println(g.move(new int[]{2, 0}, new int[]{3, 1}));
//        g.getBoard().displayBoardAsString();
//
//        System.out.println(g.move(new int[]{5, 3}, new int[]{4, 2}));
//        g.getBoard().displayBoardAsString();
//
//        g.tryForcedCapture(new int[]{4, 2});
//        g.getBoard().displayBoardAsString();
//    }
}
