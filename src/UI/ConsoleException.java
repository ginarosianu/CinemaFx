package UI;

public class ConsoleException extends RuntimeException {
    public ConsoleException(String message){
        super("ConsoleException ||| "+message);
    }
}
