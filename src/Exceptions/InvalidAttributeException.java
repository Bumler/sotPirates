package Exceptions;

/**
 * A wrapper exception class intended to help identify the reason for the
 * exception
 * 
 * @author ryanpelaez
 *
 */
public class InvalidAttributeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAttributeException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
