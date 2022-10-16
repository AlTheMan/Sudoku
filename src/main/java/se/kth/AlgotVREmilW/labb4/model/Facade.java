package se.kth.AlgotVREmilW.labb4.model;
import java.util.Random;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;

public class Facade {

    int[][] gameState;
    private SudokuModel model;
    private SudokuTile[][] sudokuTiles;
    private SudokuLevel difficulty;
    public Facade() {
        this.difficulty = SudokuLevel.EASY;
        this.model = new SudokuModel(difficulty, this);
        gameState = model.makeGameAtStartCopy();
        sudokuTiles = model.getSudokuTiles();
    }

    public String getSudokuString(int x, int y){
        return sudokuTiles[x][y].getNumString();
    }
    public boolean getIfSudokuStartNr(int x, int y) {
        return sudokuTiles[x][y].getIfStartNr();
    }


    public void clearGame(){
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                model.setGameNr(i, j, model.getGameAtStartNumber(i, j));
                sudokuTiles[i][j].changeStateOnTile(model.getGameAtStartNumber(i, j));
            }
        }
    }

    public void changeDifficulty(SudokuLevel difficulty){
        this.difficulty = difficulty;
        model.changeDifficulty(difficulty);
        clearGame();
    }

    public boolean checkIfNoMistakes(){
        printGameState();
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                if(gameState[i][j] != 0){
                    if(model.getSudokuMatrix(i, j, 0) != model.getSudokuMatrix(i, j, 1)) return false;
                }
            }
        }
        return true;
    }

    private void printGameState() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(gameState[i][j]);
            }
            System.out.println();
        }
    }

    public boolean checkIfGameIsSolved(){
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(model.getSudokuMatrix(i, j,0) != model.getSudokuMatrix(i, j, 1)) return false;  //man kan också bara kolla om game[i][j][0]!=0, eftersom varje input ska vara laglig
            }
        }
        return true;
    }

    public int[] getHint(){
        Random random = new Random();
        int[] hintNr = new int[3];
        if(checkIfGameIsSolved()) return hintNr;   //för att förhindra att spelet krashar ifall man trycker på "hint" knappen när spelet är löst.

        while(true){
            int nr1= random.nextInt(9);
            int nr2= random.nextInt(9);
            if(model.getSudokuMatrix(nr1, nr2, 0)!=model.getSudokuMatrix(nr1, nr2, 1)){
                hintNr[0] = nr1;                    //x-värdet
                hintNr[1] = nr2;                    //y-värdet
                hintNr[2] = model.getSudokuMatrix(nr1, nr2, 1);      //vilken siffra på positionen
                return hintNr;
            }
        }
    }
    public boolean updateGame(int x, int y, int inputNr ){
        if(!checkLegalMove(x,y,inputNr)) {
            System.out.println("Illegal move"); //TODO: detta måste tas bort
            return false; //throws new Exception(); //ska kasta exception
        }
        model.setGameNr(x, y, inputNr);
        sudokuTiles[x][y].changeStateOnTile(inputNr);
        return true;
    }

    public boolean checkLegalMove(int x, int y, int inputNr ){
        //TODO: kolla om inputNr==0 behövs kollas
        if(inputNr==0){
            return !sudokuTiles[x][y].getIfStartNr();         //så att man inte kan placera på startvärden
        }
        for(int i =0; i<GRID_SIZE; i++){
            if (model.getGameNr(i, y) == inputNr) return false;
            if (model.getGameNr(x, i) == inputNr) return false;
        }

        //kollar om det finns samma siffra i samma ruta:
        //om man har position [8][0], så blir mod3 av 8 = 2.
        //då räknar man 8-2 för att få startindex, alltså
        // börjar man leta på index [6][0] och letar fram 3 rutor i vardera håll.
        int modX= x%3;
        int modY= y%3;
        for(int i=0; i<SECTION_SIZE; i++){
            for(int j=0; j<SECTION_SIZE; j++){
                if(model.getGameNr(x-modX+i, y-modY+j) == inputNr) return false;
                if(model.getGameNr(x-modX+j, y-modY+i) == inputNr) return false;
            }
        }

        return true;
    }
}
