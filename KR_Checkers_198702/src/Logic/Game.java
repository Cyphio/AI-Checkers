package Logic;

import java.util.ArrayList;

public class Game {

    private GameBoard board;
    private ArrayList<Checker> blacks;
    private ArrayList<Checker> reds;

    public Game() {
        blacks = new ArrayList<>();
        reds = new ArrayList<>();
        for(int i=0; i<12; i++) {
            Checker black = new WhiteChecker();
            blacks.add(black);
            Checker red = new RedChecker();
            reds.add(red);
        }
        board = new GameBoard();
    }

    public GameBoard getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.getBoard().displayBoard();
    }
}
