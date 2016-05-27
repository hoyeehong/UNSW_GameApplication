import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The actual Maze GUI
 */
public class Board extends JPanel{
	
    private final int OFFSET = 10; 
    private final int SPACE = 26;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private LinkedList<Object> walls = new LinkedList<Object>();
    private LinkedList<Object> paths = new LinkedList<Object>();
    private Door door;
    private Player player;
     
    private int width;
    private int height;
 
    private int currentX;
    private int currentY;
      
    private boolean completed = false;
    
    
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld(width, height);	
	}

	public LinkedList<Object> getBoardPath(){
		return this.paths;
	}
	
	public void initWorld(int width, int height)
	{     
		MazeGen maze = new MazeGen(width,height);
		
		String generatedMaze = maze.generateMaze();
		
        int x = OFFSET;
        int y = OFFSET;
        
        Wall wall;
        Path path;
        
        for (int i = 0; i < generatedMaze.length(); i++)
        {
            char item = generatedMaze.charAt(i);
            if (item == '\n')
            {
                y += SPACE;
                if (this.width < x)
                {
                    this.width = x;
                }
                x = OFFSET;
            } 
            else if (item == '#')
            {
                wall = new Wall(x, y);
                walls.add(wall);
                x += SPACE;
            }
            else if (item == 'x')
            {
                path = new Path(x, y);
                paths.add(path);
                x += SPACE;
            }
            else if (item == '.')
            {
                door = new Door(x, y);
                x += SPACE;
            }
            else if (item == '@')
            {
                player = new Player(x, y);
                x += SPACE;
            }
            else if (item == ' ')
            {
                x += SPACE;
            }
            this.height = y;
        }
    }
	public int getBoardWidth() {
        return this.width;
    }
    public int getBoardHeight() {
    	return this.height;
    }    
    public int getPlayerX()
	{
		return this.currentX;
	}
	public int getPlayerY()
	{
		return this.currentY;
	}
	public void setGameStatus(boolean status)
	{
		if(this.completed = true)
		{
			this.completed = status;
		}
	}
	
    public void buildWorld(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        LinkedList<Object> world = new LinkedList<Object>();
        world.addAll(walls);
        world.addAll(paths);
        world.add(door);
        world.add(player);

        for (int i = 0; i < world.size(); i++)
        {
        	Object item = (Object) world.get(i);
            if ((item instanceof Player)||(item instanceof Door)||(item instanceof Path))
            {
                g.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, this);
            } 
            else
            {
                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            }

            if (completed)
            {
                g.setColor(new Color(0, 0, 0));
                //g.drawString("Completed", 50, 150);
            }
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        buildWorld(g);
    }
    class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if (completed)
            {          	
                return;
            }        
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT)
            {
                if (checkWallCollision(player,LEFT_COLLISION)){               	
                    return;
                }
                if (checkDoorCollision(LEFT_COLLISION)){ 
                	JOptionPane.showMessageDialog(null, "You have completed the Maze!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                	return;
                }
                player.move(-SPACE, 0);
                currentX = player.getX();
                currentY = player.getY();
            } 
            else if (key == KeyEvent.VK_RIGHT)
            {
                if (checkWallCollision(player,RIGHT_COLLISION)){
                    return;
                }
                if (checkDoorCollision(RIGHT_COLLISION)){
                	JOptionPane.showMessageDialog(null, "You have completed the Maze!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                	return;
                }
                player.move(SPACE, 0);
                currentX = player.getX();
                currentY = player.getY();
            } 
            else if (key == KeyEvent.VK_UP)
            {            
            	if (checkWallCollision(player,TOP_COLLISION)){
                    return;
                }
                if (checkDoorCollision(TOP_COLLISION)){
                	JOptionPane.showMessageDialog(null, "You have completed the Maze!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                	return;
                	
                }
                player.move(0, -SPACE);
                currentX = player.getX();
                currentY = player.getY();
            } 
            else if (key == KeyEvent.VK_DOWN)
            {
                if (checkWallCollision(player,BOTTOM_COLLISION)){
                    return;
                }
                if (checkDoorCollision(BOTTOM_COLLISION)){
                	JOptionPane.showMessageDialog(null, "You have completed the Maze!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                	return;
                }
                player.move(0, SPACE);
                currentX = player.getX();
                currentY = player.getY();
            } 
            else if (key == KeyEvent.VK_R)
            {
                restartLevel();
            }
            repaint();
        }
    }

    private boolean checkWallCollision(Object player, int type)
    {
        if (type == LEFT_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (player.isLeftCollision(wall)){
                    return true;
                }
            }
            return false;

        } 
        else if (type == RIGHT_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (player.isRightCollision(wall)){
                    return true;
                }
            }
            return false;
        } 
        else if (type == TOP_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (player.isTopCollision(wall)){
                    return true;
                }
            }
            return false;

        }
        else if (type == BOTTOM_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (player.isBottomCollision(wall)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkDoorCollision(int type)
    {
        if (type == LEFT_COLLISION) 
        {       	
        	if (player.isLeftCollision(door)) 
        	{
        		completed = true;
                repaint();
        		return true;       		 
        	}
        }                 
        else if (type == RIGHT_COLLISION) 
        {    
        	if (player.isRightCollision(door)) 
        	{     
        		completed = true;
                repaint();
        		return true;     		                        		                                
        	}  
        }        
        else if (type == TOP_COLLISION)         
        {                   	  	
        	if (player.isTopCollision(door))       	  	
        	{   
        		completed = true;
                repaint();
        		return true;       		                     	  	
        	}  
        }        
        else if (type == BOTTOM_COLLISION)
        {       
        	if (player.isBottomCollision(door))
        	{ 
        		completed = true;
                repaint();
        		return true;     		                              		             
        	}   
        }
       return false;
    }

    public void restartLevel()
    {
        paths.clear();        
        walls.clear();
        initWorld(width, height);
        if (completed)
        {
            completed = false;
        }
    }

	
}

