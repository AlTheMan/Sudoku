package se.kth.AlgotVREmilW.labb4.model;
import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;

public class TestMain {
    public static void main(String[] args){
        SudokuModel sudokuModel = new SudokuModel(SudokuLevel.EASY);
        System.out.println(sudokuModel);

    }
}
