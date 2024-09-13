package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a Command that lists all the tasks
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        return taskList.showTasks();
    }


}
