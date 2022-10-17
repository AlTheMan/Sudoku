package se.kth.AlgotVREmilW.labb4.model;

import java.util.Random;

public class SudokuUtilities {

    public enum SudokuLevel {EASY, MEDIUM, HARD}

    public static final int GRID_SIZE = 9;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param level The level, i.e. the difficulty, of the initial standing.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    public static int[][][] generateSudokuMatrix(SudokuLevel level) {
        String representationString;
        switch (level) {
            case EASY: representationString = easy; break;
            case MEDIUM: representationString = medium; break;
            case HARD: representationString = hard; break;
            default: representationString = medium;
        }
        return convertStringToIntMatrix(representationString);
    }

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param stringRepresentation A string of 2*81 characters, 0-9. The first 81 characters represents
     *                             the initial values, '0' representing an empty cell.
     *                             The following 81 characters represents the solution.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    /*package private*/
    static int[][][] convertStringToIntMatrix(String stringRepresentation) {
        if (stringRepresentation.length() != GRID_SIZE * GRID_SIZE * 2)
            throw new IllegalArgumentException("representation length " + stringRepresentation.length());

        int[][][] values = new int[GRID_SIZE][GRID_SIZE][2];
        char[] charRepresentation = stringRepresentation.toCharArray();

        int charIndex = 0;
        // initial values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][0] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }

        // solution values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][1] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }

        return values;
    }

    private static int convertCharToSudokuInt(char ch) {
        if (ch < '0' || ch > '9') throw new IllegalArgumentException("character " + ch);
        return ch - '0';
    }

    private static final String easy =
            "000914070" +
                    "010000054" +
                    "040002000" +
                    "007569001" +
                    "401000500" +
                    "300100000" +
                    "039000408" +
                    "650800030" +
                    "000403260" + // solution values after this substring
                    "583914672" +
                    "712386954" +
                    "946752183" +
                    "827569341" +
                    "461238597" +
                    "395147826" +
                    "239675418" +
                    "654821739" +
                    "178493265";

    private static final String medium =
            "300000010" +
                    "000050906" +
                    "050401200" +
                    "030000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200300058" +
                    "100800090" +
                    "324976815" +
                    "718253946" +
                    "659481273" +
                    "536142789" +
                    "872569431" +
                    "491738562" +
                    "985617324" +
                    "267394158" +
                    "143825697";

    private static final String hard =
            "030600000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "340000800" +
                    "500030094" +
                    "000400000" +
                    "150800200" +
                    "700006050" +
                    "931687542" +
                    "465219378" +
                    "287345916" +
                    "876924135" +
                    "349561827" +
                    "512738694" +
                    "693452781" +
                    "154873269" +
                    "728196453";



    /**
     * Randomizes two numbers between 1-9 to switch with each other.
     * Changes both the player board and the solution board.
     */
    public static void randomizeGameBoard(int[][][] game) { //TODO: Ska göras static och flyttas till SudokuUtil
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
        if (rand1 == 1) reverseVertical(game);
        if (rand2 == 1) reverseHorizontal(game);
    }



    private static void reverseHorizontal(int[][][] game) {
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


    private static void reverseVertical(int[][][] game){
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
}
