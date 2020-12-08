package GUI;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {

    private final int boardSize;
    private GameState state;


    public GameLogic(int boardSize, ArrayList<Checker> rCheckers, ArrayList<Checker> bCheckers, ArrayList<Square> wSquares, ArrayList<Square> bSquares) {
        this.boardSize = boardSize;
        state = new GameState(boardSize, rCheckers, bCheckers, wSquares, bSquares);
    }

    public GameState getState() {
        return state;
    }

    public void nextTurn(Checker checker, int[] newCoor) {
        if(move(checker, newCoor)) {
            state.update();
        }
        if(isAtBaseline(checker)) {
            checker.turnIntoKing();
        }
    }

    public boolean isAtBaseline(Checker checker) {
        if(checker.getType() == CheckerType.BLACK) {
            return checker.getCurrCoor()[1] == 0;
        }
        else return checker.getCurrCoor()[1] == boardSize - 1;
    }

    public boolean move(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        try {
            if (checker.getType() == state.getWhosTurn() && state.getSquareAt(newCoor).canMoveTo()) {
                if (isLegalMove(currCoor, newCoor)) {
                    state.removeCheckerAt(currCoor);
                    checker.setCurrCoor(newCoor);
                    return true;
                }
                if (isLegalJump(currCoor, newCoor)) {
                    capture(checker, newCoor);
                    return true;
                }
            }
        } catch (Exception e) { e.getMessage(); }
        return false;
    }

    private boolean isLegalMove(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
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
        Checker checker = state.getCheckerAt(currCoor);
        int[] proposedJumpCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        try {
            for (int[] jumpCoor : checker.getJumpCoors()) {
                if (Arrays.equals(jumpCoor, proposedJumpCoor)) {
                    Checker midChecker = state.getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
                    if (midChecker == null) { return false; }
                    return (!midChecker.getColour().equals(checker.getColour()));
                }
            }
        } catch(Exception e) { e.getMessage(); }
        return false;
    }

    private void capture(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        state.removeCheckerAt(currCoor);

        checker.setCurrCoor(newCoor);

        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        state.removeCheckerFromGame(state.getCheckerAt(midCoor));
        state.removeCheckerAt(midCoor);

        if(checker.getColour() == Color.BLACK) { state.incrementBlackPoints(); }
        else if (checker.getColour() == Color.RED) { state.incrementRedPoints(); }
    }

    public boolean tryForcedCapture() {
        ArrayList<Checker> checkers = null;
        if(state.getWhosTurn() == CheckerType.BLACK) { checkers = state.getBCheckers(); }
        else { checkers = state.getRCheckers(); }
        for(Checker checker : checkers) {
            int[] currCoor = checker.getCurrCoor();
            for(int[] jumpCoor : checker.getJumpCoors()) {
                int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
                if((newCoor[0] >= 0 && newCoor[0] <= boardSize-1) && (newCoor[1] >= 0 && newCoor[0] <= boardSize-1)) {
                    if(isLegalJump(currCoor, newCoor) && state.getSquareAt(newCoor).canMoveTo()) {
                        System.out.println("FORCED CAPTURE");
                        capture(checker, newCoor);
                        state.update();
                        state.endTurn();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
