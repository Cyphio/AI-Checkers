package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The AlertBox class displays a Stage
 */
public class AlertBox {
    
    /**
     * The endOfGameBox method initialises an un-closeable alert box and populates it according to
     * the message passed as a parameter.
     */
    public static void endOfGameBox(Stage guiStage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game over");
        Label text = new Label();
        text.setText("Game over!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        text.setTextFill(Paint.valueOf("#000000"));
        
        window.setOnHiding(e -> {
           guiStage.close(); 
        });
        
        VBox ui = new VBox(10);
        ui.getChildren().addAll(text);
        ui.setAlignment(Pos.CENTER);
        
        StackPane pane = new StackPane();
        pane.getChildren().add(ui);
        
        window.setScene(new Scene(pane, 600, 300));
        window.showAndWait();
    }

    public static void ruleBox() {
        Stage window = new Stage();
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Rules");
        Label text = new Label();
        String rules = "The object of checkers is to eliminate all opposing checkers, or to put your opponent in a situation in which they can't make a move.\n" +
                "\nHere are a set of basic rules:\n" +
                "\n1. The Black Player plays first. Turns will then proceed alternately" +
                "\n2. If you have chosen to play against an AI, they will play as the Red Player." +
                "\n3. Checkers can only move diagonally forward along black squares relative to their starting position." +
                "\n4. There are two types of moves: capturing and non-capturing." +
                "\n4a. Capturing moves involve diagonally jumping forward over an opposing piece to a free square. The opposing piece is removed from the board and a point is received." +
                "\n4b. Non-capturing moves are normal diagonal forward moves to a free square." +
                "\n5. When a checker reaches the opponent's edge of the board (the 'King's row') it is crowned - signified by a gold outline." +
                "\n5a. A crowned checker can move and jump in all directions." +
                "\n5b. If a normal checker captures a crowned checker (regicide), the normal checker is crowned.";
        text.setText(rules);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setTextFill(Paint.valueOf("#000000"));
        text.setMaxWidth(1250);
        text.setWrapText(true);
        text.setTextAlignment(TextAlignment.CENTER);

        VBox ui = new VBox(10);
        ui.getChildren().addAll(text);
        ui.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(ui);

        window.setScene(new Scene(pane, 1300, 500));
        window.show();
    }
}
