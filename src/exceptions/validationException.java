package exceptions;

import java.io.IOException;

public class validationException extends RuntimeException {
    public validationException(String message) {
        super(message);
    }
}

