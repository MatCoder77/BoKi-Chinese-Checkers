package communication;

import java.io.Serializable;

import server.ClientHandler;

/**
 * Represents client's request to server sent via socket's ObjectStream. As a subclass of Command inherits accept function, which due
 * to CommandHandler implementation doesn't have to be reimplemented. Class contains basic information about request's sender.
 * @author Mateusz
 *
 */
public abstract class Request extends Command{

	private static final long serialVersionUID = 4158418538134531213L;
//	String clientName;
//	
//	String getClientName() {
//		return clientName;
//	}
}
