package se.kth.AlgotVREmilW.labb4.view;

import se.kth.AlgotVREmilW.labb4.model.Facade;
import se.kth.AlgotVREmilW.labb4.model.SudokuUtilities;


import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.GRID_SIZE;

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

    public void handleHintButton() {
        /*int[] addHint = facade.getHint();
        if(facade.updateGame(addHint[0], addHint[1], addHint[2])){
            view.updateGameBoard();
        }
        if(facade.checkIfGameIsSolved()) {
            view.showAlert("Congratulations!");
        }*/
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.MEDIUM);
        view.setColorsForNumbers();
        view.updateGameBoard();

    }

    public void handleClearItem() {
        facade.clearGame();
        view.updateGameBoard();
    }

    

    public void handleEasyItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.EASY);
        view.setColorsForNumbers();
        view.updateGameBoard();
    }
    public void handleMediumItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.MEDIUM);
        view.setColorsForNumbers();
        view.updateGameBoard();
    }
    public void handleHardItem() {
        facade.changeDifficulty(SudokuUtilities.SudokuLevel.HARD);
        view.setColorsForNumbers();
        view.updateGameBoard();
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

        /*if (this.number == 0) {
            if (facade.getCopyOfStart(row, col) == 0){
                facade.updateGame(row, col, this.number);
                view.updateGameBoard();
            }
        }
        else if (facade.checkLegalMove(row, col, number)) {
            facade.updateGame(row, col, number);
            view.updateGameBoard();
            System.out.println("Success");
        }
        else {
            //view.showAlert("Can't place here!");
        }*/
        //TODO: else show alert, elr do nothing
        facade.updateGame(row, col, number);
        view.updateGameBoard();
        if(facade.checkIfGameIsSolved()) {
            view.showAlert("Congratulations!");
        }
    }


}
