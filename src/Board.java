import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 * 
 * @author Yeehong
 */
public class Board extends JPanel{
	
    private final int OFFSET = 50;
    private final int SPACE = 40;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList walls = new ArrayList();
    private ArrayList baggs = new ArrayList();
    private ArrayList door = new ArrayList();
    private Player player;
    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "###  ### ### \n"
          + "#@#  # # # # \n"
          + "# #  # # # # \n"
          + "# #### ### ##\n"
          + "#           #\n"
          + "# ###########\n"
          + "# # #    ###\n"
          + "# # # ## #.#####\n"
          + "#      #       #\n"
          + "# ### ##########\n"
          + "#   # #\n"
          + "#######\n";
    		

    
	public Board()
	{
		addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
	}

	public final void initWorld()
	{      
        int x = OFFSET;
        int y = OFFSET;
        
        Wall wall; 
        Door d;
        Baggage b;

        for (int i = 0; i < level.length(); i++)
        {
            char item = level.charAt(i);

            if (item == '\n')
            {
                y += SPACE;
                if (this.w < x)
                {
                    this.w = x;
                }
                x = OFFSET;
            } 
            else if (item == '#')
            {
                wall = new Wall(x, y);
                walls.add(wall);
                x += SPACE;
            }
            else if (item == '$')
            {         
            	b = new Baggage(x, y);
                baggs.add(b);
                x += SPACE;
            }
            else if (item == '.')
            {
                d = new Door(x, y);
                door.add(d);
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
            h = y;
        }
    }
	public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
	
    public void buildWorld(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(walls);
        world.addAll(door);
        world.add(player);
        world.addAll(baggs);

        for (int i = 0; i < world.size(); i++)
        {
        	Object item = (Object) world.get(i);
            if ((item instanceof Player) || (item instanceof Door))
            {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } 
            else
            {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed)
            {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
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
                if (checkBagCollision(LEFT_COLLISION)){
                    return;
                }
                player.move(-SPACE, 0);
            } 
            else if (key == KeyEvent.VK_RIGHT)
            {
                if (checkWallCollision(player,RIGHT_COLLISION)){
                    return;
                }
                if (checkBagCollision(RIGHT_COLLISION)){
                    return;
                }
                player.move(SPACE, 0);
            } 
            else if (key == KeyEvent.VK_UP)
            {            
            	if (checkWallCollision(player,TOP_COLLISION)){
                    return;
                }
                if (checkBagCollision(TOP_COLLISION)){
                    return;
                }
                player.move(0, -SPACE);
            } 
            else if (key == KeyEvent.VK_DOWN)
            {
                if (checkWallCollision(player,BOTTOM_COLLISION)){
                    return;
                }
                if (checkBagCollision(BOTTOM_COLLISION)){
                    return;
                }
                player.move(0, SPACE);
            } 
            else if (key == KeyEvent.VK_R)
            {
                restartLevel();
            }
            repaint();
        }
    }

    private boolean checkWallCollision(Object character, int type)
    {
        if (type == LEFT_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (character.isLeftCollision(wall)){
                    return true;
                }
            }
            return false;

        } 
        else if (type == RIGHT_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (character.isRightCollision(wall)){
                    return true;
                }
            }
            return false;
        } 
        else if (type == TOP_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (character.isTopCollision(wall)){
                    return true;
                }
            }
            return false;

        }
        else if (type == BOTTOM_COLLISION)
        {
            for (int i = 0; i < walls.size(); i++){
                Wall wall = (Wall) walls.get(i);
                if (character.isBottomCollision(wall)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBagCollision(int type)
    {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (player.isLeftCollision(bag)) {

                    for (int j=0; j < baggs.size(); j++) {
                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag, LEFT_COLLISION)){
                            return true;
                        }
                    }
                    bag.move(-SPACE, 0);
                    isCompleted();
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (player.isRightCollision(bag)) {
                    for (int j=0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(SPACE, 0);
                    isCompleted();                   
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (player.isTopCollision(bag)) {
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                TOP_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, -SPACE);
                    isCompleted();
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {
        
            for (int i = 0; i < baggs.size(); i++) {

                Baggage bag = (Baggage) baggs.get(i);
                if (player.isBottomCollision(bag)) {
                    for (int j = 0; j < baggs.size(); j++) {

                        Baggage item = (Baggage) baggs.get(j);
                        if (!bag.equals(item)) {
                            if (bag.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(bag,
                                BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    bag.move(0, SPACE);
                    isCompleted();
                }
            }
        }

        return false;
    }

    public void isCompleted() {

        int num = baggs.size();
        int compl = 0;

        for (int i = 0; i < num; i++) {
            Baggage bag = (Baggage) baggs.get(i);
            for (int j = 0; j < num; j++) {
                //Area area = (Area) door.get(j);
                //if (bag.x() == area.x() && bag.y() == area.y()) {
                //    compl += 1;
                //}
            }
        }

        if (compl == num) {
            completed = true;
            repaint();
        }
    }

    public void restartLevel()
    {
        door.clear();        
        walls.clear();
        //baggs.clear();
        initWorld();
        if (completed)
        {
            completed = false;
        }
    }

	
}

