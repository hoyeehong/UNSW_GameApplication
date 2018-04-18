import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * @author Yeehong Ho
 * @date 30/5/2016
 * Door Object
 */
public class Door extends Object{	
	public Door(int x, int y)
	{	        
		super(x, y);
	        
		URL loc = this.getClass().getResource("images/door.png");	        
		ImageIcon door = new ImageIcon(loc);        
		Image image = door.getImage();        
		this.setImage(image);	    
	}
}
