package command;

import file.TaskFile;
import tasks.TaskList;
import ui.Ui;


public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskList.unmarkTask(idx);
    }

}