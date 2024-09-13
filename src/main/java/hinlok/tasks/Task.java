package hinlok.tasks;

/**
 * Represents a Task with a name and status
 */
public class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructor for a task with name and status
     * @param name name of the task
     * @param isDone status of the task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;

    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatus() + "] " + getName();
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }


}
