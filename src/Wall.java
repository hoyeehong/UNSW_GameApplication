import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * 
 * @author Yeehong
 *
 */
public class Wall extends Object{
	
	private Image image;

    public Wall(int x, int y)
    {
        super(x, y);

        URL loc = this.getClass().getResource("images/wall.png");
        ImageIcon wall = new ImageIcon(loc);
        image = wall.getImage();
        this.setImage(image);
    }

}
