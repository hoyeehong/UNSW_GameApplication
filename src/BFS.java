import java.util.LinkedList;
/**
 * Breadth-First Searching
 *
 */
public class BFS {

	public LinkedList<Node> findPath(Node nodeStart, Node nodeFinish)
	{
		LinkedList<Node> nodesVisited = new LinkedList<Node>();
		LinkedList<Node> nodesToVisit = new LinkedList<Node>();
		LinkedList<Node> parent = new LinkedList<Node>();
		LinkedList<Node> parentVisited = new LinkedList<Node>();

		if(allNodes.size()>0)
		{
			Node rootNode = allNodes.get(0);
			nodesToVisit.add(rootNode);
			parent.add(rootNode);
				
			Node current = rootNode;
			Node parentTemp = current;
			while(!nodesToVisit.isEmpty() && !nodesVisited.contains(graph.findNode(nodeFinish)))
			{
				current = nodesToVisit.remove();
				nodesVisited.add(current);
						
				parentTemp = parent.remove();
				parentVisited.add(parentTemp);
	
				LinkedList<Edge> toAdd = current.getEdgesFromList();
	
				for (Edge e : toAdd) 
				{
					if (!nodesVisited.contains(e.getToNode()) && !nodesToVisit.contains(e.getToNode()))
					{
						nodesToVisit.addLast(e.getToNode());
						parent.addLast(current);
					}
				}
				if (nodesVisited.contains(graph.findNode(nodeFinish)))
				{				
					LinkedList<Node> path = new LinkedList<Node>();						
					current	= graph.findNode(nodeFinish);
					path.addFirst(current);
					int index = nodesVisited.indexOf(current);
					Node currentParent = parentVisited.get(index);
					while (current != currentParent)
					{
						current = currentParent;
						index = nodesVisited.indexOf(current);
						currentParent = parentVisited.get(index);
						path.addFirst(current);
					}					
					//printNodes("FINAL: ", path);
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
