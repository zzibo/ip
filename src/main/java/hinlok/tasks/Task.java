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

    /**
     * Returns a String that represents the status
     * @return String of status
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Return a String representation of a task
     *
     * @return String of task
     */
    public String toString() {
        return "[" + getStatus() + "] " + getName();
    }

    /**
     * Returns the name of the task
     *
     * @return name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Assign isDone as True
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Assign isDone as false
     */
    public void markAsUndone() {
        this.isDone = false;
    }


}
