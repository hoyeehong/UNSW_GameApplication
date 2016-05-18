import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel{
	Image image;
	  public BackgroundPanel()
	  {
	    try
	    {
	      image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("bg.jpg"), "bg.jpg"));
	    }
	    catch (Exception e) {  }
	  }
	 
	  @Override
	  protected void paintComponent(Graphics g)
	  {
	    super.paintComponent(g); 
	    if (image != null)
	      g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	  }

}
