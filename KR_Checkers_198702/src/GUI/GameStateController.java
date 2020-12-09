package GUI;

public class GameStateController {

    private GameState state;
    private AI AI;

    public GameStateController(GameState state) {
        this.state = state;
        AI = new AI();
    }

    public GameState getState() {
        return state;
    }

    public void makeAIMove(int depth) {
        if(!state.isComplete()) {
            state = AI.miniMax(state, depth, true);
        }
    }
}
