package GUI;

import java.util.ArrayList;

public class GameState {

    private Checker[][] state;

    public GameState(int boardSize, ArrayList<Checker> reds, ArrayList<Checker> blacks) {
        state = new Checker[reds.size()][blacks.size()];
        update(reds, blacks);
    }

    public void update(ArrayList<Checker> reds, ArrayList<Checker> blacks) {
        for(Checker red : reds) {
            int[] currCoor = red.getCurrCoor();
            state[currCoor[0]][currCoor[1]] = red;
        }
        for(Checker black : blacks) {
            int[] currCoor = black.getCurrCoor();
            state[currCoor[0]][currCoor[1]] = black;
        }
    }

    public Checker[][] getState() {
        return state;
    }

    public Checker getCheckerAt(int[] coor) {
        return state[coor[0]][coor[1]];
    }
}
