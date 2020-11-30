package CMD;

import Logic.Game;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author ianw
 *
 * A very simple input line parser that parses each line written by the user to
 * the console. To use, create an object of this type, and then repeatedly call
 * getCommand.
 */
public class Parser {

    private Scanner input;
    private Game game;

    public Parser() {
        input = new Scanner(System.in);
        game = new Game(8, 12);
    }

    /**
     * Parse the input line, converting the first word encountered into a
     * command, and then passing any further arguments that make sense.
     *
     * @return the parsed command
     */
    public Command getCommand() {
        String inputLine = "";
        inputLine = input.nextLine();
        Scanner scanner = new Scanner(inputLine);
        if (scanner.hasNext()) {
            String str = scanner.next();
            CommandWord cw = CommandWord.getCommandWord(str);
            if (cw == CommandWord.UNKNOWN) {
                return new Command(cw, "Unknown command: " + str);
            } else if (cw == CommandWord.QUIT) {
                return new Command(cw, "Bye bye");
            } else {
                switch (cw) {
                    case NEW:
//                        int[] vars = new int[2];
//                        for(int i=0; i<vars.length; i++) {
//                            System.out.println("Enter")
//                        }
//                        if (scanner.hasNextInt() {
//                        int value = scanner.nextInt();
                        return new Command(cw, 8, 12);
                    case MOVE:
//                        if (scanner.hasNextInt()) {
//                            int row = scanner.nextInt();
//                            if (scanner.hasNextInt()) {
//                                int col = scanner.nextInt();
//                                return new Command(cw, row, col);
//                            }
//                        }
                        Pattern pattern = Pattern.compile("[0-9][0-9]");
                        if(!scanner.hasNext(pattern)) { break; }
                        int[] coor = new int[2];
                        for(int i=0; i<2; i++) {
                            coor[i] = scanner.nextInt();
                        }
                        return new Command(cw, coor);
                }
            }
            return new Command(CommandWord.UNKNOWN, "Probable insufficient argument: " + cw.getWord());
        }
        return new Command(CommandWord.UNKNOWN,  "Please tell me what to do");
    }

    public static void main(String args[]) {
        Parser p = new Parser();
        Command c = null;
        System.out.print("Enter a command> ");
        while ((c = p.getCommand()) != null) {
            System.out.println(c.getMsg());
            if(c.getCommand() == CommandWord.QUIT) { break; }
            System.out.print("> ");
        }
    }
}
