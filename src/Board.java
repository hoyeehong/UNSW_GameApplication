import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Draws and paints the Maze and the movement of the character is set to
 * Delay = 3 on the Maze.
 * @author Yeehong
 *
 */
public class Board extends JPanel implements ActionListener{
	private Timer timer;
    private Character character;
    private final int DELAY = 3;

	public Board(){
		initBoard();
	}
	private void initBoard(){
		addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        character = new Character();

        timer = new Timer(DELAY, this);
        timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{        
		super.paintComponent(g);        
		doDrawing(g);       
		Toolkit.getDefaultToolkit().sync();    
	}
    
	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;     
		g2d.drawImage(character.getImage(), character.getX(), character.getY(), this);    
	}
   
	@Override
	public void actionPerformed(ActionEvent e)
	{
	    character.move();        
	    repaint();      
	}
	    
	private class TAdapter extends KeyAdapter
	{      
		@Override	        
		public void keyReleased(KeyEvent e)
		{        	
			character.keyReleased(e);        
		}        
		@Override        
		public void keyPressed(KeyEvent e) 
		{        	
			character.keyPressed(e);        
		}    
	}
}

