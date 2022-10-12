package se.kth.AlgotVREmilW.labb4.model;

import java.util.Arrays;
import java.util.Random;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;
public class SudokuModel {
    private int[][][] game;
    private int[][] gameCopy;  //contains a copy of the initial state of the game

    public SudokuModel(SudokuLevel sudokuLevel) {
        this.game = generateSudokuMatrix(sudokuLevel);
        gameCopy = new int[GRID_SIZE][GRID_SIZE];
        makeGameCopy();
    }

//    for(int i =0; i<GRID_SIZE; i++){
//        for(int j=0; j<GRID_SIZE; j++){
//        }
//    }

    //getters för int[][][0] till GridView

    private void makeGameCopy(){
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                gameCopy[i][j] = game[i][j][0];
            }
        }
    }
    private boolean checkIfStartNrExistAtPosition(int x, int y){
        if (gameCopy[x][y]!=0) return false;
        return true;
    }

    public void updateGame(int x, int y, int inputNr ){
        if(inputNr==0) {
            if( !checkIfStartNrExistAtPosition(x, y)){
                System.out.println("You tried to change a startNr");
                return; //TODO: illegal move
            }
        }
        else if(!checkLegalMove(x,y,inputNr)) {
            System.out.println("Illegal move"); //deta måste ta bort
            //TODO: detta måste tas bort
            return; //throws new Exception(); //ska kasta exception
        }
        game[x][y][0]= inputNr;
    }

    public boolean checkLegalMove(int x, int y, int inputNr ){
        for(int i =0; i<GRID_SIZE; i++){
            if (game[i][y][0] == inputNr) return false;
            if (game[x][i][0] == inputNr) return false;
        }
        return true;
    }

    public boolean checkIfGameIsSolved(){
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(game[i][j][0] != game[i][j][1]) return false;  //man kan också bara kolla om game[i][j][0]!=0, eftersom varje input ska vara laglig
            }
        }
        return true;
    }

    //TODO: return game för vy kanske

    public void clearGame(){
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                game[i][j][0] = gameCopy[i][j];
            }
        }
    }

    //TODO: göra psuedorandom generering för spelplanen
    // updatera oxå gameCopy

    public void randomizeGameBoard() {
        Random rand = new Random();
        int rand1 = rand.nextInt(9) + 1;
        int rand2 = rand1;

        while(rand2 == rand1){
            rand2 = rand.nextInt(9) + 1;
        }

        System.out.println("Random nr 1 = " + rand1);           //TODO: Ta bort sen
        System.out.println("Random nr 2 = "+ rand2 + "\n");

        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++) {
                if (game[i][j][1] == rand1) {
                    game[i][j][1] = rand2;
                    if(game[i][j][0] != 0) {
                        game[i][j][0] = rand2;
                    }
                }
                else if(game[i][j][1] == rand2) {
                    game[i][j][1] = rand1;
                    if(game[i][j][0] != 0) {
                        game[i][j][0] = rand1;
                    }
                }
            }
        }
    }


    @Override
    public String toString() {

        String info="";
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                info+= String.valueOf(game[i][j][0]);
                info+= " ";
            }
            info+="\n";
        }

        info += "\n";

        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                info+= String.valueOf(game[i][j][1]);
                info+= " ";
            }
            info+="\n";
        }

        return info;
    }
}
