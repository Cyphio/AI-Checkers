package CMD;

public class Command {

    private CommandWord command;
    private String msg = "";
    private int[] currCoor = new int[2];
    private int[] newCoor = new int[2];
    private int boardSize = 0;
    private int nCheckers = 0;

    public Command(CommandWord command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public Command(CommandWord command, int boardSize, int nCheckers) {
        super();
        this.command = command;
        this.boardSize = boardSize;
        this.nCheckers = nCheckers;
    }

    public Command(CommandWord command, int[] currCoor, int[] newCoor) {
        super();
        this.command = command;
        this.currCoor = currCoor;
        this.newCoor = newCoor;
    }

    public String getMsg() {
        return msg;
    }

    public CommandWord getCommand() {
        return command;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNCheckers() {
        return nCheckers;
    }

    public int[] getCurrCoor() {
        return currCoor;
    }

    public int[] getNewCoor() {
        return newCoor;
    }
}
