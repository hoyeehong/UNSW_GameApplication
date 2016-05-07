import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Creates the main Maze map
 *
 */
public class Maze extends JFrame{
	
	public Maze()
	{     
        initUI();
    }
	 
	private void initUI()
	{       
		add(new Board());
		
		setSize(800, 700);        
		setResizable(false);  
		setTitle("Moving character");        
		setLocationRelativeTo(null);      
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}
	 
	public static void main(String[] args)
	{	
		EventQueue.invokeLater(new Runnable()
		{        
			@Override
            public void run()
			{                       	
				Maze demo = new Maze();
            	demo.setVisible(true);
            }
        });
	}

}
