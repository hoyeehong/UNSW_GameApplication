import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * Wall Object
 */
public class Wall extends Object{
		
    public Wall(int x, int y)
    {
        super(x, y);

        URL loc = this.getClass().getResource("images/wall.png");
        ImageIcon wall = new ImageIcon(loc);
        image = wall.getImage();
        this.setImage(image);
    }

    private Image image;
}
