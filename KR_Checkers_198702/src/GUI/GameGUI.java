package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameGUI extends Application {

    public static final int SQUARESIZE = 100;
    public static int WIDTH;
    public static int HEIGHT;

    private Stage window;

    private Group squareGroup = new Group();;

    private Label turnLabel;
    private Label blackPointsLabel;
    private Label redPointsLabel;

    private GameStateController controller;
    private ChoiceBox<Object> difficultyChoice;
    private CheckBox playAgainstAI;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        Label competitionMsg = new Label("Play against AI: ");
        playAgainstAI = new CheckBox();
        playAgainstAI.setSelected(true);

        Label difficultyMsg = new Label("Difficulty: ");
        difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll("Easy", "Regular", "Hard");
        difficultyChoice.setValue("Regular");

        Label boardSizeMsg = new Label("Board size: ");
        ChoiceBox<String> boardSizeChoice = new ChoiceBox<>();
        boardSizeChoice.getItems().addAll( "6", "8", "10");
        boardSizeChoice.setValue("8");

        Button generateNewGame = new Button("Generate Game");
        generateNewGame.setOnAction(e -> {
            int boardSize = Integer.valueOf(boardSizeChoice.getValue());
            WIDTH = boardSize;
            HEIGHT = boardSize;
            ArrayList<Checker> rCheckers = new ArrayList();
            ArrayList<Checker> bCheckers = new ArrayList();
            ArrayList<Square> wSquares = new ArrayList();
            ArrayList<Square> bSquares = new ArrayList();
            for(int i = 0; i< HEIGHT; i++) {
                for(int j = 0; j< WIDTH; j++) {
                    if((i+j)%2 == 0) {
                        Square whiteSq = new Square(SquareType.WHITE, new int[]{i, j});
                        wSquares.add(whiteSq);
                        squareGroup.getChildren().add(whiteSq);
                    }
                    else {
                        Square blackSq = new Square(SquareType.BLACK, new int[]{i, j});
                        bSquares.add(blackSq);
                        squareGroup.getChildren().add(blackSq);
                    }
                    if(j <= (boardSize/2)-2 && (i+j)%2 != 0) {
                        rCheckers.add(new Checker(CheckerType.RED, new int[]{i, j}));
                    }
                    else if(j >= (boardSize/2)+1 && (i+j)%2 != 0) {
                        bCheckers.add(new Checker(CheckerType.BLACK, new int[]{i, j}));
                    }
                }
            }
            GameState state = new GameState(boardSize, rCheckers, bCheckers, wSquares, bSquares);
            controller = new GameStateController(state);
            updateGameContent(controller.getState());
        });

        HBox h = new HBox();
        h.setPadding(new Insets(0, 25, 25, 25));
        h.setAlignment(Pos.CENTER);
        h.setSpacing(5);
        h.getChildren().addAll(competitionMsg, playAgainstAI, difficultyMsg, difficultyChoice);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(5);
        h2.getChildren().addAll(boardSizeMsg, boardSizeChoice, generateNewGame);

        VBox v = new VBox();
        v.setPadding(new Insets(25, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h, h2);

        window.setTitle("Checkers Main Menu");
        window.setScene(new Scene(v, 400, 300));
        window.show();
    }

    private void updateGameContent(GameState state) {
        int fontSize = 15;

        Group checkerGroup = new Group();

        Pane board = new Pane();
        board.setPrefSize(WIDTH * SQUARESIZE, HEIGHT * SQUARESIZE);

        Label turnMsg1 = new Label("It's currently");
        turnLabel = new Label(state.getWhosTurnName());
        Label turnMsg2 = new Label("Player's turn");

        Label blackPointsMsg = new Label("Black Player's points: ");
        blackPointsLabel = new Label(Integer.toString(state.getBlackPoints()));

        Label redPointsMsg = new Label("Red Player's points: ");
        redPointsLabel = new Label(Integer.toString(state.getRedPoints()));

        turnMsg1.setFont(new Font(fontSize));
        turnLabel.setFont(new Font(fontSize));
        turnMsg2.setFont(new Font(fontSize));
        blackPointsMsg.setFont(new Font(fontSize));
        blackPointsLabel.setFont(new Font(fontSize));
        redPointsMsg.setFont(new Font(fontSize));
        redPointsLabel.setFont(new Font(fontSize));

        for(Checker red : state.getCheckers(CheckerType.RED)) {
            checkerGroup.getChildren().add(InitChecker(red, red.getCurrCoor(), Color.RED, Color.WHITE, 0.4));
            red.relocate(red.getCurrCoor()[0] * SQUARESIZE, red.getCurrCoor()[1] * SQUARESIZE);
        }
        for(Checker black : state.getCheckers(CheckerType.BLACK)) {
            checkerGroup.getChildren().add(InitChecker(black, black.getCurrCoor(), Color.BLACK, Color.WHITE, 0.4));
            black.relocate(black.getCurrCoor()[0] * SQUARESIZE, black.getCurrCoor()[1] * SQUARESIZE);
        }

        board.getChildren().addAll(squareGroup, checkerGroup);

        Button helpButton = new Button("Help");
        helpButton.setOnMousePressed(e -> {
            AlertBox.helpBox();
        });

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
        v.getChildren().addAll(h1, h2, h3, helpButton);

        HBox h4 = new HBox();
        h4.setPadding(new Insets(25, 25, 25, 25));
        h4.setAlignment(Pos.CENTER);
        h4.setSpacing(5);
        h4.getChildren().addAll(board, v);

        window.setTitle("Checkers");
        window.setScene(new Scene(h4));
        window.show();
    }

    private Checker InitChecker(Checker checker, int[] coor, Color colour, Color secColour, double size) {
        checker.setCurrCoor(coor);

        Ellipse checkerPiece = new Ellipse(SQUARESIZE * size, SQUARESIZE * size);
        checkerPiece.setFill(colour);
        checkerPiece.setStroke(secColour);
        checkerPiece.setStrokeWidth(3);

        checkerPiece.setTranslateX((SQUARESIZE - checkerPiece.getRadiusX() * 2) / 2);
        checkerPiece.setTranslateY((SQUARESIZE - checkerPiece.getRadiusY() * 2) / 2);

        checker.getChildren().add(checkerPiece);

        if (checker.isAtRisk()) {
            checkerPiece.setStroke(Color.GREENYELLOW);
        }
        else {
            if (checker.getKing()) {
                checkerPiece.setStroke(Color.GOLD);
            } else {
                checkerPiece.setStroke(Color.WHITE);
            }
        }

        checker.setOnMouseReleased(e -> {
            int[] newCoor = new int[]{
                    (int) (checker.getLayoutX() + SQUARESIZE / 2) / SQUARESIZE,
                    (int) (checker.getLayoutY() + SQUARESIZE / 2) / SQUARESIZE};

            controller.getState().takeTurn(checker, newCoor);

            updateGameContent(controller.getState());

            if (controller.getState().getWhosTurn() == CheckerType.RED) {
                if(!playAgainstAI.isSelected()) {
                    controller.makeAIMove(0);
                }
                else if (difficultyChoice.getValue().equals("Easy")) {
                    controller.makeAIMove(1);
                }
                else if (difficultyChoice.getValue().equals("Regular")) {
                    controller.makeAIMove(3);
                }
                else if (difficultyChoice.getValue().equals("Hard")) {
                    controller.makeAIMove(4);
                }
            }

            updateGameContent(controller.getState());

            if(controller.getState().isComplete()) {
                AlertBox.endOfGameBox("GAME OVER", window);
            }
        });

        checker.setOnMousePressed(e -> {
            checker.setMouseCoor(new double[]{e.getSceneX(), e.getSceneY()});
        });

        checker.setOnMouseDragged(e -> {
            checker.relocate(e.getSceneX() - checker.getMouseCoor()[0] + (checker.getCurrCoor()[0] * SQUARESIZE),
                    e.getSceneY() - checker.getMouseCoor()[1] + (checker.getCurrCoor()[1] * SQUARESIZE));
        });

        return checker;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
