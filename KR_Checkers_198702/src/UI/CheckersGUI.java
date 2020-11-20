package UI;

import Logic.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckersGUI extends Application {

    private GridPane grid;
    private int boardSize;
    private Game game;
    private Button[][] input;
    private Stage window;

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Checkers Main Menu");
        grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);

        Button generateNewGame = new Button("Generate Game");
        generateNewGame.setOnAction(e -> {
            createGame();
        });

        Button load = new Button ("Load");
        load.setOnAction(e -> {
        });

        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.setSpacing(5);
        h.getChildren().addAll(generateNewGame, load);

        VBox v = new VBox();
        v.setPadding(new Insets(25, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h, grid);

        window.setScene(new Scene(v, 750, 750));
        window.show();
    }

    private void createGame() {
        game = new Game(8, 12);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
