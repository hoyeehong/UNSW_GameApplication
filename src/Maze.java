import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * @author Yeehong Ho, Annie, Winnie
 * @date 30/5/2016
 * Creates the main selection page and proceed to the actual Maze
 */
public class Maze extends JFrame{

	private void initHomepage()
	{   	
		rootPanel = new BackgroundPanel();
		rootPanel.setBorder(new EmptyBorder(new Insets(250, 210, 360, 210)));
		
		JButton easyLevelBtn = new JButton("Easy Mode");
		easyLevelBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		easyLevelBtn.setBackground(Color.white);
        easyLevelBtn.setForeground(Color.black);
        easyLevelBtn.setOpaque(true); 
        easyLevelBtn.setBorderPainted(false); 		
		easyLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(EASY_SIZE, EASY_SIZE, "Easy Mode");
			}
		});
		      
        JButton mediumLevelBtn = new JButton("Medium Mode");
        mediumLevelBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
        mediumLevelBtn.setBackground(Color.white);
        mediumLevelBtn.setForeground(Color.black);
        mediumLevelBtn.setOpaque(true);
        mediumLevelBtn.setBorderPainted(false);    
        mediumLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(MEDIUM_SIZE, MEDIUM_SIZE, "Medium Mode");
			}
		});
                
        JButton hardLevelBtn = new JButton("Hard Mode");
        hardLevelBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
        hardLevelBtn.setBackground(Color.white);
        hardLevelBtn.setForeground(Color.black);
        hardLevelBtn.setOpaque(true);
        hardLevelBtn.setBorderPainted(false);      
        hardLevelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initMazeUI(HARD_SIZE, HARD_SIZE, "Hard Mode");
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
	
	private void initMazeUI(int width, int height, String mode)
	{
    	JLabel scoreText = new JLabel("  "+mode); 
		scoreText.setBorder(BorderFactory.createEtchedBorder());
		scoreText.setFont(new Font("AR JULIAN", Font.BOLD, 16));
			
		final Board board = new Board(width, height);
		setSize(board.getBoardWidth() + 7*OFFSET, board.getBoardHeight() + 3*OFFSET);
	
		JButton revealPathBtn = new JButton("  Reveal Path  ");
		revealPathBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		revealPathBtn.setForeground(new Color(253, 254, 254));
		revealPathBtn.setBackground(new Color(160, 64, 0));
		revealPathBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		revealPathBtn.setOpaque(true);
		revealPathBtn.setBorderPainted(false);
		revealPathBtn.setToolTipText("Click this for cheats");
		revealPathBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{	
				URL loc = this.getClass().getResource("images/path.jpg");
		        ImageIcon wall = new ImageIcon(loc);
		        Image image = wall.getImage();
		        	        
				LinkedList<Object> path = board.getBoardPath();
				for(Object o : path){
					o.setImage(image);
					repaint();
					board.requestFocusInWindow();
				}		    
			}

			public void mouseExited(MouseEvent e)
			{				
				LinkedList<Object> path = board.getBoardPath();
				for(Object o : path)
				{
					o.setImage(null);
					repaint();
					board.requestFocusInWindow();
				}		    	
			}
		});	
		
		JButton changeModeBtn = new JButton("Change Mode");
		changeModeBtn.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		changeModeBtn.setForeground(new Color(253, 254, 254));
		changeModeBtn.setBackground(new Color(160, 64, 0));
		changeModeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    changeModeBtn.setOpaque(true);
		changeModeBtn.setBorderPainted(false);
		changeModeBtn.setToolTipText("Click this if you want to change to other modes");
		changeModeBtn.addActionListener(new ActionListener()
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
		rightPanel.add(changeModeBtn);
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
    }
	
	private static Maze maze;
	private JPanel rootPanel;
	private final int OFFSET = 27;
	
	private final int EASY_SIZE = 17;
	private final int MEDIUM_SIZE = 25;
	private final int HARD_SIZE = 35;

}
