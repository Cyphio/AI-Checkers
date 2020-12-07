package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameGUI extends Application {

    public static final int SQUARESIZE = 100;
    public static int WIDTH;
    public static int HEIGHT;

    private Group squareGroup = new Group();
    private Group checkerGroup = new Group();

    private GameLogic gameLogic = null;

    private Label blackPointsLabel;
    private Label redPointsLabel;

    @Override
    public void start(Stage primaryStage) {

        Label boardSizeMsg = new Label("Board size: ");
        ChoiceBox<String> boardSize = new ChoiceBox<>();
        boardSize.getItems().addAll("6", "8", "10");
        boardSize.setValue("8");

        Label difficultyMsg = new Label("Difficulty: ");
        ChoiceBox<String> difficulty = new ChoiceBox<>();
        difficulty.getItems().addAll("Easy", "Regular", "Hard");
        difficulty.setValue("Regular");

        Button load = new Button("Load");
        load.setOnAction(e -> {
            try {
                GameLogic data = (GameLogic) ResourceManager.load("a.save");
                this.gameLogic = data;
//                displayGame();
//                interact();

            } catch (Exception error) {
                System.out.println("Couldn't load: " + error.getMessage());
            }
        });

        Button generateNewGame = new Button("Generate Game");
        generateNewGame.setOnAction(e -> {
            primaryStage.setTitle("Checkers");
            primaryStage.setScene(new Scene(createGameContent(Integer.valueOf(boardSize.getValue()))));
            primaryStage.show();
        });

        HBox h = new HBox();
        h.setPadding(new Insets(0, 25, 25, 25));
        h.setAlignment(Pos.CENTER);
        h.setSpacing(5);
        h.getChildren().addAll(difficultyMsg, difficulty, boardSizeMsg, boardSize);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(5);
        h2.getChildren().addAll(generateNewGame, load);

        VBox v = new VBox();
        v.setPadding(new Insets(25, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h, h2);

        primaryStage.setTitle("Checkers Main Menu");
        primaryStage.setScene(new Scene(v, 400, 300));
        primaryStage.show();
    }

    private Parent createGameContent(int boardSize) {
        this.WIDTH = boardSize;
        this.HEIGHT = boardSize;

        Label blackPointsMsg = new Label("Black player's points: ");
        blackPointsLabel = new Label("0");
        Label redPointsMsg = new Label("Red player's points: ");
        redPointsLabel = new Label("0");

        Pane board = new Pane();
        board.setPrefSize(WIDTH * SQUARESIZE, HEIGHT * SQUARESIZE);

        ArrayList<Checker> rCheckers = new ArrayList();
        ArrayList<Checker> bCheckers = new ArrayList();
        ArrayList<Square> wSquares = new ArrayList();
        ArrayList<Square> bSquares = new ArrayList();
        for(int i = 0; i< HEIGHT; i++) {
            for(int j = 0; j< WIDTH; j++) {
                if((i+j)%2 == 0) {
                    wSquares.add(new Square(SquareType.WHITE, new int[]{i, j}));
                }
                else {
                    bSquares.add(new Square(SquareType.BLACK, new int[]{i, j}));
                }
                if(j <= (boardSize/2)-2 && (i+j)%2 != 0) {
                    rCheckers.add(InitChecker(CheckerType.RED, new int[]{i, j}, 0.4));
                }
                else if(j >= (boardSize/2)+1 && (i+j)%2 != 0) {
                    bCheckers.add(InitChecker(CheckerType.BLACK, new int[]{i, j}, 0.4));
                }
            }
        }

        gameLogic = new GameLogic(boardSize, rCheckers, bCheckers, wSquares, bSquares);
        UpdateBoard();

        board.getChildren().addAll(squareGroup, checkerGroup);

        Button save = new Button("Save");
        save.setOnAction(e -> {
            try {
                ResourceManager.save(this.gameLogic, "a.save");
            } catch (Exception error) {
                System.out.println("Couldn't save: " + error.getMessage());
            }
        });

        HBox h1 = new HBox();
        h1.setPadding(new Insets(25, 25, 25, 25));
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(5);
        h1.getChildren().addAll(blackPointsMsg, blackPointsLabel);

        HBox h2 = new HBox();
        h2.setPadding(new Insets(0, 25, 25, 25));
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(5);
        h2.getChildren().addAll(redPointsMsg, redPointsLabel);

        VBox v = new VBox();
        v.setPadding(new Insets(0, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.setSpacing(5);
        v.getChildren().addAll(h1, h2, save);

        HBox h3 = new HBox();
        h3.setPadding(new Insets(25, 25, 25, 25));
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(5);
        h3.getChildren().addAll(board, v);

        return h3;
    }

    private Checker InitChecker(CheckerType type, int[] coor, double size) {
        Checker checker = new Checker(type, coor, size);

        checker.setOnMouseReleased(e -> {
            int[] newCoor = new int[]{
                    (int) (checker.getLayoutX() + SQUARESIZE / 2) / SQUARESIZE,
                    (int) (checker.getLayoutY() + SQUARESIZE / 2) / SQUARESIZE};
            gameLogic.move(checker, newCoor);
            blackPointsLabel.setText(Integer.toString(gameLogic.getState().getBlackPoints()));
            redPointsLabel.setText(Integer.toString(gameLogic.getState().getRedPoints()));
            UpdateBoard();
        });

        return checker;
    }

    private void UpdateBoard() {
        squareGroup.getChildren().clear();
        for(Square white : gameLogic.getState().getWSquares()) {
            squareGroup.getChildren().add(white);
        }
        for(Square black : gameLogic.getState().getBSquares()) {
            squareGroup.getChildren().add(black);
        }

        checkerGroup.getChildren().clear();
        for(Checker red : gameLogic.getState().getRCheckers()) {
            checkerGroup.getChildren().add(red);
            red.relocate(red.getCurrCoor()[0] * SQUARESIZE, red.getCurrCoor()[1] * SQUARESIZE);
        }
        for(Checker black : gameLogic.getState().getBCheckers()) {
            checkerGroup.getChildren().add(black);
            black.relocate(black.getCurrCoor()[0] * SQUARESIZE, black.getCurrCoor()[1] * SQUARESIZE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
