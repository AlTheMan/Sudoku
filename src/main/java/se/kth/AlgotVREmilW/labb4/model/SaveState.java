package se.kth.AlgotVREmilW.labb4.model;

import java.io.Serializable;

public class SaveState implements Serializable {

    private int[][][] game;
    private int[][] beginningState;


    public SaveState(){

    }
    public SaveState(int[][][] game, int[][] beginningState){
        this.game = game;
        this.beginningState = beginningState;
    }

    public int[][][] getGame() {
        return game;
    }

    public int[][] getBeginningState() {
        return beginningState;
    }
}
