package hinlok.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline task with a due date
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a deadline task
     * @param name name of deadline
     * @param by due date of deadline
     * @param isDone status of task
     */
    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns String of formatted date of the deadline
     *
     * @return String of formatted date
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a string representation of the deadline
     *
     * @return String of deadline
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
