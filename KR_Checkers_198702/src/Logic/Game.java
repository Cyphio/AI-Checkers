package Logic;

import Logic.Checkers.Checker;
import Logic.Checkers.RedChecker;
import Logic.Checkers.BlackChecker;
import Logic.Squares.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private GameBoard board;
    private int nCheckers;
    private ArrayList<Checker> blacks;
    private ArrayList<Checker> reds;
    private int blackPoints;
    private int redPoints;

    public Game(int boardSize, int nCheckers) {
        this.nCheckers = nCheckers;
        board = new GameBoard(boardSize);
        blackPoints = 0;
        redPoints = 0;

        blacks = new ArrayList<>();
        reds = new ArrayList<>();
        for(int i=0; i<nCheckers; i++) {
            blacks.add(new BlackChecker());
            reds.add(new RedChecker());
        }

        board.setUpCheckers(blacks, reds);
    }

    public GameBoard getBoard() { return board; }

    public int getBlackPoints() { return blackPoints; }

    public int getRedPoints() { return redPoints; }

    public boolean move(int[] currCoor, int[] newCoor) {
        Checker checker = board.getSquareAt(currCoor).getChecker();
        if(board.getSquareAt(newCoor).canMoveTo() && isLegalMove(currCoor, newCoor)) {
            tryForcedCapture(currCoor);
            board.getSquareAt(currCoor).removeChecker();
            board.setPosOfChecker(checker, newCoor);
            return true;
        } else if(isLegalJump(currCoor, newCoor)) {
            capture(currCoor, newCoor);
        }
        return false;
    }

    private boolean isLegalJump(int[] currCoor, int[] newCoor) {
        Checker checker = board.getSquareAt(currCoor).getChecker();
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

    private boolean tryForcedCapture(int[] currCoor) {
        Checker checker = board.getSquareAt(currCoor).getChecker();
        if(checker == null) { return false; }
        for (int[] jumpCoor : checker.getJumpCoors()) {
            int[] newCoor = new int[]{currCoor[0] + jumpCoor[0], currCoor[1] + jumpCoor[1]};
            if ((newCoor[0] >= 0 && newCoor[0] < board.getSize() - 1) && (newCoor[1] >= 0 && newCoor[1] < board.getSize() - 1)) {
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
        Checker checker = board.getSquareAt(currCoor).getChecker();
        board.getSquareAt(currCoor).removeChecker();
        board.setPosOfChecker(checker, newCoor);
        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        Square midSquare = board.getSquareAt(midCoor);
        board.getSquareAt(midCoor).removeChecker();
        if(checker.getColour().equals("black")) { blackPoints++; }
        else if (checker.getColour().equals("red")) { redPoints++; }
    }

    private boolean isLegalMove(int[] currCoor, int[] newCoor) {
        Checker checker = board.getSquareAt(currCoor).getChecker();
        int[] proposedMoveCoor = new int[]{newCoor[0] - currCoor[0], newCoor[1] - currCoor[1]};
        for (int[] moveCoor : checker.getMoveCoors()) {
            if(Arrays.equals(moveCoor, proposedMoveCoor)) { return true; }
        }
        return false;
    }

    public boolean isComplete() {
        return blackPoints == nCheckers || redPoints == nCheckers;
    }

    public static void main(String[] args) {
        Game g = new Game(8, 12);
        System.out.println(g.move(new int[]{2, 0}, new int[]{3, 1}));
        g.getBoard().displayBoardAsString();

        System.out.println(g.move(new int[]{5, 3}, new int[]{4, 2}));
        g.getBoard().displayBoardAsString();

        g.tryForcedCapture(new int[]{4, 2});
        g.getBoard().displayBoardAsString();
    }
}
