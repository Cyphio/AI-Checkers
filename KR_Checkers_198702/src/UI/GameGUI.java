package UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameGUI extends Application {

    public static final int squareSize = 100;
    public static final int width = 8;
    public static final int height = 8;

    private Group squareGroup = new Group();
    private Group checkerGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * squareSize, height * squareSize);
        root.getChildren().addAll(squareGroup, checkerGroup);
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                if((i+j)%2 == 0) {  squareGroup.getChildren().add(new BlackSquare(new int[]{i, j})); }
                else { squareGroup.getChildren().add(new WhiteSquare(new int[]{i, j})); }
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
