import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameRoot extends Group {
    Heros heros;
    Enemies enemies;
    Camera camera;

    Stage stage;
    boolean running, goNorth, goSouth, goEast, goWest, pickPocketing=false;

    public GameRoot(Stage stage) {
        super();
        this.stage = stage;
        heros = new Heros(200,250);
        camera = new Camera(0,0);
        enemies = new Enemies(500,100);
        Pane pane = new Pane(this);
        Scene theScene = new Scene(pane, 600, 400,true);
        this.getChildren().add(heros.getImageView());
        this.getChildren().add(enemies.getImageView());

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                heros.updateImageViewInScene(camera,l);
                enemies.pathHandler(l);
                boolean canSee = enemies.checkView(heros.x, heros.y);
                System.out.println(canSee);
                if(canSee && pickPocketing){
                    Group loseRoot = new Group();
                    Scene loseScene = new Scene(loseRoot,600,400,true);
                    Pane losePane = new Pane(loseRoot);
                    loseRoot.getChildren().add(new ImageView(new Image("file:./img/lose.png")));
                    stage.setScene(loseScene);
                    this.end();
                }

                enemies.updateImageViewInScene(camera,l);

                if (goNorth) heros.inputHandler(Heros.Direction.UP);
                else if (goSouth) heros.inputHandler(Heros.Direction.DOWN);
                else if (goEast)  heros.inputHandler(Heros.Direction.RIGHT);
                else if (goWest)  heros.inputHandler(Heros.Direction.LEFT);
                else heros.inputHandler(Heros.Direction.IDLE);

                if(enemies.isClose(heros.x, heros.y) && pickPocketing && !canSee){
                    Group winRoot = new Group();
                    Scene victoryScene = new Scene(winRoot,600,400,true);
                    Pane victoryPane = new Pane(winRoot);
                    winRoot.getChildren().add(new ImageView(new Image("file:./img/victory.png")));
                    stage.setScene(victoryScene);
                    this.end();
                }
            }

            private void end() {
                heros = null;
                enemies = null;
                camera = null;
                goNorth = false;
                goSouth = false;
                goEast = false;
                goWest = false;
                pickPocketing = false;
            }
        };
        animationTimer.start();
    }


    public void keyPress(KeyEvent event) {
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

    public void keyRelease(KeyEvent event) {
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
}
