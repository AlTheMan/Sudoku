package se.kth.AlgotVREmilW.labb4.model;

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
        randomizeGameBoard(game);
        gameAtStartCopy = new int[GRID_SIZE][GRID_SIZE];
        makeGameAtStartCopy();
        createSudokuTiles();
    }


        /**
         * Creates a game instance from file. Is used when user has saved a game on file
         * and wants to reload
         */
    private void updateFacadeGameState(){
        int[][] gameState = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i<9; i++){
            for (int j = 0; j < 9; j++) {
                gameState[i][j] = game[i][j][0];
            }
        }
        facade.setGameState(gameState);
    }

        /**
         * Creates a new game with input difficulty
         * @param difficulty specified difficulty
         */
    public void changeDifficulty(SudokuLevel difficulty) {
        this.game = generateSudokuMatrix(difficulty);
        randomizeGameBoard(game);
        makeGameAtStartCopy();
        createSudokuTiles();
        updateFacadeGameState();
    }

        /**
         * Loads game from input array. Is used to load a game from
         * previously saved file
         * @param loadedGame input array that stores the full game-state.
         */
    public void loadGame(int[][][] loadedGame) {
        this.game = loadedGame;
        createSudokuTiles();
        updateFacadeGameState();
    }

        /**
         *
         * @return array of the game-state (not a copy)
         */
    public int[][][] getGame(){
        return game;
    }

        /**
         * returns a copy of game at initial positions (before a user has inputted any numbers)
         * @return array of the game-state at starting positions.
         */
    public int[][] getGameAtStartCopy() {
        return gameAtStartCopy;
    }

        /**
         * Updates the initial game-states starting positions (before users
         * have inputed any numbers). I.e updates the starting-numbers.
         * is used when loading game from file.
         * @param gameAtStartCopy the array used to keep track of starting-numbers.
         */
    public void setGameAtStartCopy(int[][] gameAtStartCopy) {
        this.gameAtStartCopy = gameAtStartCopy;
    }


        /**
         * Creates a 2d array of SudokuTile:
         * SudokuTile[][]
         */
    public void createSudokuTiles() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(sudokuTiles[i][j] == null){
                    sudokuTiles[i][j] = new SudokuTile();
                }
                sudokuTiles[i][j].setNumber(game[i][j][1]);
                sudokuTiles[i][j].setVisible(false);        // Resets the values if a new game is generated.
                sudokuTiles[i][j].setIfStartNr(false);
                if(gameAtStartCopy[i][j] != 0){
                    sudokuTiles[i][j].setIfStartNr(true);
                }
                if(game[i][j][0] != 0) {
                    sudokuTiles[i][j].setVisible(true);
                }
            }
        }
    }


        /**
         * Returns a 3d array with either the solution or current state of the game
         * depending on which depth specified.
         * @param row specified row
         * @param col specified column
         * @param depth 0 indicates user-inputed numbers and initial starting-numbers. 1 indicates the array used for the solution
         * @return
         */
    public int getSudokuMatrix(int row, int col, int depth) {
        return game[row][col][depth];
    }

        /**
         * returns an array of current state of the game
         * @return
         */

    public String getSudokuString(int x, int y){
        return sudokuTiles[x][y].getNumString();
    }

    public boolean getIfSudokuStartNumber(int x, int y) {
        return sudokuTiles[x][y].getIfStartNr();
    }

        /**
         * Returns the value at specified row and column
         * @param x specified row
         * @param y specified column
         * @return
         */
    public int getGameNr(int x, int y){
        return game[x][y][0];
    }

        /**
         * sets a value at specified row and column
         * @param x specified row
         * @param y specified column
         * @param value what number to set on position
         */
    public void setGameNr(int x, int y, int value){
        game[x][y][0] = value;
        sudokuTiles[x][y].changeStateOnTile(value);
        updateFacadeGameState();
    }

        /**
         * returns the value at specified row and columnat at games initial positions
         * @param x specified row
         * @param y specified column
         * @return
         */
    public int getGameAtStartNumber(int x, int y) {
        return gameAtStartCopy[x][y];
    }

        /**
         * Copies the (initial) game state to a separate array that keeps track
         * of numbers at starting position.
         * @return the array that was created
         */
    int[][] makeGameAtStartCopy() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gameAtStartCopy[i][j] = game[i][j][0];
            }
        }
        return gameAtStartCopy;
    }
}
