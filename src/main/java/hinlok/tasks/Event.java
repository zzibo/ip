package hinlok.tasks;

/**
 * Represents an Event that starts at /from and ends at /to
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an event task
     * @param name name of event
     * @param from start time
     * @param to end time
     * @param isDone status of task
     */
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time
     *
     * @return from
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time
     *
     * @return to
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of an event
     * @return String of event
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
