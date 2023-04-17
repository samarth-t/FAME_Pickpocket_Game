import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Enemies extends Heros{
    private ArrayList<Direction> path;

    public Enemies(double x, double y, ArrayList<Direction> path) {
        super(x, y);
        this.path = path;
        this.imageView = new ImageView(new Image("file:./img/Civ_1.png", 96 * 4, 16 * 4, true, false));
        imageView.setViewport(new Rectangle2D(20,0,65,100));
    }

    public void pathHandler(long time) {
        int index = (int) (((time/10000000)/150) % path.size());
        curDirection = path.get(index);
        inputHandler(curDirection);
    }

    public boolean checkView(double heroX, double heroY) {
        if(curDirection == Direction.LEFT) {
            if (heroX < x && (Math.abs(y - heroY)) < imageView.getViewport().getHeight()) {
                return true;
            }
        }
        if(curDirection == Direction.RIGHT) {
            if (heroX > x && (Math.abs(y - heroY)) < imageView.getViewport().getHeight()) {
                return true;
            }
        }
        if(curDirection == Direction.UP) {
            if (heroY < y && (Math.abs(x - heroX)) < imageView.getViewport().getWidth()) {
                return true;
            }
        }
        if(curDirection == Direction.DOWN||curDirection== Direction.IDLE) {
            if (heroY > y && (Math.abs(x - heroX)) < imageView.getViewport().getWidth()) {
                return true;
            }
        }
        return false;
    }
}
