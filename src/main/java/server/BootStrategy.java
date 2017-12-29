package server;

import java.awt.Point;

public interface BootStrategy {
	Point getStartPos();
	Point makeMove();
}
