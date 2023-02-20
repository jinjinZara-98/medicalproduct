package capstonedesign.medicalproduct.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

