import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
		panel = new JPanel();	
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		statusBar = new JLabel("Status");
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		
		ButtonListener buttonListener = new ButtonListener();
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(buttonListener);

        /*JButton easyLevel = new JButton("Easy Mode");
        easyLevel.addActionListener(buttonListener);

        JButton mediumLevel = new JButton("Medium Mode");
        mediumLevel.addActionListener(buttonListener);*/

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(startButton).addGap(500));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(startButton).addGap(500));
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.linkSize(startButton);
		
		add(panel, BorderLayout.CENTER);
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
        	panel.setVisible(false);
        	Board board = new Board();
            add(board);

            setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2*OFFSET);
                  
            //JButton o = (JButton) e.getSource();
            //String label = o.getText();
            //statusBar.setText(" " + label + " button clicked");
        }
    }
	
	public Maze()
	{
		super("Maze");
		initUI();			   
    }
	
	private JPanel panel;
	private JLabel statusBar;
	private final int OFFSET = 80;
}
