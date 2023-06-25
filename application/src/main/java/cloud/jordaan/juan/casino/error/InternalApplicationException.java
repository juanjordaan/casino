package cloud.jordaan.juan.casino.error;

public class InternalApplicationException extends RuntimeException {
    public InternalApplicationException(String message) {
        super(message);
    }
}