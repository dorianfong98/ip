package duke.exceptions;

/**
 * Exception class for exceptions specific to duke.duke
 */
public class DukeException extends Exception {

    protected String errorMessage;

    public DukeException (String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
