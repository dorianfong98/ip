package duke.tasks;

import java.time.LocalDate;

// @@author dorianfong98-reused
// Reused from https://github.com/dojh111/ip/blob/master/src/main/java/walter/tasks/Event.java with minor modifications
/**
 * The duke.tasks.Event class inherits from the duke.tasks.Task class and is used to create event objects
 */
public class Event extends Task {

    public static final String EVENT_ICON = "[E]";
    public static final String EVENT_INFO_START = " (At: ";
    public static final String EVENT_INFO_END = ")";

    protected String at;
    protected LocalDate date;

    public Event(String description, String at, String date) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(date);
    }

    public String getTaskIcon() {
        return EVENT_ICON;
    }

    public String getTime() {
        return at;
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + EVENT_INFO_START + at + EVENT_INFO_END;
    }
}
//@@author
