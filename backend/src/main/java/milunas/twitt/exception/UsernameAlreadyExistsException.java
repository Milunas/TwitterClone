package milunas.twitt.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("There is an account with that username: " + username);
    }
}
