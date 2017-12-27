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
	
	public Pawn(Point coords, String target) {
		this.location = coords;
		this.lastLocation = coords;
		this.target = target;
		this.isInTarget = false;
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

}
