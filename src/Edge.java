/**
 * Edges between nodes
 */
public class Edge {

	public Node getNodeFrom()
	{
		return nodeFrom;
	}
	public Node getToNode()
	{
		return toNode;
	}
	
	@Override
	public String toString()
	{
		return nodeFrom.getCoordinates()+"->"+toNode.getCoordinates();
	}
	public Edge(Node nodeFrom, Node toNode)
	{
		this.nodeFrom = nodeFrom;
		this.toNode = toNode;
	}
	
	private Node nodeFrom;
	private Node toNode;
}
