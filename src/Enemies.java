import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Enemies extends Heros{
    private ArrayList<Direction> path;

    public Enemies(double x, double y, ArrayList<Direction> path) {
        super(x, y);
        this.path = path;
        this.imageView = new ImageView(new Image("file:./img/MC.png", 96 * 4, 16 * 4, true, false));
        imageView.setViewport(new Rectangle2D(20,0,65,100));
    }
}