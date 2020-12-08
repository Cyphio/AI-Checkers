package AI;

import GUI.Checker;
import GUI.CheckerType;
import GUI.GameState;

import java.util.ArrayList;
import java.util.HashMap;

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

    private ArrayList<HashMap<Checker, GameState>> getAllMoves(GameState position, CheckerType type) {
        ArrayList<HashMap<Checker, GameState>> moves = null;
        for(Checker checker : position.getCheckers(type)) {

        }
        return null;
    }
}
