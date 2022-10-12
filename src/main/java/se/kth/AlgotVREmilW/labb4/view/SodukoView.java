package se.kth.AlgotVREmilW.labb4.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import se.kth.AlgotVREmilW.labb4.view.GridView;

public class SodukoView extends BorderPane {

    private GridView gridView;

    public SodukoView(){
        super();
        GridView gridView = new GridView();
        gridView.getPane().setMinSize(295,295);
        //gridView.getPane().setHgap(5);
        //gridView.getPane().setVgap(1);
        //gridView.getPane().setPadding(new Insets(10, 10, 10, 10));

        //BorderPane borderPane = new BorderPane();
        this.setCenter(gridView.getPane());
        //borderPane.setPrefSize( 300, 300);


        // menu compontents
        Menu fileMenu = new Menu("File");
        MenuItem loadGameItem = new MenuItem("Load Game");
        MenuItem saveGameItem = new MenuItem("Save Game");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(loadGameItem, saveGameItem, exitItem);

        //menu component
        Menu gameMenu = new Menu("Game");
        MenuItem newItem = new MenuItem("New");
        MenuItem easyItem = new MenuItem("Easy");
        MenuItem mediumItem = new MenuItem("Medium");
        MenuItem hardItem = new MenuItem("Hard");
        gameMenu.getItems().addAll(newItem, easyItem, mediumItem, hardItem);

        Menu helpMenu = new Menu("Help");
        MenuItem clearItem = new MenuItem("Clear game");
        MenuItem checkItem = new MenuItem("Check if numbers are correct");
        MenuItem rulesItem = new MenuItem("rules");
        helpMenu.getItems().addAll(clearItem,checkItem,rulesItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
        menuBar.setMinWidth(408);  //TODO: gör detta smartare

        GridPane gridPane2= new GridPane();
        gridPane2.getChildren().add(menuBar);

        gridPane2.setPadding(new Insets(0,0 , 10, 0));

        this.setTop(gridPane2);



        //left panel:
        GridPane leftGrid = new GridPane();
        Button checkButton = new Button("Check");
        Button hintButton = new Button("Hint");
        leftGrid.add(checkButton, 0,0);
        leftGrid.add(hintButton,0,1);
        leftGrid.setHgap(10);
        leftGrid.setVgap(10);
        leftGrid.setPadding(new Insets(10, 10, 10, 10));
        leftGrid.setAlignment(Pos.CENTER);

        leftGrid.setHalignment(hintButton, HPos.CENTER); // To align horizontally in the cell
        leftGrid.setHalignment(checkButton, HPos.CENTER); // To align horizontally in the cell

        this.setLeft(leftGrid);




        //right panel
        GridPane rightPane =  new GridPane();
        Button[] numberButton = new Button[9];
        Button clearButton = new Button("C");

        for(int i =0; i<9; i++){
            int nr = i+1;
            String nr2 = ""+nr;
            numberButton[i] = new Button(nr2);
        }

        for(int i=0; i<9; i++){
            rightPane.add(numberButton[i], 0,i);
        }
        rightPane.add(clearButton, 0,9);

        rightPane.setHgap(5);
        rightPane.setVgap(1.5);
        rightPane.setPadding(new Insets(10, 10, 10, 10));
        rightPane.setAlignment(Pos.CENTER);

        this.setRight(rightPane);


        //bottom pane:
        GridPane bottomPane = new GridPane();
        bottomPane.setPadding(new Insets(0, 0, 15, 0));
        this.setBottom(bottomPane);


    }

}
