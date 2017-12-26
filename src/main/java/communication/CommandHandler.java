package communication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Base abstract class for all CommandHandlers. Performs matching handler
 * function to particular Command type by emulating double dispatching
 * mechanism. For convenience of use CommandHandler takes advantage of
 * reflection. This enables creating new Command types by extending Command
 * class or one of its subclasses. Command handling is performed by calling
 * accept method on Command object along with particular CommandHandler
 * subclass. When you want to create new CommandHandler you have to extend this
 * class and provide overloaded methods for Commands you want to handle. In case
 * no match is made, CommandHandler will call defaultHandle method, which can be
 * reimplemented by user. Important constraint when creating own CommandHandler
 * is to know handler's method naming convention. All method HAVE TO BE NAMED
 * "handle" and should take one argument of Command type or its subclasses.
 * 
 * @author Mateusz
 *
 */
public abstract class CommandHandler {
	/**
	 * Returns handler method appropriate for Command type
	 * 
	 * @param command
	 * @return handler method for command
	 */
	private Method getMethod(Command command) {
		@SuppressWarnings("rawtypes")
		Class cl = command.getClass();
		// checking super-classes for matching method
		while (!cl.equals(Object.class)) {
			try {
				return this.getClass().getDeclaredMethod("handle", new Class[] { cl });
			} catch (NoSuchMethodException ex) {
				cl = cl.getSuperclass();
			}
		}
		// checking interfaces for matching method
		@SuppressWarnings("rawtypes")
		Class[] interfaces = command.getClass().getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			try {
				return this.getClass().getDeclaredMethod("handle", new Class[] { interfaces[i] });
			} catch (NoSuchMethodException ex) {
			}
		}
		return null;
	}

	/**
	 * Calls proper handler method for particular command
	 * 
	 * @param command
	 */
	public void handle(Command command) {
		Method handlerMethod = getMethod(command);
		if (handlerMethod == null) {
			defaultHandle(command);
		} else {
			try {
				handlerMethod.invoke(this, new Object[] { command });
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Default command handler method. It is called when no match is performed.
	 * 
	 * @param command
	 */
	public abstract void defaultHandle(Command command);
}