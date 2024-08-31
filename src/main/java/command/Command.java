package command;

import exceptions.HinlokException;
import file.TaskFile;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException;

    public boolean isExit(){
        return false;
    }
}

