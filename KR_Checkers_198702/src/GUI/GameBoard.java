package GUI;

import java.util.ArrayList;

public class GameBoard {

    private Square[][] board;
    private int size;

    public GameBoard(int size) {
        board = new Square[size][size];
        this.size = size;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++) {
                if((i+j)%2 == 0) {
                    board[i][j] = new BlackSquare();
                } else {
                    board[i][j] = new WhiteSquare();
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setUpCheckers(ArrayList<Checker> blacks, ArrayList<Checker> reds) {
        for(Checker black : blacks) {
            int i = 0; int j = 0;
            while (!(board[i][j].canMoveTo())) {
                if ((j + 1) % size == 0) { i++; j = 0; } else { j++; }
            }
            board[i][j].setChecker(black);
        }

        for(Checker red : reds) {
            int i = size-1; int j = 0;
            while (!(board[i][j].canMoveTo())) {
                if ((j + 1) % size == 0) { i--; j = 0; } else { j++; }
            }
            board[i][j].setChecker(red);
        }
    }

    public Square getSquareAt(int[] coor) { return board[coor[0]][coor[1]]; }

    public int[] getPosOfChecker(Checker checker) {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(board[i][j].getChecker() == checker) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public void setPosOfChecker(Checker checker, int[] newCoor) {
        board[newCoor[0]][newCoor[1]].setChecker(checker);
    }

    public void displayBoardAsString() {
        for(int i=0; i<size; i++) {
            System.out.print("----------");
        }
        System.out.println();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++) {
                if(board[i][j].getChecker() != null) {
                    if (board[i][j].getChecker().getColour().equals("black")) {
                        System.out.print("| " + board[i][j].getChecker().getColour() + " | ");
                    } else if (board[i][j].getChecker().getColour().equals("red")) {
                        System.out.print("|  " + board[i][j].getChecker().getColour() + "  | ");
                    }
                } else {
                    System.out.print("|       | ");
                }
            }
            System.out.println();
            for(int j=0; j<size; j++) {
                System.out.print("----------");
            }
            System.out.println();
        }
    }
}
