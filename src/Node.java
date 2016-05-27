import java.util.LinkedList;
/**
 * Contain information on a node
 */
public class Node {
	
	public int getNodeX()
	{
		return this.nodeX;
	}
	public int getNodeY()
	{
		return this.nodeY;
	}
	
	public String getCoordinates()
	{
		return this.nodeX+","+this.nodeY;
	}
	
	public LinkedList<Edge> getEdgesFromList()
	{
		return listOfEdges;
	}
	
	@Override
	public String toString()
	{
		StringBuffer str = new StringBuffer();
		str.append(getNodeX()+","+getNodeY()+":\n");
		for(Edge e: listOfEdges){
			str.append("\t-> "+e.getToPoint().getNodeX()+","+e.getToPoint().getNodeY()+"\n");
		}
		return str.toString();
	}

	public Node(int nodeX, int nodeY)
	{
		this.nodeX = nodeX;
		this.nodeY = nodeY;
		listOfEdges = new LinkedList<Edge>();
	}
	
	private int nodeX;
	private int nodeY;
	private LinkedList<Edge> listOfEdges;
}
