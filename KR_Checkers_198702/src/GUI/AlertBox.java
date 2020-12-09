package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The AlertBox class displays a Stage that congratulates or commiserates the 
 * player.
 */
public class AlertBox {
    
    /**
     * The display method initialises the Stage and populates it according to 
     * the message passed as a parameter.
     */
    public static void display(String message, Stage guiStage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(message);
        ImageView iv = new ImageView();
        Label text = new Label();
        text.setText(message);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        text.setTextFill(Paint.valueOf("#000000"));
        
        window.setOnHiding(e -> {
           guiStage.close(); 
        });
        
        VBox ui = new VBox(10);
        ui.getChildren().addAll(text);
        ui.setAlignment(Pos.CENTER);
        
        iv.setFitWidth(500);
        iv.setFitHeight(500);
        
        StackPane pane = new StackPane();
        pane.getChildren().addAll(iv, ui);
        
        window.setScene(new Scene(pane, 750, 300));
        window.showAndWait();
    }
}
