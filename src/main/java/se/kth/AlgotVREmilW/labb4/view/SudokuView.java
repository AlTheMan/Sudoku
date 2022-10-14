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

import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se.kth.AlgotVREmilW.labb4.model.SudokuModel;

import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.*;
import static se.kth.AlgotVREmilW.labb4.model.SudokuUtilities.SECTIONS_PER_ROW;

public class SudokuView extends BorderPane {
    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private TilePane gridView;
    private SudokuModel model;
    Button[] numberButton;
    Button clearButton;
    Button hintButton;
    Button checkButton;
    MenuItem clearItem;

    public SudokuView(SudokuModel model){
        super();
        this.model = model;
        SudokuController controller = new SudokuController(this, model);
        initAndAddgrid();
        addMenu();
        addLeftPane();
        addRightPane();
        addBottomPane();
        addEventHandlers(controller);
    }
    private void initAndAddgrid(){
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();
        gridView= makeNumberPane();
        gridView.setMinSize(295,295);
        this.setCenter(gridView);
    }
    private void addMenu(){
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
    }
    private void addLeftPane(){
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

    }
    private void addRightPane(){
        GridPane rightPane =  new GridPane();
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
    }
    private void addBottomPane(){
        GridPane bottomPane = new GridPane();
        bottomPane.setPadding(new Insets(0, 0, 15, 0));
        this.setBottom(bottomPane);
    }







    //------------grid view --------------------
    public TilePane getPane(){
        return numberPane;
    }
    // called by constructor (only)
    private final void initNumberTiles() {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                //TODO: lägg till nummer här mha model.getNr()

                //TODO: ändra från startPositionen
                Label tile = new Label(model.getNrFromStartPositions(row, col)); // data from model
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                //tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }

    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }
        return root;
    }

    public Label getNumberTiles(int row, int col) {
        return numberTiles[row][col];
    }

    public Label[][] getNumberTilesArr(){
        return numberTiles;
    }

    public void setNumberOnTile(int row, int col, String number){
        numberTiles[row][col].setText(number);

    }














//----------------event handlers-----------------
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
            @Override
            public void handle(MouseEvent event) {
                for(int row = 0; row < GRID_SIZE; row++) {
                    for(int col = 0; col < GRID_SIZE; col++) {
                        //System.out.println(event.getSource());
                        if(event.getSource() == getNumberTiles(row, col)) {
                            System.out.println(getNumberTiles(row, col));
                            controller.handleCenterClick(row, col);
                            return;
                        }
                    }
                }
            }
        };
        //Label[][] TilesArr = getNumberTilesArr();
        for(int i =0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                numberTiles[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, tileCLickHandler);
            }
        }

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
