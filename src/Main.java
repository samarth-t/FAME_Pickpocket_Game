import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

import static javafx.scene.input.KeyCode.E;

public class Main extends Application {
    Stage primaryStage;
    GameRoot currentRoot;

    boolean pickPocketing=false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        currentRoot = new GameRoot(primaryStage);
        primaryStage.setScene(currentRoot.getScene());
        primaryStage.show();

        currentRoot.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentRoot.keyPress(event);
            }
        });

        currentRoot.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentRoot.keyRelease(event);
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}