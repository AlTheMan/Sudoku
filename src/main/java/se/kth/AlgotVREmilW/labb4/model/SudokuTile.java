package se.kth.AlgotVREmilW.labb4.model;

class SudokuTile {

    private String numString;
    private boolean isVisible;
    private boolean isStartNumber;

    public SudokuTile(){
        isVisible = false;
    }

    public SudokuTile(int number, boolean isVisible) {
        this.isVisible = isVisible;
        this.numString = String.valueOf(number);
    }


    public void setNumber(int number) {
        this.numString = String.valueOf(number);
    }

    public void changeStateOnTile(int number) {
        setNumber(number);
        isVisible = (number != 0);
    }

    public String getNumString() {
        if (isVisible) return numString;
        else return "";
    }


    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    public void setIfStartNr(boolean startNumber) {
        this.isStartNumber = startNumber;
    }

    public boolean getIfStartNr() {
        return isStartNumber;
    }
}
