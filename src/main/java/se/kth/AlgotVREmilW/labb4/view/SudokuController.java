package se.kth.AlgotVREmilW.labb4.view;

import se.kth.AlgotVREmilW.labb4.model.Facade;
import se.kth.AlgotVREmilW.labb4.model.SudokuUtilities;

import java.io.IOException;

public class SudokuController {
    private int number;

    private SudokuView view;
    private Facade facade;

    public SudokuController(SudokuView view, Facade facade) {
        this.view = view;
        this.facade = facade;
    }

    public void handleCheckButton() {
        if (facade.checkIfNoMistakes()) {
            view.showAlert("No mistakes yet!");
        }
        else{
            view.showAlert("You made some mistakes! :(");
        }

    }

    public void handleSaveGameItem() {
        try {
            view.saveFile();
        } catch (IOException e) {
            view.showAlert("Something went wrong trying to save file!");
            throw new RuntimeException(e);
        }
    }

    public void handleLoadGameItem() {
        try {
            view.loadFile();
            view.updateAllTiles();
            view.setColorsForNumbers();
        } catch (IOException | ClassNotFoundException e) {
            view.showAlert("Something went wrong trying to load file!");
            throw new RuntimeException(e);
        }
    }

    public void handleNewGameItem(){
        facade.changeDifficulty(facade.getDifficulty());
        view.setColorsForNumbers();
        view.updateAllTiles();
    }

    public void handleExitItem(){
        System.exit(0);
    }

    public void handleRulesItem(){
        view.showAlert(
                "The objective is to fill a 9 × 9 grid with digits so that each\n" +
                "column, each row, and each of the nine 3×3 subgrids that\n" +
                "compose the grid contain all of the digits from 1 to 9.");
    }

    public void handleHintButton() {
        int[] addHint = facade.getHint();
        if(facade.updateGame(addHint[0], addHint[1], addHint[2])){
            view.updateAllTiles();
        }
        if(facade.checkIfGameIsSolved()) {
            view.showAlert("Congratulations!");
        }
    }

    public void handleClearItem() {
        facade.clearGame();
        view.updateAllTiles();
    }

    

    public void handleEasyItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.EASY);
        view.setColorsForNumbers();
        view.updateAllTiles();
    }
    public void handleMediumItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.MEDIUM);
        view.setColorsForNumbers();
        view.updateAllTiles();
    }
    public void handleHardItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.HARD);
        view.setColorsForNumbers();
        view.updateAllTiles();
    }


    public void handleClearButton() {
        this.number = 0;
    }

    public void handleOneButton() {
        this.number = 1;
    }

    public void handleTwoButton() {
        this.number = 2;
    }

    public void handleThreeButton() {
        this.number = 3;
    }

    public void handleFourButton() {
        this.number = 4;
    }

    public void handleFiveButton() {
        this.number = 5;
    }

    public void handleSixButton() {
        this.number = 6;
    }

    public void handleSevenButton() {
        this.number = 7;
    }

    public void handleEightButton() {
        this.number = 8;
    }

    public void handleNineButton() {
        this.number = 9;
    }


    public void handleCenterClick(int row, int col) {
        facade.updateGame(row, col, number);
        view.updateTile(row, col);
        if(facade.checkIfGameIsSolved()) {
            view.showAlert("Congratulations!");
        }
    }


}
