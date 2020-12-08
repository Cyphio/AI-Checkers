package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    public void takeTurn(Checker checker, int[] newCoor) {
        if (tryMove(checker, newCoor)) {
            if (isAtBaseline(checker)) {
                checker.turnIntoKing();
            }
            state.endTurn();
            resetRisk();
            getCheckersAtRisk();
            if(state.isComplete()) {
                System.out.println(state.getWhosTurnName() + "wins!!!");
            }
        }
    }

    public boolean isAtBaseline(Checker checker) {
        if(checker.getType() == CheckerType.BLACK) {
            return checker.getCurrCoor()[1] == 0;
        }
        else return checker.getCurrCoor()[1] == boardSize - 1;
    }

    public boolean tryMove(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        try {
            if (checker.getType() == state.getWhosTurn() && state.getSquareAt(newCoor).canMoveTo()) {
                if (isLegalMove(currCoor, newCoor)) {
                    state.removeCheckerAt(currCoor);
                    checker.setCurrCoor(newCoor);
                    state.update();
                    return true;
                }
                if (isLegalJump(currCoor, newCoor)) {
                    capture(checker, newCoor);
                    state.update();
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
        if(newCoor[0] >= 0 && newCoor[0] < boardSize && newCoor[1] >= 0 && newCoor[1] < boardSize) {
            for (int[] jumpCoor : checker.getJumpCoors()) {
                if (Arrays.equals(jumpCoor, proposedJumpCoor)) {
                    Checker midChecker = state.getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
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
        state.removeCheckerAt(currCoor);
        checker.setCurrCoor(newCoor);

        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        if(state.getCheckerAt(midCoor).getKing()) {
            checker.turnIntoKing();
        }
        state.removeCheckerFromGame(state.getCheckerAt(midCoor));
        state.removeCheckerAt(midCoor);

        if(checker.getType() == CheckerType.BLACK) { state.incrementBlackPoints(); }
        else if (checker.getType() == CheckerType.RED) { state.incrementRedPoints(); }
    }

//    private boolean tryForcedCapture() {
//        ArrayList<Checker> checkers = null;
//        if(state.getWhosTurn() == CheckerType.BLACK) { checkers = state.getBCheckers(); }
//        else { checkers = state.getRCheckers(); }
//
//        HashMap<Checker, int[]> checkersAtRisk = getCheckersAtRisk(checkers);
//
//
//
//    }

    private void resetRisk() {
        for(Checker checker : state.getCheckers()) {
            if(checker.isAtRisk()) { checker.removeAtRisk(); }
        }
    }

    private HashMap<Checker, int[]> getCheckersAtRisk() {
        ArrayList<Checker> checkers = null;
        if(state.getWhosTurn() == CheckerType.BLACK) { checkers = state.getBCheckers(); }
        else { checkers = state.getRCheckers(); }

        HashMap<Checker, int[]> checkersAtRisk = new HashMap<>();
        for(Checker checker : checkers) {
            int[] currCoor = checker.getCurrCoor();
            for(int[] jumpCoor : checker.getJumpCoors()) {
                int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
                if(isLegalJump(currCoor, newCoor) && state.getSquareAt(newCoor).canMoveTo()) {
                    Checker midChecker = state.getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
                    checkersAtRisk.put(midChecker, currCoor);
                    midChecker.setAtRisk();
                }
            }
        }
        return checkersAtRisk;
    }
}
