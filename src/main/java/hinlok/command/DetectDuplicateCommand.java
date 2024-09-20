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
     *
     * @param taskDetails details of task
     */
    public DetectDuplicateCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Returns duplicated tasks that are detected
     *
     * @param taskList task list with all tasks
     * @param taskFile task file that store tasks into data
     * @return duplicated tasks
     */
    public String execute(TaskList taskList, TaskFile taskFile) {
        int num = 0;
        String duplicates = "These tasks are duplicated according to their name,"
                + " delete them by typing 'delete [task index]':\n";
        int idx = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task.getName().equals(this.taskDetails)) {
                duplicates += idx + ". " + task + "\n";
                num++;
            }
            idx++;
        }
        if (num == 0) {
            duplicates = "There are no duplicated tasks with this name " + this.taskDetails;
        }
        return duplicates;
    }
}
