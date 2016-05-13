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
	
	private void initUI()
	{   	
		rootPanel = new JPanel();
		welcomePanel = new JPanel();
		BorderLayout layout = new BorderLayout();     	
		welcomePanel.setLayout(layout);	
		rootPanel.add(welcomePanel);
		rootPanel.setBorder(new EmptyBorder(new Insets(250, 321, 250, 321)));
		
		statusBar = new JLabel("Status");
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		
		ButtonListener buttonListener = new ButtonListener();
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(buttonListener);
        /*JButton easyLevel = new JButton("Easy Mode");
        easyLevel.addActionListener(buttonListener);
        JButton mediumLevel = new JButton("Medium Mode");
        mediumLevel.addActionListener(buttonListener);*/

		welcomePanel.add(startButton, BorderLayout.CENTER);

		add(rootPanel);	
		add(statusBar, BorderLayout.SOUTH);
		pack();
      
		setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
	}
	private class ButtonListener implements ActionListener
	{
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	rootPanel.setVisible(false);

        	Board board = new Board();  	
        	setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2*OFFSET);
        	add(board);    	
        }
    }
	
	public Maze()
	{
		super("Maze");
		initUI();
    }
	
	private JPanel rootPanel;
	private JPanel welcomePanel;
	private JLabel statusBar;
	private final int OFFSET = 40;
}
