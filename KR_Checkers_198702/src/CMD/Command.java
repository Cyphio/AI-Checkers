package CMD;

/**
 * @author ianw A representation of what the user wants the game to do next.
 */
public class Command {

    private CommandWord command;
    private String msg = "";
    private int x = 0;
    private int y = 0;
    private int value = 0;

    /**
     * Initialise with a Command and a message (which may be empty)
     *
     * @param command
     * @param msg
     */
    public Command(CommandWord command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    /**
     * Messages are currently only associated with "unknown" commands
     *
     * @return the message associated with the command
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * If we have a command that operated on a value, instantiate
     *
     * @param column
     */
    public Command(CommandWord command, int value) {
        super();
        this.command = command;
        this.value = value;
    }

    /**
     * If we have a command that operated on coordinates, instantiate with the
     * correct row and column
     *
     * @param command
     * @param row
     * @param column
     */
    public Command(CommandWord command, int column, int row) {
        super();
        this.command = command;
        this.x = row;
        this.y = column;
    }

    /**
     * If we have a command that operated on coordinates and a value,
     * instantiate with the correct row and column
     *
     * @param command
     * @param row
     * @param column
     * @param value
     */
    public Command(CommandWord command, int column, int row, int value) {
        super();
        this.command = command;
        this.x = row;
        this.y = column;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Command " + command + ", row=" + x + ", column=" + y + ", value=" + value;
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

    public int getValue() {
        return(value);
    }
}
