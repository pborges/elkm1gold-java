package exception;

/**
 * Created by pborges on 2/6/15.
 */
public class AlreadyStartedException extends Exception {
    public AlreadyStartedException() {
        super("You cannot call addListener after calling listen");
    }
}
