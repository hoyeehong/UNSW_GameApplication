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
		rootPanel = new BackgroundPanel();
		welcomePanel = new BackgroundPanel();
		welcomePanel.setOpaque(false);
		BorderLayout layout = new BorderLayout();     	
		welcomePanel.setLayout(layout);	
		
		rootPanel.add(welcomePanel);
		rootPanel.setBorder(new EmptyBorder(new Insets(40, 40, 40, 40)));
		layout.setHgap(15);
		layout.setVgap(5);
		
		
		statusBar = new JLabel("Status");
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		
		ButtonListener buttonListener = new ButtonListener();
		
		//Easy Mode button
        JButton easyLevel = new JButton("Easy Mode");
        easyLevel.addActionListener(buttonListener);
        easyLevel.setBackground(Color.black);
        easyLevel.setForeground(Color.white);
        easyLevel.setBorderPainted(false); 
        //Medium Mode button
        JButton mediumLevel = new JButton("Medium Mode");
        mediumLevel.addActionListener(buttonListener);
        mediumLevel.setBackground(Color.black);
        mediumLevel.setForeground(Color.white);
        mediumLevel.setBorderPainted(false);
        //Hard Mode button
        JButton hardLevel = new JButton("Hard Mode");
        hardLevel.addActionListener(buttonListener);
        hardLevel.setBackground(Color.black);
        hardLevel.setForeground(Color.white);
        hardLevel.setBorderPainted(false);
        

        welcomePanel.add(easyLevel, BorderLayout.WEST);
        welcomePanel.add(mediumLevel, BorderLayout.CENTER);
		welcomePanel.add(hardLevel, BorderLayout.EAST);
		

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
	
	
	class BackgroundPanel extends JPanel
	{
	  Image image;
	  public BackgroundPanel()
	  {
	    try
	    {
	      image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("image.jpg"), "image.jpg"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	  }
	 
	  @Override
	  protected void paintComponent(Graphics g)
	  {
	    super.paintComponent(g); 
	    if (image != null)
	      g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	  }
	}
	
	
	
	
	public Maze()
	{
		super("MazeMaster");
		initUI();
    }
	
	private JPanel rootPanel;
	private JPanel welcomePanel;
	private JLabel statusBar;
	private final int OFFSET = 40;
	private ImageIcon icon;
}
