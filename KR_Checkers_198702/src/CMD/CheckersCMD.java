package CMD;

import Logic.Game;

public class CheckersCMD {

    public static void main(String args[]) {
        Game game = null;
        Parser p = new Parser();
        System.out.println("COMMAND LIST:\n" +
                "NEW: requires two arguments defining the size of the board and the number of checkers\n" +
                "e.g. new 8 12 - creates an 8x8 board with 12 red checkers and 12 black checkers\n" +
                "MOVE: requires 4 arguments defining the coordinates of the checker to be moved & where it is to be moved to\n" +
                "e.g. move 2 0 3 1 - moves the checker at (2, 0) to (3, 1)\n" +
                "QUIT: finishes the game\n");
        System.out.print("Please create a new game\n> ");
        Command c = p.getCommand();
        while(c.getCommand() != CommandWord.NEW) {
            System.out.println("Must initiate the game");
            System.out.print("> ");
            c = p.getCommand();
        }
        game = new Game(c.getBoardSize(), c.getNCheckers());
        game.getBoard().displayBoardAsString();
        while(!game.isComplete()) {
            System.out.print("> ");
            c = p.getCommand();
            if(c.getCommand() == CommandWord.MOVE) {
                game.move(c.getCurrCoor(), c.getNewCoor());
                game.getBoard().displayBoardAsString();
            }
            if(c.getCommand() == CommandWord.UNKNOWN) {
                System.out.println("Unknown command");
            }
            if(c.getCommand() == CommandWord.QUIT) {
                break;
            }
        }
        System.out.println("Game finished");
    }
}
