package Logic;

import Logic.Checkers.Checker;
import Logic.Checkers.RedChecker;
import Logic.Checkers.WhiteChecker;
import Logic.Squares.Square;

import java.util.ArrayList;

public class Game {

    private GameBoard board;
    private ArrayList<Checker> whites;
    private ArrayList<Checker> reds;

    public Game(int board_size, int n_checkers) {
        board = new GameBoard(board_size);

        whites = new ArrayList<>();
        reds = new ArrayList<>();
        for(int i=0; i<n_checkers; i++) {
            whites.add(new WhiteChecker());
            reds.add(new RedChecker());
        }

        board.setUpCheckers(whites, reds);
    }

    public GameBoard getBoard() { return board; }

    private boolean moveChecker(Checker checker, int[] newCoor) {
        int[] currCoor = board.getPosOfChecker(checker);
        if(board.getSquareAt(newCoor).canMoveTo() && isLegalDiagonalMove(checker, currCoor, newCoor)) {
            board.getSquareAt(currCoor).removeChecker();
            board.setPosOfChecker(checker, newCoor);
            return true;
        } else {
            return false;
        }
    }

    private boolean isCapturingMove(Checker checker, int[] newCoor) {
        int[] currCoor = board.getPosOfChecker(checker);
        int[] midCoor = new int[]{(currCoor[0] + newCoor[0])/2, (currCoor[1] + newCoor[1])/2};
        Square midSquare = board.getSquareAt(midCoor);
        if(midSquare.getChecker() == null) { return false; }
        return(!midSquare.getChecker().getColour().equals(checker.getColour()));
    }

    private boolean isLegalDiagonalMove(Checker checker, int[] currCoor, int[] newCoor) {
        if(checker.getColour().equals("white")) {
            return (newCoor[0] - currCoor[0] == 1) && (Math.pow((newCoor[1] - currCoor[1]), 2) == 1);
        }
        else {
            return (newCoor[0] - currCoor[0] == -1) && (Math.pow((newCoor[1] - currCoor[1]), 2) == 1);
        }
    }

    public static void main(String[] args) {
        Game g = new Game(8, 12);
        System.out.println(g.moveChecker(g.getBoard().getSquareAt(new int[]{2, 0}).getChecker(), new int[]{3, 1}));
        g.getBoard().displayBoardAsString();
        System.out.println(g.moveChecker(g.getBoard().getSquareAt(new int[]{5, 3}).getChecker(), new int[]{4, 2}));
        g.getBoard().displayBoardAsString();

        System.out.println("CAN WHITE MAKE A CAPTURING MOVE? " + g.isCapturingMove(g.getBoard().getSquareAt(new int[]{3, 1}).getChecker(), new int[]{5, 3}));
    }
}
