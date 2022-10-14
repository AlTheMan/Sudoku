package se.kth.AlgotVREmilW.labb4.view;

import javafx.scene.control.Label;
import se.kth.AlgotVREmilW.labb4.model.SudokuModel;

public class SudokuController {
    private Label number;

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

    public void handleOneButton(){
        this.number= new Label("1");
        System.out.println("Button 1");
    }
    public void handleTwoButton(){
        System.out.println("Button 2");
    }
    public void handleThreeButton(){
        System.out.println("Button 3");
    }
    public void handleFourButton(){
        System.out.println("Button 4");
    }
    public void handleFiveButton(){
        System.out.println("Button 5");
    }
    public void handleSixButton(){
        System.out.println("Button 6");
    }
    public void handleSevenButton(){
        System.out.println("Button 7");
    }
    public void handleEightButton(){
        System.out.println("Button 8");
    }
    public void handleNineButton(){
        System.out.println("Button 9");
    }

    public void handleCenterClick(int row, int col){
        view.setNumberOnTile(row,col,number);

    }




}
