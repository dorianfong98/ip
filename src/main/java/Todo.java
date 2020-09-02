/**
 * The Todo class inherits from the Task class.
 * E.g. of input: todo borrow book
 * E.g. of output: [T][✓] join sports club
 */

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
