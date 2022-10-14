package se.kth.AlgotVREmilW.labb4.view;

import javafx.scene.control.Label;
import se.kth.AlgotVREmilW.labb4.model.SudokuModel;

public class SudokuController {
    private int number;

    SudokuView view;
    SudokuModel model;

    public SudokuController(SudokuView view, SudokuModel model) {
        this.view = view;
        this.model = model;
    }

    public void handleCheckButton() {
        System.out.println("Check button");
    }

    public void handleHintButton() {
        System.out.println("Hint button");
    }


    public void handleClearButton() {
        System.out.println("Clear");
    }

    public void handleOneButton() {
        this.number = 1;
        System.out.println("Button 1");
    }

    public void handleTwoButton() {
        this.number = 2;
        System.out.println("Button 2");
    }

    public void handleThreeButton() {
        this.number = 3;
        System.out.println("Button 3");
    }

    public void handleFourButton() {
        this.number = 4;
        System.out.println("Button 4");
    }

    public void handleFiveButton() {
        this.number = 5;
        System.out.println("Button 5");
    }

    public void handleSixButton() {
        this.number = 6;
        System.out.println("Button 6");
    }

    public void handleSevenButton() {
        this.number = 7;
        System.out.println("Button 7");
    }

    public void handleEightButton() {
        this.number = 8;
        System.out.println("Button 8");
    }

    public void handleNineButton() {
        this.number = 9;
        System.out.println("Button 9");
    }

    public void handleCenterClick(int row, int col) {

        if (model.checkLegalMove(row, col, number)) {
            model.updateGame(row, col, number);
            view.setNumberOnTile(row, col, String.valueOf(number));
            System.out.println("Success");
        } else {
            System.out.println("Can't place here");
        }
        //TODO: else show alert, elr do nothing

    }


}
