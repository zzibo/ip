package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents an ExitCommand that exits the chat bot
 */
public class ExitCommand extends Command {
    /**
     * Returns exit message
     *
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     * @return exit message
     */
    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        taskFile.saveTasks(taskList);
        return "Bye bro I zao first";
    }
}
