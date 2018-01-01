package board;

import java.awt.Point;

/**
 * @author filipbk
 * Class stores information about Pawn's location
 * and target corner
 *
 */
public class Pawn {
	
	private Point location;
	private Point lastLocation;
	private String target;
	private boolean isInTarget;
	private boolean isInMove;
	private boolean isAfterStep;
	
	public Pawn(Point coords, String target) {
		this.location = coords;
		this.lastLocation = coords;
		this.target = target;
		this.isInTarget = false;
		this.isInMove = false;
		this.isAfterStep = false;
	}
	
	public Pawn(Pawn p) {
		this.location = new Point(p.location);
		this.lastLocation = new Point(p.lastLocation);
		this.target = new String(p.target);
		this.isInTarget = p.isInTarget;
	}
	
	public boolean isInTarget() {
		return isInTarget;
	}
	
	public void setInTarget() {
		isInTarget = true;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public Point getLastLocation() {
		return lastLocation;
	}
	
	public void setLastLocation(Point lastLocation) {
		this.lastLocation = lastLocation;
	}
	
	public void setInMove() {
		isInMove = true;
	}
	
	public void setNotInMove() {
		isInMove = false;
	}
	
	public boolean isInMove() {
		return isInMove;
	}
	
	public void setAfterStep() {
		isAfterStep = true;
	}
	
	public void setNotAfterStep() {
		isAfterStep = false;
	}
	
	public boolean isAfterStep() {
		return isAfterStep;
	}

}
