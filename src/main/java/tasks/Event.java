/**
 * The Event class inherits from the Task class.
 * E.g. of input: event project meeting /at Mon 2-4pm
 * E.g. of output:[E][✗] project meeting (at: Mon 2-4pm)
 */

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + at + ")";
    }
}