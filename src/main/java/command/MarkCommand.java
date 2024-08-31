package command;

import file.TaskFile;
import tasks.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    private int idx;
    public MarkCommand(int idx){
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskList.markTask(idx);
    }


}
