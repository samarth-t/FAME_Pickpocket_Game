import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Enemies extends Heros{
    private ArrayList<Direction> path;

    public Enemies(double x, double y, ArrayList<Direction> path) {
        super(x, y);
        this.path = path;
        this.imageView = new ImageView(new Image("file:./img/Civ_1.png", 96 * 4, 16 * 4, true, false));
        imageView.setViewport(new Rectangle2D(20,0,65,100));
        this.speed = 1;
    }

    public void pathHandler(long time) {
        int index = (int) (((time/10000000)/150) % path.size());
        curDirection = path.get(index);
        inputHandler(curDirection);
    }

    public boolean checkView(double heroX, double heroY) {
        if(curDirection == Direction.LEFT) {
            if (heroX < x && (abs(y - heroY)) < imageView.getViewport().getHeight()) {
                return true;
            }
        }
        if(curDirection == Direction.RIGHT) {
            if (heroX > x && (abs(y - heroY)) < imageView.getViewport().getHeight()) {
                return true;
            }
        }
        if(curDirection == Direction.UP) {
            if (heroY < y && (abs(x - heroX)) < imageView.getViewport().getWidth()) {
                return true;
            }
        }
        if(curDirection == Direction.DOWN||curDirection== Direction.IDLE) {
            if (heroY > y && (abs(x - heroX)) < imageView.getViewport().getWidth()) {
                return true;
            }
        }
        return false;
    }
    public boolean isClose(double heroX,double heroY){
        return abs(x - heroX) < 50 && abs(y - heroY) < 50;
    }
}
