package Domain;

public class MovieValidatorException extends RuntimeException {
    public MovieValidatorException(String message) {
        super ("MovieValidatorException |||" + message);
    }
}
