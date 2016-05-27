import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
/**
 * Generates a random maze when player enters the game each time
 * @author Annie
 */
public class MazeGen implements MazeGenerator {

	public String generateMaze() {

		char tiles[][] = new char[width][height];
		int col, row;
		int tilesVisited = ((width/2) + 1) * ((height/2) + 1) - 1;

		Point p, nextP = null;
		Random rand = new Random();

		// initialise 
		for (row = 0; row < width ; row ++) {
			for (col = 0; col < height ; col ++) {
				tiles[row][col] = WALL;
			}
		}

		Point start = new Point(0,0);	
		tiles[start.x][start.y] = START;	
		Point door = new Point (height - 1, width - 1);

		graph = new MazeGraph();
		graph.addNode(start.x, start.y);
		
		ArrayList<Point> visited = new ArrayList<Point>();	
		visited.add(start);

		while (tilesVisited > 0 ){

			p = visited.remove(visited.size()-1);

			if (p.x != door.x || p.y != door.y) {

				ArrayList<Point> neighbours = new ArrayList<Point>();
				ArrayList<Point> validNext = new ArrayList<Point>();

				neighbours.add(new Point(p.x + 2, p.y));
				neighbours.add(new Point(p.x - 2, p.y));
				neighbours.add(new Point(p.x, p.y + 2));
				neighbours.add(new Point(p.x, p.y - 2));

				// check if neighbors are valid 
				for (Point point: neighbours) {

					if (point.x < 0 || point.y < 0 || point.x >= width || point.y >= height)
					{
						//do nothing
					} 
					else if (tiles[point.x][point.y] == WALL)
					{
						validNext.add(point);					
						graph.addNode(point.x, point.y);
					}
				}	

				if (!validNext.isEmpty()) {
					int size = validNext.size();				
					nextP = validNext.get(rand.nextInt(size));					
					visited.add(p);
					visited.add(nextP);
					tiles[(nextP.x + p.x)/2][(nextP.y + p.y)/2] = PASSAGE;
					
					if(tiles[(nextP.x + p.x)/2][(nextP.y + p.y)/2] == PASSAGE){
						int currentX = (nextP.x + p.x)/2;
						int currentY = (nextP.y + p.y)/2;						
						graph.addNode(currentX, currentY);
					}
				}
			} 

			if (tiles[p.x][p.y] == WALL) {
				tilesVisited --;
				tiles[p.x][p.y] = PASSAGE;
				graph.addNode(p.x,p.y);	
			}
		}

		tiles[door.x][door.y] = DOOR;
		graph.addNode(door.x, door.y);
		createEdgesBetweenNodes();
			
		LinkedList<Node> path = generatePath(start, door);	
		for(Node n : path)
		{
			if(tiles[n.getNodeX()][n.getNodeY()] != START &&
				tiles[n.getNodeX()][n.getNodeY()] != DOOR)
			{
				tiles[n.getNodeX()][n.getNodeY()] = SEARCHED_PATH;				
			}
		}
		
		StringBuilder stringBuild = new StringBuilder();
		for (int i = 0; i < width + 2 ; i++) {
			stringBuild.append("#");
		}
		stringBuild.append("\n");
		
		for (int rows = 0; rows < width; rows ++) {	
			stringBuild.append("#");
			for (int cols = 0; cols < height; cols ++) {
				stringBuild.append(tiles[rows][cols]);
			}	
			stringBuild.append("#\n");
		}

		for (int i = 0; i < width + 2 ; i++) {
			stringBuild.append("#");
		}
		stringBuild.append("\n");

		String mazeString = stringBuild.toString();
		return mazeString;			
	}
	
	private LinkedList<Node> generatePath(Point start, Point door)
	{
		Node startNode = new Node(start.x, start.y);
		Node doorNode = new Node(door.x, door.y);	
		BFS bfs = new BFS(graph);
		LinkedList<Node> path = bfs.findPath(startNode, doorNode);
		return path;
	}
		
	private void createEdgesBetweenNodes()
	{
		LinkedList<Node> allAddedNodes = graph.getAllNodes();		
		LinkedList<Node> nodesToVisit = new LinkedList<Node>();
		
		for(Node p : allAddedNodes)
		{
			nodesToVisit.add(p);
		}			
		
		if(nodesToVisit.size()>0)
		{	
			Node currentP = null;
			while(!nodesToVisit.isEmpty())
			{
				currentP = nodesToVisit.remove();//(0,0)
				
				LinkedList<Node> neighboursToAdd = new LinkedList<Node>();			
				neighboursToAdd.add(new Node(currentP.getNodeX() + 1, currentP.getNodeY()));
				neighboursToAdd.add(new Node(currentP.getNodeX() - 1, currentP.getNodeY()));
				neighboursToAdd.add(new Node(currentP.getNodeX(), currentP.getNodeY() + 1));
				neighboursToAdd.add(new Node(currentP.getNodeX(), currentP.getNodeY() - 1));
				
				for(Node n : neighboursToAdd)
				{
					for(Node m : allAddedNodes)
					{
						if( n.getNodeX()==m.getNodeX() && n.getNodeY()==m.getNodeY() )
						{
							graph.addEdge(currentP, n);
						}
					}					
				}
			}				
		}
	}	

	private Point setPoint() {

		int x, y;
		Random rand = new Random();

		x = rand.nextInt(width - 1);
		y = rand.nextInt (height - 1);

		// cannot be on edges
		while (x == 0 || y == 0) {
			if (x == 0) {
				x = rand.nextInt(width - 1);
			} else {
				y = rand.nextInt(height - 1);
			}
		}
		Point tile = new Point (x, y);
		return tile;
	}
	
	public MazeGen(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	private static MazeGraph graph;
	
	private final static char WALL = '#'; 
	private final static char PASSAGE = ' ';
	private final static char START = '@';
	private final static char DOOR = '.';
	private final static char SEARCHED_PATH = 'x';
	
	private final int width; 
	private final int height;

}
