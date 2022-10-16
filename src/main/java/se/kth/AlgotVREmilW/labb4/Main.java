package se.kth.AlgotVREmilW.labb4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.AlgotVREmilW.labb4.model.Facade;
import se.kth.AlgotVREmilW.labb4.view.SudokuView;
import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        Facade facade = new Facade();
        SudokuView view = new SudokuView(facade);
        Scene scene = new Scene(view);
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);  //TODO: kanske gör detta på ett annat sätt
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}