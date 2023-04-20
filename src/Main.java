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
    Heros hero;
    Camera action;
    Enemies enemies;
    Stage primaryStage;

    boolean running, goNorth, goSouth, goEast, goWest, pickPocketing=false,ended = false;

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            hero.updateImageViewInScene(action,l);
            enemies.pathHandler(l);
            boolean canSee = enemies.checkView(hero.x, hero.y);
            System.out.println(canSee);
            if(canSee){
                Group loseRoot = new Group();
                Scene loseScene = new Scene(loseRoot,600,400,true);
                Pane losePane = new Pane(loseRoot);
                loseRoot.getChildren().add(new ImageView(new Image("file:./img/lose.png")));
                primaryStage.setScene(loseScene);
            }
            enemies.updateImageViewInScene(action,l);

            if (goNorth) hero.inputHandler(Heros.Direction.UP);
            else if (goSouth) hero.inputHandler(Heros.Direction.DOWN);
            else if (goEast)  hero.inputHandler(Heros.Direction.RIGHT);
            else if (goWest)  hero.inputHandler(Heros.Direction.LEFT);
            else hero.inputHandler(Heros.Direction.IDLE);
            if(enemies.isClose(hero.x, hero.y)&&pickPocketing){
                Group winRoot = new Group();
                Scene victoryScene = new Scene(winRoot,600,400,true);
                Pane victoryPane = new Pane(winRoot);
                winRoot.getChildren().add(new ImageView(new Image("file:./img/victory.png")));
                primaryStage.setScene(victoryScene);
            }
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        hero = new Heros(200,250);
        action = new Camera(0,0);
        enemies = new Enemies(500,100);

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
                    case E:    pickPocketing = true;break;
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
                    case E:    pickPocketing = false; break;
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