package communication;

import java.awt.Point;

public class PossibleMovesRequest extends Request{

	private static final long serialVersionUID = 3053151431355666421L;
	Point pawnPos;
	
	public PossibleMovesRequest(Point pawnPos) {
		this.pawnPos = pawnPos;
	}
	
	public Point getPawnPos() {
		return pawnPos;
	}
}
