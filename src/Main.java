import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    Heros hero;
    Camera action;

    boolean running, goNorth, goSouth, goEast, goWest;

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
//            toto.physics();
            hero.updateImageViewInScene(action,l);

            if (goNorth) hero.inputHandler(Heros.Direction.UP);
            else if (goSouth) hero.inputHandler(Heros.Direction.DOWN);
            else if (goEast)  hero.inputHandler(Heros.Direction.RIGHT);
            else if (goWest)  hero.inputHandler(Heros.Direction.LEFT);
            else hero.inputHandler(Heros.Direction.IDLE);
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        hero =new Heros(1200,250);
        action = new Camera(1000,0);

        primaryStage.setTitle("Hello world");
        Group root = new Group();
        Pane pane = new Pane(root);
        Scene theScene = new Scene(pane, 600, 400,true);
        primaryStage.setScene(theScene);

        root.getChildren().add(hero.getImageView());


        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    goNorth = true; break;
                    case S:    goSouth = true; break;
                    case A:    goWest  = true; break;
                    case D:    goEast  = true; break;
                }
            }
        });

        theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    goNorth = false; break;
                    case S:    goSouth = false; break;
                    case A:    goWest  = false; break;
                    case D:    goEast  = false; break;
                }
            }
        });

        primaryStage.show();
        animationTimer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}