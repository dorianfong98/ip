/**
 * Exceptions specific to Duke
 */
public class DukeException extends Exception {

    protected String errorMessage;

    public DukeException (String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
