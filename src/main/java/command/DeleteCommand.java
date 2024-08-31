package command;

import exceptions.HinlokException;
import file.TaskFile;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int idx;
    public DeleteCommand(int taskIndexDelete) {
        this.idx = idx;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException {
        taskList.deleteTask(idx);

    }
}
