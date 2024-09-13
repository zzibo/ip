package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents an abstract class Command
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, TaskFile taskFile) throws HinlokException;
}

