package duke.tasks;

/**
 * The duke.tasks.Deadline class inherits from the duke.tasks.Task class and is used to create deadline objects
 */
public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    public static final String MESSAGE_INFO_START = " (By: ";
    public static final String MESSAGE_INFO_END = ")";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskIcon() {
        return DEADLINE_ICON;
    }

    public String getTimingInformation() {
        return by;
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + MESSAGE_INFO_START + by + MESSAGE_INFO_END;
    }
}
