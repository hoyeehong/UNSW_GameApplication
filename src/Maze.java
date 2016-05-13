import javax.swing.JFrame;

/**
 * Creates the main Maze map
 *
 */
public class Maze extends JFrame{
	private final int OFFSET = 30;
	public Maze()
	{
		super("The title bar");
        initUI();
    }
	 
	private void initUI()
	{       
		Board board = new Board();
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2*OFFSET);
        setLocationRelativeTo(null);
        //setTitle("Maze"); 
	}
	 
	public static void main(String[] args)
	{	
		Maze maze = new Maze();
        maze.setVisible(true);
	}

}
