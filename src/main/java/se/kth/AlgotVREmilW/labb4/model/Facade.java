package se.kth.AlgotVREmilW.labb4.model;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;
import static se.kth.AlgotVREmilW.labb4.model.SaveAndLoadFile.*;

/**
 * Is the facade of model
 */
public class Facade {

    int[][] gameState;
    private final SudokuModel model;
    private final SudokuTile[][] sudokuTiles;
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

    /**
     * Resets all tiles of the game to startpositions
     */
    public void clearGame(){
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                model.setGameNr(i, j, model.getGameAtStartNumber(i, j));
                sudokuTiles[i][j].changeStateOnTile(model.getGameAtStartNumber(i, j));
            }
        }
    }

    /**
     * Generates a new game with the set difficulty
     * @param difficulty sets the difficulty of the new game
     */
    public void changeDifficulty(SudokuLevel difficulty){
        this.difficulty = difficulty;
        model.changeDifficulty(difficulty);
        clearGame();
    }

    /**
     * Checks if user has entered any incorrect numbers
     * Is used to alert user of their mistakes
     * @return true if there has been mistakes, false otherwise
     */
    public boolean checkIfNoMistakes(){
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                if(gameState[i][j] != 0){
                    if(model.getSudokuMatrix(i, j, 0) != model.getSudokuMatrix(i, j, 1)) return false;
                }
            }
        }
        return true;
    }


    /**
     * Checks if game is solved
     * @return
     */
    public boolean checkIfGameIsSolved(){
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(model.getSudokuMatrix(i, j,0) != model.getSudokuMatrix(i, j, 1)) return false;  //man kan också bara kolla om game[i][j][0]!=0, eftersom varje input ska vara laglig
            }
        }
        return true;
    }

    /**
     * Returns the position and value of a correct tile that the user has not
     * yet entered
     * @return an int[] with the first element representing which row, and second element which column the tile is positioned at. The third element represents the tiles value
     */
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

    /**
     * Updates a specific tile with the input number
     * @param x what row should be updated
     * @param y what column should be updated
     * @param inputNr what value the tile should have
     * @return true if successfuly updated game
     */
    public boolean updateGame(int x, int y, int inputNr ){
        if(!checkLegalMove(x,y,inputNr)) {
            System.out.println("Illegal move"); //TODO: detta måste tas bort
            return false; //throws new Exception(); //ska kasta exception
        }
        model.setGameNr(x, y, inputNr);
        sudokuTiles[x][y].changeStateOnTile(inputNr);
        return true;
    }
    public SudokuLevel getDifficulty(){
        return difficulty;
    }

    /**
     * Checks if it's legal to update tile at a specified position with specified value.
     * @param x specified row
     * @param y specified column
     * @param inputNr what value you want to check
     * @return true if the move is legal
     */
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

    /**
     * Saves the class SaveState to a file with the ending .Sudoku
     * @param file filename
     * @throws IOException
     */
    public void saveFile(File file) throws IOException {
        SaveState state = new SaveState(model.getGame(), model.getGameAtStartCopy());
        serializeToFile(file, state);
    }


    //TODO: hantera exception
    /**
     * Loads file from file. Can only load .Sudoku-files
     * @param file specified file to load
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFile(File file) throws IOException, ClassNotFoundException{
        SaveState state = deSerializeFromFile(file);
        model.setGameAtStartCopy(state.getBeginningState());
        model.loadGame(state.getGame());
    }

    /**
     * prints the state of the game on terminal
     */
    private void printGameState() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(gameState[i][j]);
            }
            System.out.println();
        }
    }


}
