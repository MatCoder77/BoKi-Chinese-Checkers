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
	ArrayList<Point> visitedInThisTurn;
	MoveRequest lastMove;

	Point getGoal() {
		int corner = game.getPlayer(playerID).getCorner();
		Point goal = null;
		goal = null;
		
		if (corner == 4) {
			goal = new Point(0, 4);
			/*
			if(game.getField(0, 4).isEmpty())
				goal = new Point(0, 4);
			else if(game.getField(1, 4).isEmpty())
				goal = new Point(1, 4);
			else if(game.getField(1, 5).isEmpty())
				goal = new Point(1, 5);
			else if(game.getField(2, 4).isEmpty())
				goal = new Point(2, 4);
			else if(game.getField(2, 5).isEmpty())
				goal = new Point(2, 5);
			else if(game.getField(2, 6).isEmpty())
				goal = new Point(2, 6);
			else if(game.getField(3, 4).isEmpty())
				goal = new Point(3, 4);
			else if(game.getField(3, 5).isEmpty())
				goal = new Point(3, 5);
			else if(game.getField(3, 6).isEmpty())
				goal = new Point(3, 6);
			else if(game.getField(3, 7).isEmpty())
				goal = new Point(3, 7);*/
		}
		if (corner == 5) {
			goal = new Point(4, 12);
			/*
			if(game.getField(4, 12).isEmpty())
				goal = new Point(4, 12);
			else if(game.getField(4, 11).isEmpty())
				goal = new Point(4, 11);
			else if(game.getField(5, 12).isEmpty())
				goal = new Point(5, 12);
			else if(game.getField(4, 10).isEmpty())
				goal = new Point(4, 10);
			else if(game.getField(5, 11).isEmpty())
				goal = new Point(5, 11);
			else if(game.getField(6, 12).isEmpty())
				goal = new Point(6, 12);
			else if(game.getField(4, 9).isEmpty())
				goal = new Point(4, 9);
			else if(game.getField(5, 10).isEmpty())
				goal = new Point(5, 10);
			else if(game.getField(6, 11).isEmpty())
				goal = new Point(6, 11);
			else if(game.getField(7, 12).isEmpty())
				goal = new Point(7, 12);*/
		}
		if (corner == 6) {
			goal = new Point(12, 16);/*
			if(game.getField(12, 16).isEmpty())
				goal = new Point(12, 16);
			else if(game.getField(11, 15).isEmpty())
				goal = new Point(11, 15);
			else if(game.getField(12, 15).isEmpty())
				goal = new Point(12, 15);
			else if(game.getField(10, 14).isEmpty())
				goal = new Point(10, 14);
			else if(game.getField(11, 14).isEmpty())
				goal = new Point(11, 14);
			else if(game.getField(12, 14).isEmpty())
				goal = new Point(12, 14);
			else if(game.getField(9, 13).isEmpty())
				goal = new Point(9, 13);
			else if(game.getField(10, 13).isEmpty())
				goal = new Point(10, 13);
			else if(game.getField(11, 13).isEmpty())
				goal = new Point(11, 13);
			else if(game.getField(12, 13).isEmpty())
				goal = new Point(12, 13);*/
		}
		if (corner == 1) {
			goal = new Point(16, 12);/*
			if(game.getField(16, 12).isEmpty())
				goal = new Point(16, 12);
			else if(game.getField(15, 12).isEmpty())
				goal = new Point(15, 12);
			else if(game.getField(15, 11).isEmpty())
				goal = new Point(15, 11);
			else if(game.getField(14, 12).isEmpty())
				goal = new Point(14, 12);
			else if(game.getField(14, 11).isEmpty())
				goal = new Point(14, 11);
			else if(game.getField(14, 10).isEmpty())
				goal = new Point(14, 10);
			else if(game.getField(13, 12).isEmpty())
				goal = new Point(13, 12);
			else if(game.getField(13, 11).isEmpty())
				goal = new Point(13, 11);
			else if(game.getField(13, 10).isEmpty())
				goal = new Point(13, 10);
			else if(game.getField(13, 9).isEmpty())
				goal = new Point(13, 9);*/
		}
		if (corner == 2) {
			goal = new Point(12, 4);/*
			if(game.getField(12, 4).isEmpty())
				goal = new Point(12, 4);
			else if(game.getField(12, 5).isEmpty())
				goal = new Point(12, 5);
			else if(game.getField(11, 4).isEmpty())
				goal = new Point(11, 4);
			else if(game.getField(10, 4).isEmpty())
				goal = new Point(10, 4);
			else if(game.getField(11, 5).isEmpty())
				goal = new Point(11, 5);
			else if(game.getField(12, 6).isEmpty())
				goal = new Point(12, 6);
			else if(game.getField(12, 7).isEmpty())
				goal = new Point(12, 7);
			else if(game.getField(11, 6).isEmpty())
				goal = new Point(11, 6);
			else if(game.getField(10, 5).isEmpty())
				goal = new Point(10, 5);
			else if(game.getField(9, 4).isEmpty())
				goal = new Point(9, 4);*/
		}
		if (corner == 3) {
			goal = new Point(4, 0);/*
			if(game.getField(4, 0).isEmpty())
				goal = new Point(4, 0);
			else if(game.getField(4, 1).isEmpty())
				goal = new Point(4, 1);
			else if(game.getField(5, 1).isEmpty())
				goal = new Point(5, 1);
			else if(game.getField(6, 2).isEmpty())
				goal = new Point(6, 2);
			else if(game.getField(5, 2).isEmpty())
				goal = new Point(5, 2);
			else if(game.getField(4, 2).isEmpty())
				goal = new Point(4, 2);
			else if(game.getField(4, 3).isEmpty())
				goal = new Point(4, 3);
			else if(game.getField(5, 3).isEmpty())
				goal = new Point(5, 3);
			else if(game.getField(6, 3).isEmpty())
				goal = new Point(6, 3);
			else if(game.getField(7, 3).isEmpty())
				goal = new Point(7, 3);*/
		}
		return goal;
	}

	@Override
	public MoveRequest getBestMove() {
		visitedInThisTurn = new ArrayList<>();
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
			from = p.getLocation();
			possibleMoves = game.checkValidMoves(playerID, from);
			possibleMoves = processPossibleMoves(p, possibleMoves);
		} while (p.getLocation().equals(getGoal()) || possibleMoves.isEmpty());
		// for(Pawn p : me.getPawns()) {
		// Pawn savedState = new Pawn(p);		
		for (Point to : possibleMoves) {
			newDistance = evaluate(to);
			if (minDistace > newDistance) {
				minDistace = newDistance;
				moveRequest = new MoveRequest(from, to);
			} else if (minDistace == newDistance) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
				if (randomNum == 1) {
					moveRequest = new MoveRequest(from, to);
				}
			}
		}
		// }
		lastMove = moveRequest;
		firstMoveInTurn = moveRequest;
		if (moveRequest != null) {
			visitedInThisTurn.add(moveRequest.getOldPos());
			visitedInThisTurn.add(moveRequest.getNewPos());
		}
		return moveRequest;
	}

	public MoveRequest getBestMove(ArrayList<Point> possibleMoves) {
		MoveRequest bestMove = null;
		int newDistance;
		int minDistance = Integer.MAX_VALUE;
		// ArrayList<Point> possibleMoves = game.checkValidMoves(playerID, from);
		possibleMoves.removeAll(visitedInThisTurn);
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
		if (bestMove != null) {
			visitedInThisTurn.add(bestMove.getOldPos());
			visitedInThisTurn.add(bestMove.getNewPos());
		}
		lastMove = bestMove;
		return bestMove;
	}

	int evaluate(Point pos) {
		// return Math.max(Math.abs(goal.x - pos.x), Math.abs(goal.y - pos.y));
		return (int) Math.hypot(Math.abs(getGoal().x - pos.x), Math.abs(getGoal().y - pos.y));

	}
	
	ArrayList<Point> processPossibleMoves(Pawn p, ArrayList<Point> possible) {
		
		if(p.isInTarget()) {
			int currentDistance = evaluate(p.getLocation());
			int distanceAfterMove = Integer.MAX_VALUE;
			ArrayList<Point> processedMoves = new ArrayList<>();
			for(Point move : possible) {
				distanceAfterMove = evaluate(move);
				if(distanceAfterMove < currentDistance) {
					processedMoves.add(move);
				}
			}
			return processedMoves;
		}
		else
			return possible;
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
