package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a Command that marks task as done based on the index given
 */
public class MarkCommand extends Command {
    private int idx;

    /**
     * Constructs a MarkCommand
     * @param idx index of task to be marked
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks a task as done based on the index
     *
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     * @return message from hinlok that the task is marked
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        return taskList.markTask(idx);
    }
}
