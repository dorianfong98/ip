/**
 * The Task class provides a reference for more specific types of Task objects, and stores description and status.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Returns symbol corresponding to completion status of task
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    //Sets isDone to true
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}