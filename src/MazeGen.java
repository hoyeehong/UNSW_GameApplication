
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Annie
 * Works sometimes. Still in progress 
 * Seperate program. Will integrate when it works 100% of the time
 */

public class MazeGen {

	public MazeGen(int width, int height) {
		this.width = width;
		this.height = height;

	}

	public static void main(String[] args) {


		MazeGen maze = new MazeGen(40,40);
		maze.generateMaze();
	}

	public void generateMaze() {

		char tiles[][] = new char[width][height];
		int col, row;
	    int tilesVisited = (width * 9) - 1;
		Point p, nextP = null;
		Random rand = new Random();

		// initialise 
		for (row = 0; row < width; row ++) {
			for (col = 0; col < height; col ++) {
				if (row == 0 || row == width - 1) {
					tiles[row][col] = WALL;
				} else if (col == 0 || col == height - 1) {
					tiles[row][col] = WALL;
				} else {
					tiles[row][col] = WALL;
				}
			}
		}

		Point start = setPoint();
		tiles[start.x][start.y] = START;

		Point door = setPoint();
		while ((door.x == start.x && door.y == start.y) || (door.distance(start) == TOO_CLOSE)){
			door = setPoint();
			// System.out.println("Tut tut");
		}
		System.out.println(door.distance(start));
		tiles[door.x][door.y] = DOOR;


		ArrayList<Point> visited = new ArrayList<Point>();	
		visited.add(start);

		// while (!visited.isEmpty()) 
		while (tilesVisited > 0 ){

			// can change order
			p = visited.remove(visited.size()-1);

			if (!p.equals(door)) {

				ArrayList<Point> neighbours = new ArrayList<Point>();
				ArrayList<Point> validNext = new ArrayList<Point>();

				neighbours.add(new Point(p.x + 2, p.y));
				neighbours.add(new Point(p.x - 2, p.y));
				neighbours.add(new Point(p.x, p.y + 2));
				neighbours.add(new Point(p.x, p.y - 2));

				// check if neighbours are valid 
				for (Point point: neighbours) {

					if (point.x < 1 || point.y < 1 ||
							point.x >= (width - 1) || point.y >= (height - 1)) {

					} else if (tiles[point.x][point.y] == WALL) {
						validNext.add(point);
						
					}
				}	
			
				if (!validNext.isEmpty()) {
					int size = validNext.size();
					nextP = validNext.get(rand.nextInt(size));
					visited.add(p);
					visited.add(nextP);
					tiles[(nextP.x + p.x)/2][(nextP.y + p.y)/2] = PASSAGE;
				//	System.out.print("x: " + (nextP.x + p.x));
				//	System.out.println(" y: " + (nextP.y + p.y));
				}
				
				// System.out.println("Tiles left:" + tilesVisited);
			} 
			

			if (tiles[p.x][p.y] == WALL) {
				tilesVisited --;
				tiles[p.x][p.y] = PASSAGE;			
				// System.out.println("Tiles:" + tilesVisited);
			}
		}


		// debugging purposes

	//	System.out.println("Tiles:" + tilesVisited);
		System.out.println("Start is at: " + start.x + ", " + start.y);
		System.out.println("Door is at: " + door.x + ", " + door.y);
		// print maze in symbol format 
		for (int rows = 0; rows < width; rows ++) {
			for (int cols = 0; cols < height; cols ++) {

				System.out.print(tiles[rows][cols]);
			}
			System.out.println();
		}
		System.out.println("Door tile: " + tiles[door.x][door.y]);
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

	private final static int WALL = '#'; 
	private final static int PASSAGE = ' ';
	private final static int START = '@';
	private final static int DOOR = '.';
	private final static int TOO_CLOSE = 1;

	private final int width; 
	private final int height;

}
