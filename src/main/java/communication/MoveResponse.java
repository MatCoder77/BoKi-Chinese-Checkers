package communication;

import java.awt.Point;
import java.util.ArrayList;

import server.ClientInfo;

public class MoveResponse extends Response{
	
	private static final long serialVersionUID = -7597237159154689784L;
	ClientInfo clientInfo;
	private Point oldPos;
	private Point newPos;
	
	public MoveResponse(ClientInfo clientInfo, Point from, Point to) {
		this.clientInfo = clientInfo;
		oldPos = from;
		newPos = to;
	}
	
	public Point getNewPos()
	{
		return newPos;
	}
	
	public Point getOldPos()
	{
		return oldPos;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
