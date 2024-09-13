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
     * @param idx
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks a task as done based on the index
     * @param taskList
     * @param taskFile
     * @return message from hinlok that the task is marked
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        return taskList.markTask(idx);
    }
}
