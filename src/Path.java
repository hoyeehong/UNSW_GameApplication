import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * Path object 
 */
public class Path extends Object{

    public Path(int x, int y)
    {
        super(x, y);

        URL loc = this.getClass().getResource("images/plainPath.jpg");
        ImageIcon wall = new ImageIcon(loc);
        image = wall.getImage();
        this.setImage(image);
    }

    private Image image;

}
