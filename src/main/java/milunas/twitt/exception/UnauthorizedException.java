package milunas.twitt.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Access denied");
    }
}
