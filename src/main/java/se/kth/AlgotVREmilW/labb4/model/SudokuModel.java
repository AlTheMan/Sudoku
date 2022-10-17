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
         * returns a copy of game at initial positions (before a user has input any numbers)
         * @return array of the game-state at starting positions.
         */
    public int[][] getGameAtStartCopy() {
        return gameAtStartCopy;
    }

        /**
         * Updates the initial game-states starting positions (before users
         * have input any numbers). I.e updates the starting-numbers.
         * is used when loading game from file.
         * @param gameAtStartCopy the array used to keep track of starting-numbers.
         */
    public void setGameAtStartCopy(int[][] gameAtStartCopy) {
        this.gameAtStartCopy = gameAtStartCopy;
    }


        /**
         * Creates a 9x9 array of SudokuTiles:
         * If the array is alreade created it just updates the values.
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
         * Compares the numbers dimensionally between the matrices
         * @param x row
         * @param y column
         * @return true if equal
         */

    public boolean compareSudokuMatrix(int x, int y) {
        return (game[x][y][0] == game[x][y][1]);
    }


        /**
         * @param row specified row
         * @param col specified column
         * @return a correct number from the solution matrix
         */
    public int getACorrectNumber(int row, int col) {
        return game[row][col][1];
    }


        /**
         * @param x row
         * @param y column
         * @return a string value of the sudoku number at (x,y) position
         */

    public String getSudokuString(int x, int y){
        return sudokuTiles[x][y].getNumString();
    }

        /**
         *
         * @param x row
         * @param y column
         * @return true if the number at (x,y) is a start number.
         */
    public boolean getIfSudokuStartNumber(int x, int y) {
        return sudokuTiles[x][y].getIfStartNr();
    }

        /**
         * @param x specified row
         * @param y specified column
         * @return the value at specified row and column
         */
    public int getGameNr(int x, int y){
        return game[x][y][0];
    }

        /**
         * sets a value at specified row and column
         * updates the specific tile in the sudoku-tilearray
         * also updates the game state array in the facade class
         * @param x specified row
         * @param y specified column
         * @param value what number to set on position
         */
    public void setGameData(int x, int y, int value){
        game[x][y][0] = value;
        sudokuTiles[x][y].changeStateOnTile(value);
        updateFacadeGameState();
    }

        /**
         * @param x specified row
         * @param y specified column
         * @return the value at specified row and column at games initial positions
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
