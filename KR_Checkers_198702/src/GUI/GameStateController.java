package GUI;

public class GameStateController {

    private GameState state;
    private Bot bot;

    public GameStateController(GameState state) {
        this.state = state;
        bot = new Bot();
    }

    public GameState getState() {
        return state;
    }

    public void makeAIMove(int depth) {
        GameState tempState = bot.miniMax(state, depth, true);
        System.out.println("BEST STATE: " + tempState.evaluateFitness());
        state = tempState;
    }
}
