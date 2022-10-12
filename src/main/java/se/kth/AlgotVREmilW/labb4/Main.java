package se.kth.AlgotVREmilW.labb4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import se.kth.AlgotVREmilW.labb4.view.GridView;
import se.kth.AlgotVREmilW.labb4.view.SodukoView;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        SodukoView view = new SodukoView();
        Scene scene= new Scene(view);
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