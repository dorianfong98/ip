package duke.tasks;

/**
 * The Deadline class inherits from the Task class.
 * E.g. of input: deadline return book /by Sunday
 * E.g. of output: [D][✗] return book (by: Sunday)
 */

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}