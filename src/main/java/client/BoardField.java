package client;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardField extends Group {
	
	private Circle circle;
	private Point location;
	private String fieldType;
	private boolean isActive;
	private boolean isSelected;
	private boolean isPossible;
	Rectangle rect;
	
	public BoardField(double radius) {
		circle = new Circle(radius);
		this.getChildren().add(circle);
	}
	
	public BoardField(double radius, Point location, String fieldType) {
		circle = new Circle(radius);
		rect = new Rectangle(5, 5);
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
		//this.getChildren().add(rect);
	}
	
	public void deactivate() {
		isActive = false;
		setImpossible();
		deselect();
		//this.getChildren().remove(rect);
	}
	
	public void setPossible() {
		isPossible = true;
		this.getChildren().add(rect);
		//rect.setFill(Color.BLUE);
	}
	
	public void setImpossible() {
		isPossible = false;
		this.getChildren().remove(rect);
		//rect.setFill(Color.BLACK);
	}
	
	public boolean isPossible() {
		return isPossible;
	}
	
	public void select() {
		isSelected = true;
		//this.getChildren().add(rect);
	}
	
	public void deselect() {
		isSelected = false;
		//this.getChildren().remove(rect);
	}
	
	public boolean isSelected() {
		return isSelected;
	}

}
