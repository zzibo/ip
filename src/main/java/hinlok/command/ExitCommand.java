package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents an ExitCommand that exits the chat bot
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, TaskFile taskFile) {
        taskFile.saveTasks(taskList);
        return "Bye bro I zao first";
    }
}
