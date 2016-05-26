import java.util.Iterator;
import java.util.LinkedList;
/**
 * Creates a graph from the empty paths
 */
public class MazeGraph {
	
	public void addNode(int nodeX, int nodeY)
	{
		if(!containsNode(nodeX,nodeY))
		{
			Node newPoint = new Node(nodeX, nodeY);
			nodes.add(newPoint);
		}	
	}
	private boolean containsNode(int nodeX, int nodeY)
	{
		Iterator<Node> n = nodes.iterator();
		while(n.hasNext())
		{
			Node currentNode = n.next();
			if( (currentNode.getNodeX() == nodeX) && (currentNode.getNodeY() == nodeY))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addEdge(Node nodeFrom, Node toNode)
	{
		Node nFrom = findNode(nodeFrom);
		Node nTo = findNode(toNode);
		
		if(nFrom != null && nTo != null){
			Edge newEdge = new Edge(nFrom, nTo);
			if(!nFrom.getEdgesFromList().contains(newEdge)){
				nFrom.getEdgesFromList().add(newEdge);
			}
		}		
	}
	
	public Node findNode(Node node)
	{
		Node nodeFound = null;
		
		Iterator<Node> n = nodes.iterator();
		while(n.hasNext() && nodeFound == null)
		{
			Node n2 = n.next();
			if(n2.getNodeX()==node.getNodeX() && n2.getNodeY()==node.getNodeY()){
				
				nodeFound = n2;
			}
		}	
		return nodeFound;
	}
	
	public LinkedList<Node> getAllNodes()
	{
		LinkedList<Node> allNodes = new LinkedList<Node>();
		for(Node n: nodes){
			allNodes.add(n);
		}
		return allNodes;		
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (Node node : nodes) {
			str.append(node.toString());
		}
		return str.toString();
	}
	
	public MazeGraph()
	{
		nodes = new LinkedList<Node>();
	}
	
	private LinkedList<Node> nodes;
	
}
