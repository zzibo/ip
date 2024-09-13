package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a command that deletes a task from the task list
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructor for DeleteCommand
     * @param taskIndexDelete
     */
    public DeleteCommand(int taskIndexDelete) {
        this.idx = idx;

    }

    /**
     * Deletes a task from the task list based on the idx given
     * @param taskList
     * @param taskFile
     * @return a String that confirms the task is deleted
     * @throws HinlokException
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) throws HinlokException {
        return taskList.deleteTask(idx);
    }
}
