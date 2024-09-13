package hinlok.tasks;

/**
 * Represents a todo task with a name
 */
public class Todo extends Task {
    /**
     * Constructor for todo task
     * @param name name of the todo task
     * @param isDone status of the todo task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the task as a string
     * @return String containing task type, status and string
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}

