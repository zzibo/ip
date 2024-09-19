package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.parser.Parser.CommandType;
import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;

/**
 * Represents a command that adds a task into the task list based on the command type
 */
public class AddCommand extends Command {
    private final String taskDetails;
    private final CommandType type;

    /**
     * Constructor for AddCommand
     *
     * @param taskDetails details of task
     * @param type type of class
     */
    public AddCommand(String taskDetails, CommandType type) {
        this.taskDetails = taskDetails;
        this.type = type;
    }

    @Override
    public String execute(TaskList taskList, TaskFile taskFile) throws HinlokException {
        switch (type) {
        case TODO:
            return taskList.addTodo(taskDetails);
        case DEADLINE:
            return taskList.addDeadline(taskDetails);
        case EVENT:
            return taskList.addEvent(taskDetails);
        default:
            throw new HinlokException("simi task type is this");
        }
    }
}
