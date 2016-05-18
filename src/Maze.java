import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Creates the main Maze map
 *
 */
public class Maze extends JFrame{

	public static void main(String[] args)
	{	
		Maze maze = new Maze();
        maze.setVisible(true);      
	}
	
	private void initHomepage()
	{   	
		rootPanel = new BackgroundPanel();
		
		BorderLayout layout = new BorderLayout();
		layout.setHgap(15);
		layout.setVgap(5);
		
		rootPanel.setLayout(layout);
		rootPanel.setOpaque(false);
		rootPanel.setBorder(new EmptyBorder(new Insets(250, 321, 250, 321)));
		
		JLabel statusBar = new JLabel("Status");
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		
		JButton easyLevel = new JButton("Easy Mode");
		easyLevel.setBackground(Color.black);
        easyLevel.setForeground(Color.white);
        easyLevel.setBorderPainted(false); 		
		easyLevel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(easySize, easySize);      
			}
		});
		
        JButton mediumLevel = new JButton("Medium Mode");
        mediumLevel.setBackground(Color.black);
        mediumLevel.setForeground(Color.white);
        mediumLevel.setBorderPainted(false);    
        mediumLevel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(mediumSize, mediumSize);      
			}
		});	
        
        JButton hardLevel = new JButton("Hard Mode");
        hardLevel.setBackground(Color.black);
        hardLevel.setForeground(Color.white);
        hardLevel.setBorderPainted(false);      
        hardLevel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(hardSize, hardSize);      
			}
		});	

		rootPanel.add(easyLevel, BorderLayout.WEST);
		rootPanel.add(mediumLevel, BorderLayout.CENTER);
		rootPanel.add(hardLevel, BorderLayout.EAST);
		
		add(rootPanel);
		add(statusBar, BorderLayout.SOUTH);
		
		pack();        
	}
	
	private void initMazeUI(int width, int height)
	{
		Board board = new Board(width, height);
    	add(board);  	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    	setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2*OFFSET);      	       	
    	setLocationRelativeTo(null); 	
    	rootPanel.setVisible(false);
	}
	
	public Maze()
	{
		super("Maze");
		initHomepage();
    }
	private Image bg;
	private JPanel rootPanel;
	private final int OFFSET = 50;
	
	private final int easySize = 10;
	private final int mediumSize = 14;
	private final int hardSize = 50;
}
