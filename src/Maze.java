import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Creates the main Maze map
 *
 */
public class Maze extends JFrame{

	private void initHomepage()
	{   	
		rootPanel = new BackgroundPanel();
		rootPanel.setBorder(new EmptyBorder(new Insets(250, 210, 360, 210)));
		
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
        
        rootPanel.add(easyLevelBtn, BorderLayout.EAST);
		rootPanel.add(mediumLevelBtn, BorderLayout.CENTER);
		rootPanel.add(hardLevelBtn, BorderLayout.WEST);
	
		add(rootPanel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initMazeUI(final int width, final int height)
	{
    	JLabel scoreText = new JLabel("Score: " /*+getScore()*/); 
		scoreText.setBorder(BorderFactory.createEtchedBorder());
		scoreText.setFont(new Font("AR JULIAN", Font.BOLD, 16));
			
		Board board = new Board(width, height);
		setSize(board.getBoardWidth() + 7*OFFSET, board.getBoardHeight() + 3*OFFSET);
	
		JButton revealPathBtn = new JButton("Reveal Path!");
		revealPathBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		revealPathBtn.setForeground(new Color(253, 254, 254));
		revealPathBtn.setBackground(new Color(160, 64, 0));
		revealPathBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton restartBtn = new JButton("Restart Game");
		restartBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		restartBtn.setForeground(new Color(253, 254, 254));
		restartBtn.setBackground(new Color(160, 64, 0));
		restartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		restartBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				maze = new Maze();
			    maze.setVisible(true); 
			    dispose();				     
			}
		});		
		
		JPanel rightPanel = new JPanel();
		rightPanel.add(revealPathBtn);
		rightPanel.add(restartBtn);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
	
		add(scoreText, BorderLayout.NORTH);		
		add(board);
		add(rightPanel, BorderLayout.EAST);
		this.getContentPane().setBackground(new Color(189, 195, 199));
		
    	rootPanel.setVisible(false); 	
	}
		
	public static void main(String[] args)
	{	
		maze = new Maze();
        maze.setVisible(true);      
	}
	public Maze()
	{
		super("Maze");
		initHomepage();
		this.setFont(new Font("Display", Font.BOLD, 12));
    }
	
	private static Maze maze;
	private JPanel rootPanel;
	private final int OFFSET = 27;
	
	private final int easySize = 10;
	private final int mediumSize = 14;
	private final int hardSize = 50;

}
