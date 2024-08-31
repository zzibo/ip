package command;

import file.TaskFile;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) {
        taskFile.saveTasks(taskList);
        System.out.println("see ya");
        isExit();
    }

    public boolean isExit(){
        return true;
    }
}
