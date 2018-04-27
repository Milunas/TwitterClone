package milunas.twitt.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("There is no account with that username: " + username);
    }
}
