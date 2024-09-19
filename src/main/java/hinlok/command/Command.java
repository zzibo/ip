package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents an abstract class Command
 */
public abstract class Command {
    /**
     * Represents an abstract method that executes the command
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     */
    public abstract String execute(TaskList taskList, TaskFile taskFile) throws HinlokException;
}

