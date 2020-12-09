package AI;

import GUI.Checker;
import GUI.CheckerType;
import GUI.GameState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Minimax {

    public Minimax() { }

    public GameState getAiState(GameState state, int depth, boolean maxPlayer) {
        // Base case, if depth of tree is 0, or there is a winner, return the GameState object at that tree level
        if(depth == 0 || state.winner() != null) {
            return state;
        }

        // Check if AI is maximising or minimising player
        if(maxPlayer) {
            double maxEval = Double.NEGATIVE_INFINITY;
            GameState bestMove = null;
            for(GameState move : getAllMoves(state, CheckerType.BLACK)) {
                double eval = getAiState(move, depth-1, false).evaluateFitness();
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
            for(GameState move : getAllMoves(state, CheckerType.RED)) {
                double eval = getAiState(move, depth-1, true).evaluateFitness();
                if(eval < minEval) {
                    minEval = eval;
                    bestMove = move;
                }
            }
            return bestMove;
        }
    }

    private ArrayList<GameState> getAllMoves(GameState state, CheckerType type) {
        ArrayList<GameState> moveStates = null;
        for(Checker checker : state.getCheckers(type)) {
            ArrayList<int[]> validMoves = state.getAllValidMoves(checker);
            for(int[] move : validMoves) {
                GameState tempCopy = (GameState) deepCopy(state);
                GameState newState = simulateMove(checker, move, tempCopy);
                moveStates.add(newState);
            }
        }
        return moveStates;
    }

    private GameState simulateMove(Checker checker, int[] move, GameState stateCopy) {
        stateCopy.tryMove(checker, move);
        return stateCopy;
    }

    /**
     * Makes a deep copy of any Java object that is passed.
     */
    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
