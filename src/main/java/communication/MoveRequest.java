package communication;

import java.awt.Point;

public class MoveRequest extends GameplayRequest{

	private static final long serialVersionUID = -1966688589128685396L;
	private Point oldPos;
	private Point newPos;
	
	public MoveRequest(Point from, Point to) {
		oldPos = from;
		newPos = to;
	}
	
	public Point getNewPos()
	{
		return newPos;
	}
	
	public Point getOldPos()
	{
		return oldPos;
	}
	
}
