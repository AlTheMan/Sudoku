package se.kth.AlgotVREmilW.labb4.model;

class SudokuTile {

    private String numString;
    private boolean isVisible;
    private boolean isStartNumber;

    public SudokuTile(){
        isVisible = false;
    }

    /**
     *
     * @param number
     * @param isVisible sets if the tile should be visible at start or not
     */
    SudokuTile(int number, boolean isVisible) {
        this.isVisible = isVisible;
        this.numString = String.valueOf(number);
    }


    /**
     * Sets the number of the Tile
     * @param number
     */
    void setNumber(int number) {
        this.numString = String.valueOf(number);
    }

    /**
     * Sets the specified number to tile and
     * if the number is =0, then sets the tile to be invisible
     * @param number what value the tile should have
     */
    void changeStateOnTile(int number) {
        setNumber(number);
        isVisible = (number != 0);
    }

    /**
     * Returns If the tile is visible, the Tiles number is returned
     * if the tile is invisible, the string "" is returned.
     * @return
     */
    public String getNumString() {
        if (isVisible) return numString;
        else return "";
    }

    /**
     * Sets the tile to be visible or not
     * @param visible true if the tile should be visible. False otherwise
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Sets the number to be a "start-number" i.e. a number that's initialized from the start
     * A computer-generated number is a start-number.
     * A user input number is not a start-number
     * @param startNumber true if Tile should be a start-number.
     */
    public void setIfStartNr(boolean startNumber) {
        this.isStartNumber = startNumber;
    }

    /**
     * returns if the Tile is a start-number, i.e if the number was NOT input by a user.
     * @return true if Tile is a start-number. False if it was input by a user.
     */
    public boolean getIfStartNr() {
        return isStartNumber;
    }
}
