package communication;

import java.io.Serializable;

/**
 * Represents response from server delivered to client via socket's ObjectStream. As a subclass of Command inherits accept function, which due
 * to CommandHandler implementation doesn't have to be reimplemented. Class contains basic information about response's sender. 
 * @author Mateusz
 *
 */
public abstract class Response extends Command{
	private static final long serialVersionUID = -4189602426569148812L;

}
