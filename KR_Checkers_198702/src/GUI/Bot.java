package GUI;

import java.util.ArrayList;

public class Bot {

    private DeepCopy dc;

    public Bot() {
        dc = new DeepCopy();
    }

    public GameState miniMax(GameState state, int depth, boolean maxPlayer) {
        // Base case, if depth of tree is 0, or there is a winner, return the GameState object at that tree level
        if(depth == 0 || state.winner() != null) {
            return state;
        }

        // Check if AI is maximising or minimising player
        if(maxPlayer) {
            double maxEval = Double.NEGATIVE_INFINITY;
            GameState bestMove = null;
            for(GameState move : getAllMoves(state, CheckerType.RED)) {
                double eval = miniMax(move, depth-1, false).evaluateFitness();
                if(eval > maxEval) {
                    maxEval = eval;
                    bestMove = move;
                }
            }
            return bestMove;
        }
        else {
            double minEval = Double.POSITIVE_INFINITY;
            GameState bestMove = null;
            for(GameState move : getAllMoves(state, CheckerType.BLACK)) {
                double eval = miniMax(move, depth-1, true).evaluateFitness();
                if(eval < minEval) {
                    minEval = eval;
                    bestMove = move;
                }
            }
            return bestMove;
        }
    }

    private ArrayList<GameState> getAllMoves(GameState state, CheckerType type) {
        ArrayList<GameState> moveStates = new ArrayList<>();
        for(Checker checker : state.getCheckers(type)) {
            for(int[] move : state.getAllValidMoves(checker)) {
                GameState tempState = (GameState) dc.copy(state);
                Checker tempChecker = tempState.getCheckerAt(checker.getCurrCoor());
                GameState newState = simulateMove(tempChecker, move, tempState);
                moveStates.add(newState);
            }
        }
        return moveStates;
    }

    private GameState simulateMove(Checker checker, int[] move, GameState state) {
        int[] newCoor = new int[]{checker.getCurrCoor()[0] + move[0], checker.getCurrCoor()[1] + move[1]};
        state.takeTurn(checker, newCoor);
        return state;
    }
}
