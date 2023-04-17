import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heros {

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        IDLE
    }
    protected double x;
    protected double y;

    protected Direction curDirection;

    protected double speed = 1.5;

    protected ImageView imageView;

    public Heros(double x, double y) {
        this.x = x;
        this.y = y;
        this.imageView = new ImageView(new Image("file:./img/MC.png", 96 * 4, 16 * 4, true, false));
        imageView.setViewport(new Rectangle2D(20,0,64,64));
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void updateImageViewInScene(Camera man, long time){
        imageView.setX(x-man.getX());
        imageView.setY(y-man.getY());

        //Sprite direction offset
        int offset = curDirection == Direction.DOWN ? 0 : curDirection == Direction.UP ? 1 : 2;
        if (curDirection == Direction.RIGHT) {
            imageView.setScaleX(-1);
        } else {
            imageView.setScaleX(1);
        }

        //Walking animation
        int index = (int) (((time/1000000)/150)%2);

        if (curDirection == Direction.IDLE) {
            offset = 0;
            index = 0;
        }

        imageView.setViewport(new Rectangle2D((offset*64)+(3*64*index),0,64,64));
    }

    public void physics (){
        y+=0.5;
    }

    public void inputHandler(Direction direction) {
        curDirection = direction;
        switch(direction) {
            case UP:
                y-=speed;
                if(y <= 0) { y = 0; }
                break;
            case DOWN:
                y+=speed;
                int charHeight = (int) imageView.getViewport().getHeight();
                if(y >= 400-charHeight) { y = 400-charHeight; }
                break;
            case LEFT:
                x-=speed;
                if(x <= 0) { x = 0; }
                break;
            case RIGHT:
                x+=speed;
                int charWidth = (int) imageView.getViewport().getWidth();
                if(x >= 600-charWidth) { x = 600-charWidth; }
                break;
        }
    }
}
