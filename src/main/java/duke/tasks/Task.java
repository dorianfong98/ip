package duke.tasks;

// @@author dorianfong98-reused
// Reused from https://github.com/dojh111/ip/blob/master/src/main/java/walter/tasks/Task.java with minor modifications
/**
 * The duke.tasks.Task class provides a template for the duke.tasks.Task object where description and status is stored
 */
public abstract class Task {

    public static final String TICK_ICON = "Y";
    public static final String CROSS_ICON = "N";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? TICK_ICON : CROSS_ICON);
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskIcon();

    public abstract String getTime();

    public abstract String getDate();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
//@@author
