package AI;

import GUI.GameState;

public class Minimax {



    public Minimax() { }

    public GameState getAiState(GameState position, int depth, boolean maxPlayer) {
        // Base case, if depth of tree is 0, or there is a winner, return the GameState object at that tree level
        if(depth == 0 || position.winner() != null) {
            return position;
        }

        // Check if AI is maximising or minimising player
        if(maxPlayer) {
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            int[] bestMove = null;
        }
        else {

        }
        return null;
    }
}
