package Logic;

import Logic.Checkers.Checker;
import Logic.Checkers.RedChecker;
import Logic.Checkers.BlackChecker;
import Logic.Squares.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private GameBoard board;
    private ArrayList<Checker> blacks;
    private ArrayList<Checker> reds;
    private int blackPoints;
    private int redPoints;

    public Game(int board_size, int n_checkers) {
        board = new GameBoard(board_size);
        blackPoints = 0;
        redPoints = 0;

        blacks = new ArrayList<>();
        reds = new ArrayList<>();
        for(int i=0; i<n_checkers; i++) {
            blacks.add(new BlackChecker());
            reds.add(new RedChecker());
        }

        board.setUpCheckers(blacks, reds);
    }

    public GameBoard getBoard() { return board; }

    public int getBlackPoints() { return blackPoints; }

    public int getRedPoints() { return redPoints; }

    private boolean moveChecker(Checker checker, int[] newCoor) {
        int[] currCoor = board.getPosOfChecker(checker);
        if(board.getSquareAt(newCoor).canMoveTo() && isLegalMove(checker, currCoor, newCoor)) {
            tryForcedCapture(checker);
            board.getSquareAt(currCoor).removeChecker();
            board.setPosOfChecker(checker, newCoor);
            return true;
        } else if(isLegalJump(checker, currCoor, newCoor)) {
            capture(checker, newCoor);
        }
        return false;
    }

    private boolean isLegalJump(Checker checker, int[] currCoor, int[] newCoor) {
        int[] proposedJumpCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for (int[] jumpCoor : checker.getJumpCoors()) {
            if(Arrays.equals(jumpCoor, proposedJumpCoor)) {
                Square midSquare = board.getSquareAt(new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2});
                if(midSquare.getChecker() == null) { return false; }
                return(!midSquare.getChecker().getColour().equals(checker.getColour()));
            }
        }
        return false;
    }

    private boolean tryForcedCapture(Checker checker) {
        int[] currCoor = board.getPosOfChecker(checker);
        if(checker == null) { return false; }
        for (int[] jumpCoor : checker.getJumpCoors()) {
            int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
            if ((newCoor[0] >= 0 && newCoor[0] < board.getSize() - 1) && (newCoor[1] >= 0 && newCoor[1] < board.getSize() - 1)) {
                if(isLegalJump(checker, currCoor, newCoor)) {
                    System.out.println("FORCED CAPTURE");
                    capture(checker, newCoor);
                    return true;
                }
            }
        }
        return false;
    }

    private void capture(Checker checker, int[] newCoor) {
        int[] currCoor = board.getPosOfChecker(checker);
        board.getSquareAt(currCoor).removeChecker();
        board.setPosOfChecker(checker, newCoor);
        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        Square midSquare = board.getSquareAt(midCoor);
        board.getSquareAt(midCoor).removeChecker();
        if(checker.getColour().equals("black")) { blackPoints++; }
        else if (checker.getColour().equals("red")) { redPoints++; }
    }

    private boolean isLegalMove(Checker checker, int[] currCoor, int[] newCoor) {
        int[] proposedMoveCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for (int[] moveCoor : checker.getMoveCoors()) {
            if(Arrays.equals(moveCoor, proposedMoveCoor)) { return true; }
        }
        return false;
    }

    public static void main(String[] args) {
        Game g = new Game(8, 12);
        System.out.println(g.moveChecker(g.getBoard().getSquareAt(new int[]{2, 0}).getChecker(), new int[]{3, 1}));
        g.getBoard().displayBoardAsString();

        System.out.println(g.moveChecker(g.getBoard().getSquareAt(new int[]{5, 3}).getChecker(), new int[]{4, 2}));
        g.getBoard().displayBoardAsString();

        g.tryForcedCapture(g.getBoard().getSquareAt(new int[]{4, 2}).getChecker());
        g.getBoard().displayBoardAsString();
        //System.out.println("IS MOVE CAPTURING? " + g.isCapturingMove(g.getBoard().getSquareAt(new int[]{3, 1}).getChecker(), new int[]{5, 3}));
    }
}
