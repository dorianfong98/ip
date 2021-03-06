package duke.tasks;

import java.time.LocalDate;

// @@author dorianfong98-reused
// Reused from https://github.com/dojh111/ip/blob/master/src/main/java/walter/tasks/Todo.java with minor modifications
/**
 * The duke.tasks.Todo class inherits from the duke.tasks.Task class and is used to create todo objects
 */
public class Todo extends Task {

    public static final String TODO_ICON = "[T]";
    public static final String TIME_INFO = "";
    public static final String DEFAULT_DATE = "9999-12-31";

    private LocalDate date;

    public Todo(String description) {
        super(description);
        this.date = LocalDate.parse(DEFAULT_DATE);
    }

    public String getTaskIcon() {
        return TODO_ICON;
    }

    public String getTime() {
        return TIME_INFO;
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }
}
//@@author
