package communication;

import java.awt.Point;
import java.util.ArrayList;

public class PossibleMovesResponse extends Response{

	private static final long serialVersionUID = -6506437499474773590L;
	private ArrayList<Point> possibleMoves;
	
	public PossibleMovesResponse(ArrayList<Point> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public ArrayList<Point> getPossibleMoves() {
		return possibleMoves;
	}
}
