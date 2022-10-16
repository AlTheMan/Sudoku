package se.kth.AlgotVREmilW.labb4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.kth.AlgotVREmilW.labb4.model.Facade;
import se.kth.AlgotVREmilW.labb4.view.SudokuView;
import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        Facade facade = new Facade();
        SudokuView view = new SudokuView(facade);
        Scene scene = new Scene(view);
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        //lägger till favvicon: / application icon:
        File application_icon_dir = new File(System.getProperty("user.home"), "/labb4_spara/favicon6.png");  //öppnar en specifik mapp (directory)
        if (application_icon_dir.exists()){
            stage.getIcons().add(new Image(String.valueOf(application_icon_dir)));
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);  //TODO: kanske gör detta på ett annat sätt
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}