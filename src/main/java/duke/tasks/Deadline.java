package duke.tasks;

import java.time.LocalDate;

// @@author dorianfong98-reused
// Reused from https://github.com/dojh111/ip/blob/master/src/main/java/walter/tasks/Deadline.java with minor modifications
/**
 * The duke.tasks.Deadline class inherits from the duke.tasks.Task class and is used to create deadline objects
 */
public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    public static final String MESSAGE_INFO_START = " (By: ";
    public static final String MESSAGE_INFO_END = ")";

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by, String date) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(date);
    }

    public String getTaskIcon() {
        return DEADLINE_ICON;
    }

    public String getTime() {
        return by;
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + MESSAGE_INFO_START + by + MESSAGE_INFO_END;
    }
}
//@@author
