package cloud.jordaan.juan.casino.error;

public class ClientApplicationException extends RuntimeException {
    public ClientApplicationException(String message) {
        super(message);
    }
}