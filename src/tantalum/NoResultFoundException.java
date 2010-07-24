package tantalum;

public class NoResultFoundException extends java.lang.Exception {
	private static final long serialVersionUID = 3159767979372655331L;

	public NoResultFoundException() {
		super();
	}

	public NoResultFoundException(String message) {
		super(message);
	}

	public NoResultFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
