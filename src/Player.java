import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * Player Object
 */
public class Player extends Object{

    public void move(int x, int y)
    {
        int nx = this.getX() + x;
        int ny = this.getY() + y;
        this.setX(nx);
        this.setY(ny);
    }
    
    public Player(int x, int y) 
	{
        super(x, y);

        URL loc = this.getClass().getResource("images/playerIcon.jpg");
        ImageIcon startIcon = new ImageIcon(loc);
        Image image = startIcon.getImage();
        this.setImage(image);
    }
}
