import java.awt.*;
import java.awt.event.*;

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
		rootPanel.setBorder(new EmptyBorder(new Insets(250, 250, 390, 200)));
		
		JButton easyLevelBtn = new JButton("Easy Mode");
		easyLevelBtn.setBackground(Color.black);
        easyLevelBtn.setForeground(Color.white);
        easyLevelBtn.setBorderPainted(false); 		
		easyLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(easySize, easySize);      
			}
		});
		      
        JButton mediumLevelBtn = new JButton("Medium Mode");
        mediumLevelBtn.setBackground(Color.black);
        mediumLevelBtn.setForeground(Color.white);
        mediumLevelBtn.setBorderPainted(false);    
        mediumLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(mediumSize, mediumSize);      
			}
		});
                
        JButton hardLevelBtn = new JButton("Hard Mode");
        hardLevelBtn.setBackground(Color.black);
        hardLevelBtn.setForeground(Color.white);
        hardLevelBtn.setBorderPainted(false);      
        hardLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(hardSize, hardSize);      
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

        rootPanel.add(easyLevelBtn, BorderLayout.EAST);
		rootPanel.add(mediumLevelBtn, BorderLayout.CENTER);
		rootPanel.add(hardLevelBtn, BorderLayout.WEST);
	
		add(rootPanel);
		pack();        
	}
	
	private void initMazeUI(int width, int height)
	{
    	JLabel scoreBar = new JLabel("Score");
		scoreBar.setBorder(BorderFactory.createEtchedBorder());
		add(scoreBar, BorderLayout.SOUTH);

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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    }
	
	private JPanel rootPanel;
	private final int OFFSET = 80; //Adjusts the height of frame
	
	private final int easySize = 10;
	private final int mediumSize = 14;
	private final int hardSize = 50;

}
