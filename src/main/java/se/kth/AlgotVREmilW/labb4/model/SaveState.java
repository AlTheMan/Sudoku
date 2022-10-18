package se.kth.AlgotVREmilW.labb4.model;

import java.io.Serializable;

/**
 * Is used to save the state of the game via SaveAndLoadFile
 */
class SaveState implements Serializable {

    private int[][][] game;
    private int[][] beginningState;


    SaveState(){

    }

    /**
     * Copiesthe game array  and game-at-beginning array to internal class objects
     * @param game
     * @param beginningState
     */
    SaveState(int[][][] game, int[][] beginningState){
        this.game = game;
        this.beginningState = beginningState;
    }

    int[][][] getGame() {
        return game;
    }

    int[][] getBeginningState() {
        return beginningState;
    }
}
