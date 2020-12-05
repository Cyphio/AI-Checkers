//package CMD;
//
//import GUI.Game;
//import java.util.Scanner;
//
//public class Parser {
//
//    private Scanner input;
//    private Game game;
//
//    public Parser() {
//        input = new Scanner(System.in);
//        game = new Game(8, 12);
//    }
//
//    public Command getCommand() {
//        String inputLine = "";
//        inputLine = input.nextLine();
//        Scanner scanner = new Scanner(inputLine);
//        if (scanner.hasNext()) {
//            String str = scanner.next();
//            CommandWord cw = CommandWord.getCommandWord(str);
//            if (cw == CommandWord.UNKNOWN) {
//                return new Command(cw, "Unknown command: " + str);
//            } else if (cw == CommandWord.QUIT) {
//                return new Command(cw, "Bye bye");
//            } else {
//                switch (cw) {
//                    case NEW:
//                        try {
//                            int[] args = new int[2];
//                            for(int i=0; i<2; i++) {
//                                args[i] = scanner.nextInt();
//                            }
//                            return new Command(cw, args[0], args[1]);
//                        } catch(Exception e) {
//                            e.getMessage();
//                        }
//                        break;
//                    case MOVE:
//                        try {
//                            int[] currCoor = new int[2];
//                            int[] newCoor = new int[2];
//                            for(int i=0; i<2; i++) {
//                                currCoor[i] = scanner.nextInt();
//                            }
//                            for(int i=0; i<2; i++) {
//                                newCoor[i] = scanner.nextInt();
//                            }
//                            return new Command(cw, currCoor, newCoor);
//                        } catch(Exception e) {
//                            e.getMessage();
//                        }
//                        break;
//
//                }
//            }
//            return new Command(CommandWord.UNKNOWN, "Probable insufficient argument: " + cw.getWord());
//        }
//        return new Command(CommandWord.UNKNOWN,  "Please tell me what to do");
//    }
//
//    public static void main(String args[]) {
//        Parser p = new Parser();
//        Command c = null;
//        System.out.print("Enter a command> ");
//        while ((c = p.getCommand()) != null) {
//            System.out.println(c.getMsg());
//            if(c.getCommand() == CommandWord.QUIT) { break; }
//            System.out.print("> ");
//        }
//    }
//}