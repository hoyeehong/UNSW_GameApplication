/**
 * @author Yeehong Ho
 * @date 30/5/2016
 * Edges between nodes
 */
public class Edge {

	public Node getPointFrom()
	{
		return pointFrom;
	}
	public Node getToPoint()
	{
		return toPoint;
	}
	
	@Override
	public String toString()
	{
		return pointFrom.getCoordinates()+"->"+toPoint.getCoordinates();
	}
	public Edge(Node nodeFrom, Node toNode)
	{
		this.pointFrom = nodeFrom;
		this.toPoint = toNode;
	}
	
	private Node pointFrom;
	private Node toPoint;
}
