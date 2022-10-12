package se.kth.AlgotVREmilW.labb4.model;
import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;

public class TestMain {
    public static void main(String[] args){
        SudokuModel sudokuModel = new SudokuModel(SudokuLevel.EASY);
        System.out.println(sudokuModel);

        sudokuModel.updateGame(0,0,5);
        System.out.println(sudokuModel);

        System.out.println(sudokuModel.checkLegalMove(0,1,5));
        sudokuModel.updateGame(0,1,5);
        System.out.println(sudokuModel);

        sudokuModel.updateGame(8,8,7);
        System.out.println(sudokuModel);

    }
}
