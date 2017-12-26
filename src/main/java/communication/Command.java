package communication;

import java.io.Serializable;

/**
 * Base abstract class for representing Command passed between server and client
 * application. Objects of this class are sent via socket's ObjectStreams to
 * perform communication. This class is extended by Request (representing
 * client's requests to server) and Respond (representing server's responds to
 * client). To handle a Command all you have to do is to call accept method with
 * concrete CommandHandler. If you want to provide new type of command subclass
 * this class or one of its subclasses (Request, Response).
 * 
 * @author Mateusz
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = -5752711610098668171L;

	/**
	 * Method takes particular CommandHandler as an argument to handle Command. Each
	 * subclass of CommandHandler defines specific tasks that will be executed when
	 * Command of particular type is received.
	 * 
	 * @param handler
	 *            particular handler
	 */
	public void accept(CommandHandler handler) {
		handler.handle(this);
	}
}
