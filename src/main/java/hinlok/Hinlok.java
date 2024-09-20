package hinlok;

import hinlok.command.Command;
import hinlok.parser.Parser;
import hinlok.storage.TaskFile;
import hinlok.tasks.TaskList;


/**
 * Represent a chatbot that will handle a task list based on the user input
 */
public class Hinlok {
    private final TaskList taskList;
    private final TaskFile taskFile;

    /**
     * Constructor for Hinlok class
     *
     * @param savedFilePath path to file that stores tasks
     */
    public Hinlok(String savedFilePath) {
        taskFile = new TaskFile(savedFilePath);
        taskList = new TaskList(taskFile.loadTaskFromFile());
    }

    /**
     * Returns a String of the response of the bot
     *
     * @param input input text of the user
     * @return a String of the response
     */

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            String response = c.execute(taskList, taskFile);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
