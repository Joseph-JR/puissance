package exceptions;

public class BadArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadArgumentException(String msg){
		super(msg);
	}
}
