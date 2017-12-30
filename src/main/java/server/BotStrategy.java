package server;

import java.awt.Point;

public interface BotStrategy {
	Point getStartPos();
	Point makeMove();
}
