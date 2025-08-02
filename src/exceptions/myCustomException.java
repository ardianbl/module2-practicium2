package exceptions;

public class myCustomException extends RuntimeException {
    public myCustomException(String message) {
        super(message);
    }
}
