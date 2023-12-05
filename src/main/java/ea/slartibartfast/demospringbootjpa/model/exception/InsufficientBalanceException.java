package ea.slartibartfast.demospringbootjpa.model.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super();
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
