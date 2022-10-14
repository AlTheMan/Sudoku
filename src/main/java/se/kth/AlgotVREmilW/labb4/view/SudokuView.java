package se.kth.AlgotVREmilW.labb4.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import se.kth.AlgotVREmilW.labb4.model.SudokuModel;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.GRID_SIZE;

public class SudokuView extends BorderPane {

    private GridView gridView;
    private SudokuModel model;
    Button[] numberButton;
    Button clearButton;
    Button hintButton;
    Button checkButton;
    MenuItem clearItem;
    GridPane rightPane;

    public SudokuView(SudokuModel model){
        super();
        this.model = model;

        SudokuController controller = new SudokuController(this, model);

        GridView gridView = new GridView(model);
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
        clearItem = new MenuItem("Clear game");
        MenuItem checkItem = new MenuItem("Check if numbers are correct");
        MenuItem rulesItem = new MenuItem("rules");
        helpMenu.getItems().addAll(clearItem,checkItem,rulesItem);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);

        VBox vBoxMenu = new VBox();
        vBoxMenu.getChildren().add(menuBar);
        vBoxMenu.setPadding(new Insets(0,0 , 10, 0));
        this.setTop(vBoxMenu);

        //left panel:
        GridPane leftGrid = new GridPane();
        checkButton = new Button("Check");
        hintButton = new Button("Hint");
        leftGrid.add(checkButton, 0,0);
        leftGrid.add(hintButton,0,1);
        leftGrid.setHgap(10);
        leftGrid.setVgap(10);
        leftGrid.setPadding(new Insets(10, 10, 10, 10));
        leftGrid.setAlignment(Pos.CENTER);

        GridPane.setHalignment(hintButton, HPos.CENTER); // To align horizontally in the cell
        GridPane.setHalignment(checkButton, HPos.CENTER); // To align horizontally in the cell

        this.setLeft(leftGrid);


        //right panel
        rightPane =  new GridPane();
        numberButton = new Button[9];
        clearButton = new Button("C");

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
        addEventHandlers(controller);
    }

    public void setGridTile(int row, int col, Label label){
        gridView.setNumberOnTile(row, col, label);
    }


    private void addEventHandlers(SudokuController controller) {
        addNumberButtons(controller);

        EventHandler<ActionEvent> clearButtonHandler = actionEvent -> controller.handleClearButton();
        clearButton.addEventHandler(ActionEvent.ACTION, clearButtonHandler);
        clearItem.addEventHandler(ActionEvent.ACTION, clearButtonHandler);
        EventHandler<ActionEvent> hintButtonHandler = actionEvent -> controller.handleHintButton();
        hintButton.addEventHandler(ActionEvent.ACTION, hintButtonHandler);
        EventHandler<ActionEvent> checkButtonHandler = actionEvent -> controller.handleCheckButton();
        checkButton.addEventHandler(ActionEvent.ACTION, checkButtonHandler);

        EventHandler<MouseEvent> tileCLickHandler = new EventHandler<MouseEvent>() {

            Label[][] numberTiles = gridView.getNumberTiles();
            @Override
            public void handle(MouseEvent event) {
                for(int row = 0; row < GRID_SIZE; row++) {
                    for(int col = 0; col < GRID_SIZE; col++) {
                        if(event.getSource() == numberTiles[row][col]) {
                            controller.handleCenterClick(row, col);
                            return;
                        }
                    }
                }
            }
        };






    }

    private void addNumberButtons(SudokuController controller) {
        EventHandler<ActionEvent> buttonOneHandler = actionEvent -> controller.handleOneButton();
        numberButton[0].addEventHandler(ActionEvent.ACTION, buttonOneHandler);

        EventHandler<ActionEvent> buttonTwoHandler = actionEvent -> controller.handleTwoButton();
        numberButton[1].addEventHandler(ActionEvent.ACTION, buttonTwoHandler);

        EventHandler<ActionEvent> buttonThreeHandler = actionEvent -> controller.handleThreeButton();
        numberButton[2].addEventHandler(ActionEvent.ACTION, buttonThreeHandler);

        EventHandler<ActionEvent> buttonFourHandler = actionEvent -> controller.handleFourButton();
        numberButton[3].addEventHandler(ActionEvent.ACTION, buttonFourHandler);

        EventHandler<ActionEvent> buttonFiveHandler = actionEvent -> controller.handleFiveButton();
        numberButton[4].addEventHandler(ActionEvent.ACTION, buttonFiveHandler);

        EventHandler<ActionEvent> buttonSixHandler = actionEvent -> controller.handleSixButton();
        numberButton[5].addEventHandler(ActionEvent.ACTION, buttonSixHandler);

        EventHandler<ActionEvent> buttonSevenHandler = actionEvent -> controller.handleSevenButton();
        numberButton[6].addEventHandler(ActionEvent.ACTION, buttonSevenHandler);

        EventHandler<ActionEvent> buttonEightHandler = actionEvent -> controller.handleEightButton();
        numberButton[7].addEventHandler(ActionEvent.ACTION, buttonEightHandler);

        EventHandler<ActionEvent> buttonNineHandler = actionEvent -> controller.handleNineButton();
        numberButton[8].addEventHandler(ActionEvent.ACTION, buttonNineHandler);
    }



}
