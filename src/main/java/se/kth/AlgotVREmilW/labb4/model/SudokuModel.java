package se.kth.AlgotVREmilW.labb4.model;

import java.util.Random;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;
    class SudokuModel {
    private int[][][] game;
    private int[][] gameAtStartCopy;  //contains a copy of the initial state of the game
    private SudokuTile[][] sudokuTiles;
    private Facade facade;

    public SudokuModel(SudokuLevel sudokuLevel, Facade facade) {
        this.facade = facade;
        sudokuTiles = new SudokuTile[GRID_SIZE][GRID_SIZE];
        this.game = generateSudokuMatrix(sudokuLevel);
        randomizeGameBoard();
        gameAtStartCopy = new int[GRID_SIZE][GRID_SIZE];
        createSudokuTiles();
        makeGameAtStartCopy();
    }

    private void updateFacadeGameState(){
        int[][] gameState = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i<9; i++){
            for (int j = 0; j < 9; j++) {
                gameState[i][j] = game[i][j][0];
            }
        }
        facade.gameState = gameState;
    }

    public void changeDifficulty(SudokuLevel difficulty) {
        this.game = generateSudokuMatrix(difficulty);
        randomizeGameBoard();
        makeGameAtStartCopy();
        createSudokuTiles();
        updateFacadeGameState();
    }



    public void createSudokuTiles() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(sudokuTiles[i][j] == null){
                    sudokuTiles[i][j] = new SudokuTile();
                }
                sudokuTiles[i][j].setVisible(false);
                sudokuTiles[i][j].setIfStartNr(false);
                if(game[i][j][0] != 0){
                    sudokuTiles[i][j].setVisible(true);
                    sudokuTiles[i][j].setIfStartNr(true);
                }
                sudokuTiles[i][j].setNumber(game[i][j][1]);
            }
        }
    }


    public int getSudokuMatrix(int row, int col, int depth) {
        return game[row][col][depth];
    }

    public SudokuTile[][] getSudokuTiles(){
        return sudokuTiles;
    }

    public int getGameNr(int x, int y){
        return game[x][y][0];
    }
    public void setGameNr(int x, int y, int value){
        game[x][y][0] = value;
        updateFacadeGameState();
    }

    public int getGameAtStartNumber(int x, int y) {
        return gameAtStartCopy[x][y];
    }

    int[][] makeGameAtStartCopy(){
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                gameAtStartCopy[i][j] = game[i][j][0];
            }
        }
        return gameAtStartCopy;
    }


        //TODO: göra psuedorandom generering för spelplanen
    // updatera oxå gameCopy

    /**
     * Randomizes two numbers between 1-9 to switch with each other.
     * Changes both the player board and the solution board.
     */
    public void randomizeGameBoard() { //TODO: Ska göras static och flyttas till SudokuUtil
        Random rand = new Random();
        int rand1 = rand.nextInt(GRID_SIZE) + 1;
        int rand2 = rand1;
        while(rand2 == rand1){      // Makes sure the numbers aren't the same
            rand2 = rand.nextInt(GRID_SIZE) + 1;
        }

        // The two random numbers swaps places

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
        rand1 = rand.nextInt(2);
        rand2 = rand.nextInt(2);
        if (rand1 == 1) reverseVertical();
        if (rand2 == 1) reverseHorizontal();
    }

    public void reverseHorizontal() {
        int[] tmpUnsolved = new int[9];
        int[] tmpSolved = new int[9];

        for (int i = 0; i < GRID_SIZE; i++) {
            int size = 0;
            for (int j = GRID_SIZE - 1; j >= 0; j--) {
                tmpUnsolved[size] = game[i][j][0];
                tmpSolved[size] = game[i][j][1];
                size++;
            }
            for (int j = 0; j < GRID_SIZE; j++) {
                game[i][j][0] = tmpUnsolved[j];
                game[i][j][1] = tmpSolved[j];
            }
        }
    }

    public void reverseVertical(){
        int[] tmpUnsolved = new int[9];
        int[] tmpSolved = new int[9];

        for (int i = 0; i < GRID_SIZE; i++) {
            int size = 0;
            for (int j = GRID_SIZE - 1; j >= 0; j--) {
                tmpUnsolved[size] = game[j][i][0];
                tmpSolved[size] = game[j][i][1];
                size++;
            }
            for (int j = 0; j < GRID_SIZE; j++) {
                game[j][i][0] = tmpUnsolved[j];
                game[j][i][1] = tmpSolved[j];
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
