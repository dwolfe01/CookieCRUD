package wolfesoftware.controller.exception;

public class CookieControllerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CookieControllerException() {
	}

	public CookieControllerException(Exception e) {
		super(e);
	}

	public CookieControllerException(String string) {
		super(string);
	}

}
