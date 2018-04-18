import java.util.LinkedList;
/**
 * @author Yeehong Ho
 * @date 30/5/2016
 * Breadth-First Search
 */
public class BFS {

	public LinkedList<Node> findPath(Node startPoint, Node door)
	{
		LinkedList<Node> nodesVisitedList = new LinkedList<Node>();
		LinkedList<Node> nodesToVisitList = new LinkedList<Node>();
		LinkedList<Node> parentList = new LinkedList<Node>();
		LinkedList<Node> parentVisitedList = new LinkedList<Node>();

		if(allNodes.size()>0)
		{
			Node rootNode = allNodes.get(0);
			nodesToVisitList.add(rootNode);
			parentList.add(rootNode);
				
			Node currentPoint = rootNode;
			Node parentTemp = currentPoint;
			while(!nodesToVisitList.isEmpty() && !nodesVisitedList.contains(graph.findNode(door)))
			{
				currentPoint = nodesToVisitList.remove();
				nodesVisitedList.add(currentPoint);
						
				parentTemp = parentList.remove();
				parentVisitedList.add(parentTemp);
	
				LinkedList<Edge> toAdd = currentPoint.getEdgesFromList();
	
				for (Edge e : toAdd) 
				{
					if (!nodesVisitedList.contains(e.getToPoint()) && !nodesToVisitList.contains(e.getToPoint()))
					{
						nodesToVisitList.addLast(e.getToPoint());
						parentList.addLast(currentPoint);
					}
				}
				if (nodesVisitedList.contains(graph.findNode(door)))
				{				
					LinkedList<Node> path = new LinkedList<Node>();						
					currentPoint = graph.findNode(door);
					path.addFirst(currentPoint);
					int index = nodesVisitedList.indexOf(currentPoint);
					Node currentPointParent = parentVisitedList.get(index);
					while (currentPoint != currentPointParent)
					{
						currentPoint = currentPointParent;
						index = nodesVisitedList.indexOf(currentPoint);
						currentPointParent = parentVisitedList.get(index);
						path.addFirst(currentPoint);
					}					
					return path;
				}
			}
			return null;
		}	
		else
		{
			return null;
		}
	}
	
	public BFS(MazeGraph graph)
	{
		this.graph = graph;
		allNodes = graph.getAllNodes();
	}
	
	private MazeGraph graph;
	private static LinkedList<Node> allNodes;
	
}
