//package GUI;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Shape;
//import javafx.stage.Stage;
//import jfxtras.labs.scene.layout.ScalableContentPane;
//import jfxtras.labs.util.event.MouseControlUtil;
//
//public class LegacyGUI extends Application {
//
//    private GridPane grid;
//    private int boardSize;
//    private Game game;
//    private Button[][] input;
//    private Stage window;
//
//    @Override
//    public void start(Stage primaryStage) {
//        this.window = primaryStage;
//
//        ScalableContentPane scaledPane = new ScalableContentPane();
//        Pane root = scaledPane.getContentPane();
//
//        Shape shape = new Circle(25);
//        shape.setFill(Color.RED);
//        shape.setStroke(Color.WHITE);
//
//        MouseControlUtil.makeDraggable(shape);
//
//        root.getChildren().add(shape);
//
//        Label boardSizeMsg = new Label("Board size: ");
//        ChoiceBox<String> boardSize = new ChoiceBox<>();
//        boardSize.getItems().addAll("7", "8", "9", "10");
//        boardSize.setValue("8");
//
//        Label nCheckersMsg = new Label("Number of chekers: ");
//        ChoiceBox<String> nCheckers = new ChoiceBox<>();
//        nCheckers.getItems().addAll("10", "12", "14", "14");
//        nCheckers.setValue("12");
//
//        Label difficultyMsg = new Label("Difficulty: ");
//        ChoiceBox<String> difficulty = new ChoiceBox<>();
//        difficulty.getItems().addAll("Easy", "Regular", "Hard");
//        difficulty.setValue("Regular");
//
//        createGame(8, 12, "Regular");
//
//        Button generateNewGame = new Button("Generate Game");
//        generateNewGame.setOnAction(e -> {
//            createGame(Integer.valueOf(boardSize.getValue()), Integer.valueOf(nCheckers.getValue()), difficulty.getValue());
//        });
//
//        Button save = new Button("Save");
//        save.setOnAction(e -> {
//            try {
//                ResourceManager.save(this.game, "a.save");
//            } catch (Exception error) {
//                System.out.println("Couldn't save: " + error.getMessage());
//            }
//        });
//
//        Button load = new Button("Load");
//        load.setOnAction(e -> {
//            try {
//                Game data = (Game) ResourceManager.load("a.save");
//                this.game = data;
//                displayGame();
//                interact();
//
//            } catch (Exception error) {
//                System.out.println("Couldn't load: " + error.getMessage());
//            }
//        });
//
//        Scene scene = new Scene(scaledPane, 800, 800);
//        window.setTitle("Checkers");
//        window.setScene(scene);
//        window.show();
//    }
//
//    private void createGame(int boardSize, int nCheckers, String difficulty) {
//        game = new Game(boardSize, nCheckers);
//    }
//
//    public void interact() { }
//
//    public void displayGame() { }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
