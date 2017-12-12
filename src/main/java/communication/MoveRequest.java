package communication;

import java.awt.Point;

public class MoveRequest implements Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1966688589128685396L;
	
	public MoveRequest(Point from, Point to) {
		currentPos = from;
		destination = to;
	}
	
	Point currentPos;
	Point destination;
	
	Point getDestination()
	{
		return destination;
	}
	
	Point getCurrentPosition()
	{
		return currentPos;
	}
}
