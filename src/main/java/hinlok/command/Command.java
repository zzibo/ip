package hinlok.command;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public abstract class Command {

    /**
     * Execute the command based on the command subclass
     *
     * @param taskList
     * @param ui
     * @param taskFile
     * @throws HinlokException
     */
    public abstract void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException;

    /**
     * Returns a boolean to exit the chatbot if necessary
     * @return isExit
     */
    public boolean isExit(){
        return false;
    }
}

