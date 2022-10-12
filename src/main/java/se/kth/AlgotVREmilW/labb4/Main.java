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

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        GridView gridView = new GridView();
        gridView.getPane().setMinSize(295,295);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridView.getPane());
        //borderPane.setPrefSize( 300, 300);

        Scene scene= new Scene(borderPane);
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        //.setResizeable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}