package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a Command that lists all the tasks
 */
public class ListCommand extends Command {

    /**
     * Returns String of the task lis
     *
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     * @return String of task list
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        return taskList.showTasks();
    }


}
