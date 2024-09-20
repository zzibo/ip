package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a command that will unmark a task in the task list
 */
public class UnmarkCommand extends Command {
    private int idx;

    /**
     * Constructor for UnmarkCommand
     * @param idx index of task to be unmarked
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }
    /**
     * Marks a task as undone based on the index
     *
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     * @return message from hinlok that the task is unmarked
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        return taskList.unmarkTask(idx);
    }
}
