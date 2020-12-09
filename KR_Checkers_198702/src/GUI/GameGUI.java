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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameGUI extends Application {

    public static final int SQUARESIZE = 100;
    public static int WIDTH;
    public static int HEIGHT;

    private Group squareGroup = new Group();
    private Group checkerGroup = new Group();

    private GameState gameState = null;

    private Label turnLabel;
    private Label blackPointsLabel;
    private Label redPointsLabel;

    @Override
    public void start(Stage primaryStage) {

        Label boardSizeMsg = new Label("Board size: ");
        ChoiceBox<String> boardSize = new ChoiceBox<>();
        boardSize.getItems().addAll( "6", "8", "10");
        boardSize.setValue("8");

        Label difficultyMsg = new Label("Difficulty: ");
        ChoiceBox<String> difficulty = new ChoiceBox<>();
        difficulty.getItems().addAll("Easy", "Regular", "Hard");
        difficulty.setValue("Regular");

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
        h2.getChildren().addAll(generateNewGame);

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
        int fontSize = 15;

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

        gameState = new GameState(boardSize, rCheckers, bCheckers, wSquares, bSquares);
        UpdateBoard();

        Label turnMsg1 = new Label("It's currently");
        turnLabel = new Label(gameState.getWhosTurnName());
        Label turnMsg2 = new Label("Player's turn");

        Label blackPointsMsg = new Label("Black Player's points: ");
        blackPointsLabel = new Label("0");
        Label redPointsMsg = new Label("Red Player's points: ");
        redPointsLabel = new Label("0");

        turnMsg1.setFont(new Font(fontSize));
        turnLabel.setFont(new Font(fontSize));
        turnMsg2.setFont(new Font(fontSize));
        blackPointsMsg.setFont(new Font(fontSize));
        blackPointsLabel.setFont(new Font(fontSize));
        redPointsMsg.setFont(new Font(fontSize));
        redPointsLabel.setFont(new Font(fontSize));

        board.getChildren().addAll(squareGroup, checkerGroup);

        HBox h1 = new HBox();
        h1.setPadding(new Insets(25, 25, 25, 25));
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(5);
        h1.setPrefWidth(300);
        h1.getChildren().addAll(turnMsg1, turnLabel, turnMsg2);

        HBox h2 = new HBox();
        h2.setPadding(new Insets(0, 25, 25, 25));
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(5);
        h2.getChildren().addAll(blackPointsMsg, blackPointsLabel);

        HBox h3= new HBox();
        h3.setPadding(new Insets(0, 25, 25, 25));
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(5);
        h3.getChildren().addAll(redPointsMsg, redPointsLabel);

        VBox v = new VBox();
        v.setPadding(new Insets(0, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.setSpacing(5);
        v.getChildren().addAll(h1, h2, h3);

        HBox h4 = new HBox();
        h4.setPadding(new Insets(25, 25, 25, 25));
        h4.setAlignment(Pos.CENTER);
        h4.setSpacing(5);
        h4.getChildren().addAll(board, v);

        return h4;
    }

    private Checker InitChecker(CheckerType type, int[] coor, double size) {
        Checker checker = new Checker(type, coor, size);

        checker.setOnMouseReleased(e -> {
            int[] newCoor = new int[]{
                    (int) (checker.getLayoutX() + SQUARESIZE / 2) / SQUARESIZE,
                    (int) (checker.getLayoutY() + SQUARESIZE / 2) / SQUARESIZE};

            gameState.equals(gameState.takeTurn(checker, newCoor));

            for(Checker checker1 : gameState.getCheckers(CheckerType.RED)) {
                for(int i : checker1.getCurrCoor()) {
                    System.out.println(i);
                }
                System.out.println();
            }

            turnLabel.setText(gameState.getWhosTurnName());
            blackPointsLabel.setText(Integer.toString(gameState.getBlackPoints()));
            redPointsLabel.setText(Integer.toString(gameState.getRedPoints()));

            UpdateBoard();
        });

        return checker;
    }

    private void UpdateBoard() {
        squareGroup.getChildren().clear();
        for(Square white : gameState.getWSquares()) {
            squareGroup.getChildren().add(white);
        }
        for(Square black : gameState.getBSquares()) {
            squareGroup.getChildren().add(black);
        }

        checkerGroup.getChildren().clear();
        for(Checker red : gameState.getCheckers(CheckerType.RED)) {
            checkerGroup.getChildren().add(red);
            red.relocate(red.getCurrCoor()[0] * SQUARESIZE, red.getCurrCoor()[1] * SQUARESIZE);
        }
        for(Checker black : gameState.getCheckers(CheckerType.BLACK)) {
            checkerGroup.getChildren().add(black);
            black.relocate(black.getCurrCoor()[0] * SQUARESIZE, black.getCurrCoor()[1] * SQUARESIZE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
