package CMD;

public class Command {

    private CommandWord command;
    private String msg = "";
    private int x = 0;
    private int y = 0;
    private int boardSize = 8;
    private int nCheckers = 12;

    public Command(CommandWord command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Command(CommandWord command, int boardSize, int nCheckers) {
        super();
        this.command = command;
        this.boardSize = boardSize;
        this.nCheckers = nCheckers;
    }

    public Command(CommandWord command, int[] coor) {
        super();
        this.command = command;
        this.x = coor[0];
        this.y = coor[1];
    }

    @Override
    public String toString() {
        return "Command " + command + ", row=" + x + ", column=" + y;
    }

    /**
     * Principally to be used in a switch statement to decide on the action to
     * be taken, given this command
     *
     * @return the CommandWord
     */
    public CommandWord getCommand() {
        return command;
    }

    public void setCommand(CommandWord command) {
        this.command = command;
    }

    /**
     * Valid for commands which need a row and column value
     *
     * @return The row value
     */
    public int getX() {
        return x;
    }

    /**
     * Valid for commands which need a row and column value
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Valid for commands which need a row and column value
     *
     * @return The column value
     */
    public int getY() {
        return y;
    }

    /**
     * Valid for commands which need a row and column value
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
