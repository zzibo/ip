package command;

import file.TaskFile;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        ui.showTasks(taskList);
        isExit();
    }



}
