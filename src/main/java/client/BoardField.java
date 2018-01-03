package client;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BoardField extends Group {
	
	private Circle circle;
	private Point location;
	private String fieldType;
	private boolean isActive;
	private boolean isSelected;
	private boolean isPossible;
	private Circle pointer;
	
	public BoardField(double radius) {
		circle = new Circle(radius);
		this.getChildren().add(circle);
	}
	
	public BoardField(double radius, Point location, String fieldType) {
		circle = new Circle(radius);
		pointer = new Circle(5, Color.BLACK);
		this.getChildren().add(circle);
		this.location = location;
		this.fieldType = fieldType;
		this.isActive = false;
		this.isSelected = false;
		this.isPossible = false;
	}
	
	public void setFill(Paint value) {
		circle.setFill(value);
	}
	
	public Paint getFill() {
		return circle.getFill();
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void activate() {
		isActive = true;
	}
	
	public void deactivate() {
		isActive = false;
		setImpossible();
		deselect();
	}
	
	public void setPossible() {
		isPossible = true;
		this.getChildren().add(pointer);
	}
	
	public void setImpossible() {
		isPossible = false;
		this.getChildren().remove(pointer);
	}
	
	public boolean isPossible() {
		return isPossible;
	}
	
	public void select() {
		isSelected = true;
	}
	
	public void deselect() {
		isSelected = false;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

}
