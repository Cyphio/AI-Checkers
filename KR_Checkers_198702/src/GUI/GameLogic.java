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

    public boolean move(Checker checker, int[] newCoor) {
        int[] currCoor = checker.getCurrCoor();
        if(state.getSquareAt(newCoor).canMoveTo()) {
            if (isLegalMove(currCoor, newCoor)) {
                state.removeCheckerAt(currCoor);
                checker.setCurrCoor(newCoor);
                state.updateCheckers();
                tryForcedCapture(checker, newCoor);
                return true;
            } else if (isLegalJump(currCoor, newCoor)) {
                capture(currCoor, newCoor);
                return true;
            }
        }
        return false;
    }

    private boolean isLegalMove(int[] currCoor, int[] newCoor) {
        Checker checker = state.getCheckerAt(currCoor);
        int[] proposedMoveCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for(int i : proposedMoveCoor) {
            System.out.println("MOVE: " + i);
        }
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
        for(int i : proposedJumpCoor) {
            System.out.println("JUMP: " + i);
        }
        try {
            for (int[] jumpCoor : checker.getJumpCoors()) {
                if (Arrays.equals(jumpCoor, proposedJumpCoor)) {
                    System.out.println("CALLED");
                    Checker midChecker = state.getCheckerAt(new int[]{(currCoor[0] + newCoor[0]) / 2, (currCoor[1] + newCoor[1]) / 2});
                    if (midChecker == null) { return false; }
                    boolean x = (!midChecker.getColour().equals(checker.getColour()));
                    System.out.println("X " +  x);
                    return x;
                }
            }
        } catch(Exception e) { e.getMessage(); }
        return false;
    }

    private boolean tryForcedCapture(Checker checker, int[] currCoor) {
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
        state.updateCheckers();
        if(checker.getColour() == Color.BLACK) { state.incrementBlackPoints(); }
        else if (checker.getColour() == Color.RED) { state.incrementRedPoints(); }
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
