package Logic;

import java.util.ArrayList;

public class GameBoard {

    private Square[][] board;

    public GameBoard() {
        board = new Square[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if((i+j)%2 == 0) {
                    board[i][j] = new BlackSquare();
                } else {
                    board[i][j] = new WhiteSquare();
                }
            }
        }
    }

    public boolean placeCheckerOnBoard(int x, int y, Checker checker) {
        board[x][y].setChecker(checker);
        return board[x][y].canMoveTo();
    }

    public Checker getCheckerAt(int x, int y) {
        return board[x][y].getChecker();
    }

    public void displayBoard() {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                System.out.print(board[i][j].getColour() + " ");
            }
            System.out.println();
        }
    }
}
