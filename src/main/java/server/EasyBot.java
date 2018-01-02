package server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import board.Pawn;
import communication.MoveRequest;

public class EasyBot extends BotStrategy {
	MoveRequest firstMoveInTurn;
	MoveRequest lastMove;

	Point getGoal() {
		int corner = game.getPlayer(playerID).getCorner();
		Point goal = null;
		goal = null;
		if (corner == 4) {
			goal = new Point(0, 4);
		}
		if (corner == 5) {
			goal = new Point(4, 12);
		}
		if (corner == 6) {
			goal = new Point(12, 16);
		}
		if (corner == 1) {
			goal = new Point(16, 12);
		}
		if (corner == 2) {
			goal = new Point(12, 4);
		}
		if (corner == 3) {
			goal = new Point(4, 0);
		}
		return goal;
	}

	@Override
	public MoveRequest getBestMove() {
		MoveRequest moveRequest = null;
		ArrayList<Point> possibleMoves = new ArrayList<>();
		Point from;
		int minDistace = Integer.MAX_VALUE;
		int newDistance;
		int randomPawnIndex;
		Pawn p;
		do {
			randomPawnIndex = ThreadLocalRandom.current().nextInt(0, 10);
			p = me.getPawns().get(randomPawnIndex);
		} while (p.getLocation().equals(getGoal()));
		// for(Pawn p : me.getPawns()) {
			//Pawn savedState = new Pawn(p);
			from =  p.getLocation();
			possibleMoves = game.checkValidMoves(playerID, from);
			for(Point to : possibleMoves) {
				newDistance = evaluate(to);
				if(minDistace > newDistance) {
					minDistace = newDistance;
					moveRequest = new MoveRequest(from, to);
				}
				else if(minDistace == newDistance) {
					int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
					if(randomNum == 1) {
						moveRequest = new MoveRequest(from, to);
					}
				}
			}
		//}
		lastMove = moveRequest;
		firstMoveInTurn = moveRequest;
		return moveRequest;
	}

	public MoveRequest getBestMove(ArrayList<Point> possibleMoves) {
		MoveRequest bestMove = null;
		int newDistance;
		int minDistance = Integer.MAX_VALUE;
		// ArrayList<Point> possibleMoves = game.checkValidMoves(playerID, from);
		for (Point to : possibleMoves) {
			newDistance = evaluate(to);
			if (minDistance > newDistance) {
				minDistance = newDistance;
				bestMove = new MoveRequest(lastMove.getNewPos(), to);
			} else if (minDistance == newDistance) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
				if (randomNum == 1) {
					bestMove = new MoveRequest(lastMove.getNewPos(), to);
				}
			}
		}
		if (bestMove != null && bestMove.getNewPos().equals(firstMoveInTurn.getOldPos()))
			bestMove = null;
		lastMove = bestMove;
		return bestMove;
	}

	int evaluate(Point pos) {
		// return Math.max(Math.abs(goal.x - pos.x), Math.abs(goal.y - pos.y));
		return (int) Math.hypot(Math.abs(getGoal().x - pos.x), Math.abs(getGoal().y - pos.y));

	}

	@Override
	public MoveRequest getBestMoveFromPoint(Point from) {
		// TODO Auto-generated method stub
		return null;
	}

	// int getMaxScored(Point from, ArrayList<Point> possibleMoves, Pawn pawn) {
	// for(Point to : possibleMoves) {
	// game.move(playerID, from, to);
	// getMaxScored(to, game.checkValidMoves(playerID, to));
	// game.
	// }
	// int score = evaluate(from);
	// return score;
	// }

}
