package hinlok.command;

import java.util.ArrayList;

import hinlok.exceptions.HinlokException;
import hinlok.storage.TaskFile;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;

/**
 * Represents a command that finds particular tasks in the task list
 */

public class FindCommand extends Command {

    private final String taskDetails;


    /**
     * Constructor for FindCommand with task details and type type
     *
     * @param taskDetails details of task
     */
    public FindCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute(TaskList taskList, TaskFile taskFile) throws HinlokException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()) {
            if (task.getName().contains(taskDetails)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            return "Sorry bro, no matching tasks found";
        }
        String res = "Got it bro,here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            res += ((i + 1) + "." + matchingTasks.get(i).toString());
        }
        return res;
    }
}
