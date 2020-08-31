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

    //Set isDone to true
    public void markAsDone() {
        isDone = true;
    }
}