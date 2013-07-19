package wolfesoftware.cryptography.exception;

public class MakeItSecretException extends Exception {

	public MakeItSecretException() {
	}

	public MakeItSecretException(Exception e) {
		super(e);
	}

	public MakeItSecretException(String string) {
		super(string);
	}

}
