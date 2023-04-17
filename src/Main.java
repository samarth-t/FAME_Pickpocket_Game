import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    Heros hero;
    Camera action;
    Enemies enemies;

    boolean running, goNorth, goSouth, goEast, goWest;

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            hero.updateImageViewInScene(action,l);
            enemies.pathHandler(l);
            boolean canSee = enemies.checkView(hero.x, hero.y);
            System.out.println(canSee);
            enemies.updateImageViewInScene(action,l);

            if (goNorth) hero.inputHandler(Heros.Direction.UP);
            else if (goSouth) hero.inputHandler(Heros.Direction.DOWN);
            else if (goEast)  hero.inputHandler(Heros.Direction.RIGHT);
            else if (goWest)  hero.inputHandler(Heros.Direction.LEFT);
            else hero.inputHandler(Heros.Direction.IDLE);
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        hero = new Heros(200,250);
        action = new Camera(0,0);
        ArrayList<Heros.Direction> dir = new ArrayList<Heros.Direction>();
        dir.add(Heros.Direction.DOWN);
        dir.add(Heros.Direction.IDLE);
        dir.add(Heros.Direction.LEFT);
        dir.add(Heros.Direction.IDLE);
        dir.add(Heros.Direction.UP);
        dir.add(Heros.Direction.IDLE);
        dir.add(Heros.Direction.RIGHT);
        dir.add(Heros.Direction.IDLE);
        enemies = new Enemies(500,100,dir);

        primaryStage.setTitle("Pickpocket!");
        Group root = new Group();
        Pane pane = new Pane(root);
        Scene theScene = new Scene(pane, 600, 400,true);
        primaryStage.setScene(theScene);

        root.getChildren().add(hero.getImageView());
        root.getChildren().add(enemies.getImageView());

        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    goNorth = true; break;
                    case Z:    goNorth = true; break;
                    case S:    goSouth = true; break;
                    case A:    goWest  = true; break;
                    case Q:    goWest  = true; break;
                    case D:    goEast  = true; break;
                }
            }
        });

        theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    goNorth = false; break;
                    case Z:    goNorth = false; break;
                    case S:    goSouth = false; break;
                    case A:    goWest  = false; break;
                    case Q:    goWest  = false; break;
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