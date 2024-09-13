package hinlok.command;

import hinlok.storage.TaskFile;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;

/**
 * Represents a Command that deletes duplicates
 */
public class DetectDuplicateCommand extends Command {
    private final String taskDetails;

    /**
     * Constructor for DetectDuplicateCommand
     * @param taskDetails details of task
     */
    public DetectDuplicateCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Returns duplicated command
     * @param taskList
     * @param taskFile
     * @return
     */
    public String execute(TaskList taskList, TaskFile taskFile) {
        String duplicates = "These tasks are duplicated according to their name "
                + "delete them by typing 'delete [task index]':\n";
        int idx = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task.getName().equals(this.taskDetails)) {
                duplicates += idx + ". " + task.toString() + "\n";
            }
            idx++;
        }
        return duplicates;
    }
}
